package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.BBDD.Protectora;
import fluffandpaws.webadopcion.Service.AnimalService;
import fluffandpaws.webadopcion.Service.MensajeService;
import fluffandpaws.webadopcion.Service.ProtectoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

    @Autowired
    private ProtectoraService servProtectoras;


    @GetMapping("/")//buscamos todos los animales
    public String getAnimales(Model model) {

        List<Animal> listAnimals = servAnimales.findAll();

        model.addAttribute("listaTodos", listAnimals);

        return "/temp_Animal/todos_animales";
    }

    @GetMapping("/{id}")//Esto nos retorna el animal
    public String getAnimal(Model model, @PathVariable Long id) throws SQLException {
        Animal aux = servAnimales.findById(id).orElseThrow();
        byte[] imagenBytes = aux.getImagenAnimal().getBytes(1, (int) aux.getImagenAnimal().length()); // convertimos Blob a byte[]
        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes); // codificamos a base64
        model.addAttribute("animal", aux);
        model.addAttribute("imagenBase64", imagenBase64); // agregamos el atributo a la vista
        return "/temp_Animal/animal";
    }

    @GetMapping("/crearAnimal")
    public String crearAnimal(Model model){

        model.addAttribute("listaProtectoras", servProtectoras.findAll());
        return "/temp_Animal/registrarAnimal";

    }

    @PostMapping("/crearAnimal")
    public String crearAnimalProcess(Model model, Animal aux2, MultipartFile imagenAnimal, @RequestParam Protectora pselected) throws IOException{


        if (!imagenAnimal.isEmpty()) {
            aux2.setImagenanimal(BlobProxy.generateProxy(imagenAnimal.getInputStream(), imagenAnimal.getSize()));
            aux2.setImagen(true);
        }

        aux2.setPrtOrigen(pselected);

        servAnimales.save(aux2);
        return "redirect:/Animales/" + aux2.getId();
    }


    @GetMapping("/borrarAnimal/{id}")
    public String removeAnimal(Model model, @PathVariable Long id){
        Optional<Animal> animal = servAnimales.findById(id);
        if(animal.isPresent()){
            servAnimales.delete(id);
            model.addAttribute("animal", animal.get());
        }
        return "/temp_Animal/animalBorrado";
    }

    @GetMapping("/editAnimal/{id}")
    public String editAnimal(Model model, @PathVariable Long id) {

        Optional<Animal> animal = servAnimales.findById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());

        }

        return "/temp_Animal/editAnimalPage";

    }

    @PostMapping("/editAnimal")
    public String editAnimal(Model model, Animal animal, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {

        //updateImage(book, removeImage, imageField);

        servAnimales.save(animal);

        model.addAttribute("animalId", animal.getId());

        return "redirect:/Animales/"+animal.getId();
    }

//esto de animales te redirige a la parte de arriba del controller



    public void setAnimalImage(Animal aux, String classpathResource) throws IOException {
        aux.setImagen(true);
        Resource image = new ClassPathResource(classpathResource);
        aux.setImagenanimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

}
