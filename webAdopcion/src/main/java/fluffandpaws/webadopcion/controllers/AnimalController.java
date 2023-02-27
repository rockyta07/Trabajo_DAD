package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.model.Animal;
import fluffandpaws.webadopcion.service.AnimalService;
import fluffandpaws.webadopcion.service.ProtectoraService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


//El controller va conectado con el service, asi que aprovechamos los métodos de service y los llamamos
@Controller
@RequestMapping("/Animales")
public class AnimalController {

    @Autowired
    private AnimalService servAnimales;
    private ProtectoraService protectoraService;

    @GetMapping("/")//buscamos todos los animales
    public String getAnimales(Model model) {
        List<Animal> listAnimals = servAnimales.findAll();
        model.addAttribute("listaTodos", listAnimals);
        return "todos_animales";
    }

    @GetMapping("/{id}")//Esto nos retorna el animal
    public String getAnimal(Model model, @PathVariable Long id) throws SQLException {
        Animal aux = servAnimales.findById(id).orElseThrow();
        byte[] imagenBytes = aux.getImageAnimal().getBytes(1, (int) aux.getImageAnimal().length()); // convertimos Blob a byte[]
        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes); // codificamos a base64
        model.addAttribute("animal", aux);
        model.addAttribute("imagenBase64", imagenBase64); // agregamos el atributo a la vista
        return "animal";
    }

    @GetMapping("/crearAnimal")
    public String crearAnimal(){
        return "registrarAnimal";
    }

    @PostMapping("/crearAnimal")
    public String crearAnimalProcess(@RequestParam("name") String name,
                                     @RequestParam("species") String species,
                                     @RequestParam("breed") String breed,
                                     @RequestParam("gender") String gender,
                                     @RequestParam("birthdate") String birthdate,
                                     @RequestParam("weight") int weight,
                                     @RequestParam("size") String size,
                                     @RequestParam("id_protectora") long id_protectora,
                                     MultipartFile imagenAnimal) throws IOException{

        Animal animal = new Animal(name, species, breed, gender, birthdate, weight, size,
                protectoraService.findById(id_protectora).orElseThrow(() -> new RuntimeException("Shelter not found")));
        servAnimales.save(animal);

        if (!imagenAnimal.isEmpty()) {
            animal.setImageAnimal(BlobProxy.generateProxy(imagenAnimal.getInputStream(), imagenAnimal.getSize()));
            animal.setImage(true);
        }

        servAnimales.save(animal);
        return "redirect:/Animales/" + animal.getId();
    }

    //______//

    //Los mostramos todos en una lista y editamos según el animal, por lo que existe si o si
    @PostMapping("/editarAnimal/{id}")
    public String editAnimalProcess(@PathVariable("id") Long id, @RequestParam("") Animal animal) {
        servAnimales.save(animal);
        return "redirect:/editarAnimal/" + animal.getId();
    }

    /*
    @PostMapping("/editarAnimal/")
    public String editAnimalProcess(Model model, Animal animal) {
        servAnimales.save(animal);
        model.addAttribute("id", animal.getId());
        return "redirect:/editarAnimal/"+animal.getId();
    }

    @PutMapping("/editarAnimal/{id}")//para modificar animal
    public String editAnimal(Model model, @PathVariable Long id) {
        //Busacamos la id dada para modificar un animal válido
        Optional<Animal> aux = servAnimales.findById(id);
        if(aux.isEmpty()){
            //No existe el animal
            return "notFoundError";
        }
        //Si si lo encuentra
        model.addAttribute(aux.get());
        return "editAnimal";
    }
    */

    @DeleteMapping("/borrarAnimal/{id}")//para borrar el animal
    public String removeAnimalProcess(Model model, @PathVariable long id) {
        Optional<Animal> animal = servAnimales.findById(id);
        if (animal.isPresent()) {
            servAnimales.delete(id);
            model.addAttribute("animalborrado", animal.get());
        }
        return "todos_animales";
    }
}
