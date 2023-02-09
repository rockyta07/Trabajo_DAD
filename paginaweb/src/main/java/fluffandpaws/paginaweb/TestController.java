package fluffandpaws.paginaweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    
    @GetMapping("/test")
    public String testD(Model model){
        model.addAttribute("name", "test");

        return "test_template";
    }

}
