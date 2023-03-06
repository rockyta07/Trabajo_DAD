package fluffandpaws.webadopcion.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginWebController {

    @RequestMapping("/login")//entramos a la url de login

    public String login() {
        return "login";
    }

    @RequestMapping("/loginerror")//entramos al login error en caso de error

    public String loginerror(){
        return "loginerror";

    }

}
