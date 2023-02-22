package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Shelter;
import fluffandpaws.webadopcion.Service.animalesService;
import fluffandpaws.webadopcion.Service.mensaggesService;
import fluffandpaws.webadopcion.Service.shelterService;
import fluffandpaws.webadopcion.Service.userService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class sheltersController {

    @Autowired
    private shelterService shelterservice;

    @Autowired
    private mensaggesService mensajerservice;


    @GetMapping("/")
    public String showShelter(Model model) {

        model.addAttribute("shelter",shelterservice.findAll());

        return "shelter";
    }

    @GetMapping("/shelter/{id}")
    public String showShelter(Model model, @PathVariable long id) {

        Optional<Shelter> shelter = shelterservice.findById((int) id);
        if (shelter.isPresent()) {
            model.addAttribute("shelter", shelter.get());
            return "shelter";//habrá que crear estas paginas html
        } else {
            return "shelters";
        }

    }


    @GetMapping("/removeMensaje/{id}")
    public String removeMensaje(Model model, @PathVariable long id) {

        Optional<Shelter> shelter = shelterservice.findById((int) id);
        if (shelter.isPresent()) {
            shelterservice.deleteById((int) id);
            model.addAttribute("shelter", shelter.get());
        }
        return "removedshelter";
    }

    @GetMapping("/newShelter")
    public String newShelter(Model model) {

        model.addAttribute("shelter", shelterservice.findAll());

        return "newShelter";
    }

    @PostMapping("/newShelter")
    public String newShelterProcess(Model model, Shelter shelter) throws IOException {


        //animal.setShops(shopService.findById(selectedShops));

     shelterservice.save(shelter);

        model.addAttribute("shelterId", shelter.getId());

        return "redirect:/shelter/"+shelter.getId();
    }

    @GetMapping("/editShelter/{id}")
    public String editShelter(Model model, @PathVariable long id) {

        Optional<Shelter> shelter = shelterservice.findById((int) id);
        if (shelter.isPresent()) {
            model.addAttribute("shelter",shelter.get());
            return "editShelter";
        } else {
            return "shelter";
        }
    }

    @PostMapping("/editShelter")
    public String editShelterProcess(Model model,Shelter shelter)
            throws IOException, SQLException {



        shelterservice.save(shelter);

        model.addAttribute("shelterId", shelter.getId());

        return "redirect:/shelter/"+shelter.getId();
    }



}