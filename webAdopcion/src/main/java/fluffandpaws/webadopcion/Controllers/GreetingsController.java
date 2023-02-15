package fluffandpaws.webadopcion.Controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
//Esto es para movernos en las diferentes pantallas
    @GetMapping("/index.html")

    public String index(Model model) {

        return "index";
    }

    @GetMapping("/about.html")

    public String about(Model model) {

        return "about";
    }

    @GetMapping("/contact.html")

    public String contact(Model model) {

        return "contact";
    }

    @GetMapping("/gallery.html")

    public String gallery(Model model) {

        return "gallery";
    }

    ////////////hacer getmapping de imagenes//////////////////////////////
}
