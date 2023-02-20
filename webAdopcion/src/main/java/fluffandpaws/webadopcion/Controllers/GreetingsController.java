package fluffandpaws.webadopcion.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GreetingsController {
    //Esto es para movernos en las diferentes pantallas
    @GetMapping("/index.html")
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/about.html")
    public ModelAndView about(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @GetMapping("/contact.html")
    public ModelAndView contact(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @GetMapping("/gallery.html")
    public ModelAndView gallery(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gallery");
        return modelAndView;
    }

    ////////////hacer getmapping de imagenes//////////////////////////////
}
