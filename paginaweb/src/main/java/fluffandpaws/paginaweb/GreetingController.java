package fluffandpaws.paginaweb;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {
    @GetMapping("/paws")
    public String greeting(Model model){
        model.addAttribute("name", "");
        return "greeting_template";
    }
}
