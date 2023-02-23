package fluffandpaws.webadopcion.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingsController {
    //Esto es para movernos en las diferentes pantallas
    @GetMapping("/")
    public String index(Model model) {
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;*/
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
       // ModelAndView modelAndView = new ModelAndView();
       // modelAndView.setViewName("about");
       // return modelAndView;
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return "contact";
    }

    @GetMapping("/gallery.html")
    public ModelAndView gallery(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gallery");
        return modelAndView;
    }

    ////////////hacer getmapping de imagenes//////////////////////////////
}
