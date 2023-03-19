package fluffandpaws.webadopcion.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller

public class LoginWebController {

    @GetMapping("/login")
    public String login(Principal principal) {

        return "login";
    }

    @GetMapping("/loginerror")//entramos al login error en caso de error

    public String loginerror(){
        return "loginerror";

    }

    @GetMapping ("/logout")//entramos a la url de login

    public String logout() {
        return "logout";
    }


}
