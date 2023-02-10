package fluffandpaws.webadopcion;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
}
