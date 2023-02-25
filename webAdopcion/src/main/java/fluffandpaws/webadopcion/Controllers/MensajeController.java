package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.BBDD.Mensaje;
import fluffandpaws.webadopcion.Service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/Mensajes")
public class MensajeController {

    @Autowired
    private MensajeService servMensajes;


    @GetMapping("/")//buscamos todos los mensajes
    public String getMessages(Model model) {

        List<Mensaje> listMensajes = servMensajes.findAll();

        model.addAttribute("listMsg", listMensajes);

        return "todos_mensajes";
    }


    @GetMapping("/{id}")//Esto nos retorna el mensaje
    public String getMessagge(Model model, @PathVariable Long id) {
        Mensaje aux_mensaje = servMensajes.findById(id).orElseThrow();

        model.addAttribute("msg", aux_mensaje);

        return "mensaje";
    }

    @GetMapping("/crearMensaje")
    public String crearMensaje(){
        return "guardarMensaje";
    }

    @PostMapping("/crearMensaje")
    public String crearMensajeProcess(Model model, @ModelAttribute("mensaje") Mensaje aux2){
        servMensajes.save(aux2);
        return "redirect:/Mensajes/" + aux2.getId();
    }
/*
    @PutMapping("/{id}")//para modificar mensaje

    public Mensaje replaceMessages(@PathVariable Long id, @RequestBody Mensaje newMensaje) {

        newMensaje.setId(id);
        servMensajes.replace(newMensaje);
        return newMensaje;

    }
    @DeleteMapping("/{id})")//borramos el mensaje

    public Mensaje deleteMessages(@PathVariable Long id){

       Mensaje m = servMensajes.findById(id).orElseThrow();
       servMensajes.delete(id);
        return m;


    }
    */


}
