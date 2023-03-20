package fluffandpaws.webadopcion.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller

public class HeaderController {

    @GetMapping("/header")
    public ModelAndView renderHeader() {
        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3");
        ModelAndView modelAndView = new ModelAndView("header");
        modelAndView.addObject("options", options);
        return modelAndView;
    }

    @ModelAttribute //esto sirve para que si yo soy admin pueda ver el boton de borrado y si no lo soy pues no
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();//realizar la autenticacion y autorizacion web
//en resumen para obtener la identificacion del usuario
        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("name", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

}
