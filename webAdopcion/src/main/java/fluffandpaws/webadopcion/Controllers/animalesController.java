package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animales;

import fluffandpaws.webadopcion.Service.animalesService;
import fluffandpaws.webadopcion.Service.userService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class animalesController {

    @Autowired
    private animalesService animalesservice;

    @Autowired
    private userService userservice;

    @ModelAttribute

    public void addAttribute(Model model, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        if(principal != null){

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));

        }else{
            model.addAttribute("logged",false);
        }



    }
    @GetMapping("/")//busca todos
    public String showAnimales(Model model) {

        model.addAttribute("animales", animalesservice.findAll());

        return "animales";
    }

    @GetMapping("/animales/{id}")//nos retorna uno
    public String showAnimales(Model model, @PathVariable long id) {

        Optional<Animales> animal = animalesservice.findById((int) id);
        if (animal.isPresent()) {
            model.addAttribute("animales", animal.get());
            return "animal";//habrá que crear estas paginas html
        } else {
            return "animales";
        }

    }


    @GetMapping("/removeAnimales/{id}")//borra uno
    public String removeAnimales(Model model, @PathVariable long id) {

        Optional<Animales> animal = animalesservice.findById((int) id);
        if (animal.isPresent()) {
            animalesservice.deleteById((int) id);
            model.addAttribute("animal", animal.get());
        }
        return "removedanimal";
    }

    @GetMapping("/newAnimales")
    public String newAnimales(Model model) {

        model.addAttribute("animal", userservice.findAll());

        return "newAnimalPage";
    }

    @PostMapping("/newAnimales")
    public String newBookProcess(Model model, Animales animal, @RequestParam List<Long> selectedShops) throws IOException {


        //animal.setShops(shopService.findById(selectedShops));

        animalesservice.save(animal);


        model.addAttribute("animalId", animal.getId());

        return "redirect:/animales/"+animal.getId();
    }

    @GetMapping("/editAnimales/{id}")
    public String editAnimales(Model model, @PathVariable long id) {

        Optional<Animales> animales = animalesservice.findById((int) id);
        if (animales.isPresent()) {
            model.addAttribute("animal",animales.get());
            return "editAnimal";
        } else {
            return "animales";
        }
    }

   @PostMapping("/editAnimal")
    public String editAnimalesProcess(Model model, Animales animal)
                                        {

        animalesservice.save(animal);

        model.addAttribute("animalId", animal.getId());

        return "redirect:/animales/"+animal.getId();
    }



}