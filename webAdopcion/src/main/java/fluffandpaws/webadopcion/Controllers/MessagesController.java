package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Shelter;
import fluffandpaws.webadopcion.Service.animalesService;
import fluffandpaws.webadopcion.Service.mensaggesService;
import fluffandpaws.webadopcion.Service.shelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private mensaggesService men;


    @GetMapping("/")//buscamos todos los mensajes
    public List<Messages> getMessages() {

        return (List<Messages>) men.findAll();
    }

    @GetMapping("/{id}")//Esto nos retorna el mensaje
    public Messages getMessagges(@PathVariable Integer id) {

        return men.findById(id).orElseThrow();
    }

    @PostMapping("/")//creamos el mensaje
    public Messages createMessages(@RequestBody Messages me) {

       men.save(me);
        return me;
    }

    @PutMapping("/{id}")//para modificar mensaje

    public Messages replaceMessages(@PathVariable Integer id, @RequestBody Messages newMessage) {

        newMessage.setId(id);
        men.replace(newMessage);
        return newMessage;

    }
    @DeleteMapping("/{id})")//borramos el mensaje

    public Messages deleteMessages(@PathVariable Integer id){

       Messages m = men.findById(id).orElseThrow();
       men.deleteById(Math.toIntExact(id));
        return m;


    }



}