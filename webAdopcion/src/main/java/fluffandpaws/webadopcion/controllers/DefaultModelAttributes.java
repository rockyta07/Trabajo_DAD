package fluffandpaws.webadopcion.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttributes {

    //ProtectoraService anServ_DMA = new ProtectoraService();
    @ModelAttribute("globalVar")
    public String globalVar(){

        //List<Protectora> aux = anServ_DMA.findAll();
        //String name = aux.get(1).getName();
        return "variable global";
    }
}
