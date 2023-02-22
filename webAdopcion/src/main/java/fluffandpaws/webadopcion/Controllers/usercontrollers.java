package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Shelter;
import fluffandpaws.webadopcion.BBDD.Usuarios;
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
public class usercontrollers {

    @Autowired
    private userService userservice;

    @Autowired
    private animalesService animalesservice;


    @GetMapping("/")
    public String showUser(Model model) {

        model.addAttribute("user",userservice.findAll());

        return "user";
    }

    @GetMapping("/user/{id}")
    public String showUser(Model model, @PathVariable long id) {

        Optional<Usuarios> user = userservice.findById((int) id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user";//habrá que crear estas paginas html
        } else {
            return "users";
        }

    }


    @GetMapping("/removeUser/{id}")
    public String removeUser(Model model, @PathVariable long id) {

        Optional<Usuarios> user =userservice.findById((int) id);
        if (user.isPresent()) {
           userservice.deleteById((int) id);
            model.addAttribute("shelter", user.get());
        }
        return "removeduser";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {

        model.addAttribute("user", userservice.findAll());

        return "newuser";
    }

    @PostMapping("/newuser")
    public String newUserProcess(Model model, Usuarios user) throws IOException {


        //animal.setShops(shopService.findById(selectedShops));

       userservice.save(user);

        model.addAttribute("userId", user.getId());

        return "redirect:/user/"+user.getId();
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable long id) {

        Optional<Usuarios> user = userservice.findById((int) id);
        if (user.isPresent()) {
            model.addAttribute("user",user.get());
            return "editUser";
        } else {
            return "user";
        }
    }

    @PostMapping("/editUser")
    public String editShelterProcess(Model model,Usuarios user)
            throws IOException, SQLException {



        userservice.save(user);

        model.addAttribute("userId", user.getId());

        return "redirect:/shelter/"+user.getId();
    }



}