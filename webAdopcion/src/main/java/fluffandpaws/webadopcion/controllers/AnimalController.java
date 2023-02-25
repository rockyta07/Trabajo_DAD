package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.model.Animal;
import fluffandpaws.webadopcion.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


import org.hibernate.engine.jdbc.BlobProxy;


//El controller va conectado con el service, asi que aprovechamos los métodos de service y los llamamos
@Controller
@RequestMapping("/Animales")
public class AnimalController {

    @Autowired
    private AnimalService servAnimales;
    /*
        @Autowired
        private UsuarioService servUsuario;

     */

    @GetMapping("/")//buscamos todos los animales
    public String getAnimales(Model model) {

        List<Animal> listAnimals = servAnimales.findAll();

        model.addAttribute("listaTodos", listAnimals);

        return "todos_animales";
    }

    @GetMapping("/{id}")//Esto nos retorna el animal
    public String getAnimal(Model model, @PathVariable Long id) throws SQLException {
        Animal aux = servAnimales.findById(id).orElseThrow();
        byte[] imagenBytes = aux.getImagenAnimal().getBytes(1, (int) aux.getImagenAnimal().length()); // convertimos Blob a byte[]
        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes); // codificamos a base64
        model.addAttribute("animal", aux);
        model.addAttribute("imagenBase64", imagenBase64); // agregamos el atributo a la vista
        return "animal";
    }

    @GetMapping("/animales/{id}/image") //Para mostrar las imagenes de los animales
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Optional<Animal> animal = servAnimales.findById(id);
        if (animal.isPresent() && animal.get().getImageFile() != null) {
            Resource file = new InputStreamResource(animal.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(animal.get().getImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/crearAnimal")
    public String crearAnimal(){
        return "registrarAnimal";
    }

    @PostMapping("/crearAnimal")
    public String crearAnimalProcess(Model model, Animal aux2, MultipartFile imagenAnimal) throws IOException{

        if (!imagenAnimal.isEmpty()) {
            aux2.setImagenanimal(BlobProxy.generateProxy(imagenAnimal.getInputStream(), imagenAnimal.getSize()));
            aux2.setImagen(true);
        }

        servAnimales.save(aux2);
        return "redirect:/Animales/" + aux2.getId();
    }

    //______//

    @PostMapping("/editarAnimal")
    public String editAnimalProcess(Model model, Animal animal) {
        servAnimales.save(animal);
        model.addAttribute("Id", animal.getId());
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

    @GetMapping("/removeanimales/{id}")//para borrar el animal
    public String removeAnimal(Model model, @PathVariable long id) {

        Optional<Animal> animal = servAnimales.findById(id);
        if (animal.isPresent()) {
            servAnimales.delete(id);
            model.addAttribute("animalborrado", animal.get());
        }
        return "todos_animales";
    }

}
