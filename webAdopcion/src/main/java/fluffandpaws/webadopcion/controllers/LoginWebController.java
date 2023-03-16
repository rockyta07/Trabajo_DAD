package fluffandpaws.webadopcion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginWebController {

    @GetMapping ("/login")//entramos a la url de login

    public String login() {
        return "login";
    }

    @GetMapping("/loginerror")//entramos al login error en caso de error

    public String loginerror(){
        return "loginerror";

    }

}
