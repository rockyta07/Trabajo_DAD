package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.Service.mensaggesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private mensaggesService meServ;


    @GetMapping("/")//buscamos todos los mensajes
    public List<Messages> getMessages() {

        return (List<Messages>) meServ.findAll();
    }

    @GetMapping("/{id}")//Esto nos retorna el mensaje
    public Messages getMessagges(@PathVariable long id) {

        return meServ.findById((int) id).orElseThrow();
    }

    @PostMapping("/")//creamos el mensaje
    public Messages createMessages(@RequestBody Messages me) {

        meServ.save(me);
        return me;
    }

    @PutMapping("/{id}")//para modificar mensaje

    public Messages replaceMessages(@PathVariable long id, @RequestBody Messages newMessage) {

        newMessage.setId(Long.valueOf(id));
        meServ.replace(newMessage);
        return newMessage;

    }
    @DeleteMapping("/{id})")//borramos el mensaje

    public Messages deleteMessages(@PathVariable long id){

       Messages m = meServ.findById((int) id).orElseThrow();
        meServ.deleteById(Math.toIntExact(id));
        return m;


    }



}
