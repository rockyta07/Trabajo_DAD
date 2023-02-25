package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;


import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//El controller va conectado con el service, asi que aprovechamos los métodos de service y los llamamos
@Controller
@RequestMapping("/Animales")
public class AnimalController {

    @Autowired
    private AnimalService servAnimales;


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

    /*
    @PostMapping("/")//creamos el animal
    public Animal createAnimales(@RequestBody Animal an) {

        servAnimales.save(an);
        return an;
    }

    @PutMapping("/{id}")//para modificar animal

    public Animal replaceAnimales(@PathVariable Long id, @RequestBody Animal newAnimal) {

        newAnimal.setId(id);
        servAnimales.replace(newAnimal);
        return newAnimal;

    }
    @DeleteMapping("/{id})")//borramos el animal

    public Animal deleteAnimales(@PathVariable Long id){

       Animal a = servAnimales.findById(id).orElseThrow();
        servAnimales.delete(id);
        return a;


    }

    */


}
