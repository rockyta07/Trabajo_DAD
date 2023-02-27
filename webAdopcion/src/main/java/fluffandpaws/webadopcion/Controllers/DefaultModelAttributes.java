package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Protectora;
import fluffandpaws.webadopcion.Service.ProtectoraService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class DefaultModelAttributes {

    //ProtectoraService anServ_DMA = new ProtectoraService();
    @ModelAttribute("globalVar")
    public String globalVar(){

        //List<Protectora> aux = anServ_DMA.findAll();
        //String name = aux.get(1).getName();
        return "";
    }
}
