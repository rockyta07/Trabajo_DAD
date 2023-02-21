package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animales;
import fluffandpaws.webadopcion.BBDD.Shelter;
import fluffandpaws.webadopcion.Service.animalesService;
import fluffandpaws.webadopcion.Service.shelterService;
import fluffandpaws.webadopcion.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shelter")
public class shelterController {

    @Autowired
    private shelterService shel;


    @GetMapping("/")//buscamos todos los shelter
    public List<Shelter> getShelter() {

        return (List<Shelter>) shel.findAll();
    }

    @GetMapping("/{id}")//Esto nos retorna el shelter
    public Shelter getShelter(@PathVariable Integer id) {

        return shel.findById(id).orElseThrow();
    }

    @PostMapping("/")//creamos el shelter
    public Shelter createShelter(@RequestBody Shelter sh) {

        shel.save(sh);
        return sh;
    }

    @PutMapping("/{id}")//para modificar shelter

    public Shelter replaceShelter(@PathVariable Integer id, @RequestBody Shelter newShelter) {

        newShelter.setId(id);
        shel.replace(newShelter);
        return newShelter;

    }

    @DeleteMapping("/{id})")//borramos el shelter

    public Shelter deleteShelter(@PathVariable Integer id) {

        Shelter s = shel.findById(id).orElseThrow();
        shel.deleteById(id);
        return s;


    }
