package fluffandpaws.webadopcion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class GreetingsController {
    //Esto es para movernos en las diferentes pantallas
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/Animales/";
    }

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    ////////////hacer getmapping de imagenes//////////////////////////////
}
