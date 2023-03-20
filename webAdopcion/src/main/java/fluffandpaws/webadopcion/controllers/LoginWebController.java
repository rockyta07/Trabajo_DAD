package fluffandpaws.webadopcion.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.security.Principal;
//Este código define un controlador web en Spring para la página de login. El controlador tiene tres métodos: login,loginerror,loginout
//para poder iniciar sesion, cerrar y en el caso de que metas mas los credenciales
@Controller

public class LoginWebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginerror")//entramos al login error en caso de error

    public String loginerror(){
        return "loginerror";

    }

    /*
    @GetMapping ("/logout")//entramos a la url de login
    public String logout() {
        return "logout";
    }*/


}
