package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animales;
import fluffandpaws.webadopcion.Service.animalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//El controller va conectado con el service, asi que aprovechamos los métodos de service y los llamamos
@RestController
@RequestMapping("/animales")
public class animalController {

    @Autowired
    private animalesService animal;


    @GetMapping("/")//buscamos todos los animales
    public List<Animales> getAnimales() {

        return (List<Animales>) animal.findAll();
    }

    @GetMapping("/{id}")//Esto nos retorna el animal
    public Animales getAnimales(@PathVariable Long id) {

        return animal.findById(id).orElseThrow();
    }

    @PostMapping("/")//creamos el animal
    public Animales createAnimales(@RequestBody Animales an) {

        animal.save(an);
        return an;
    }

    @PutMapping("/{id}")//para modificar animal

    public Animales replaceAnimales(@PathVariable Long id, @RequestBody Animales newAnimal) {

        newAnimal.setId(id);
        animal.replace(newAnimal);
        return newAnimal;

    }
    @DeleteMapping("/{id})")//borramos el animal

    public Animales deleteAnimales(@PathVariable Long id){

       Animales a = animal.findById(id).orElseThrow();
        animal.deleteById(id);
        return a;


    }



}
