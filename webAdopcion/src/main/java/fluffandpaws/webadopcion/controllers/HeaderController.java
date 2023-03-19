package fluffandpaws.webadopcion.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
