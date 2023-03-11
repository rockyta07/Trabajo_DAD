package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Mensaje;
import fluffandpaws.webadopcion.models.Protectora;
import fluffandpaws.webadopcion.service.MensajeService;
import fluffandpaws.webadopcion.service.ProtectoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Mensajes")
public class MensajeController {

    @Autowired
    private MensajeService servMensajes;

    @Autowired
    private ProtectoraService servProtectoras;


    @GetMapping("/")//buscamos todos los mensajes
    public String getMessages(Model model) {

        List<Mensaje> listMensajes = servMensajes.findAll();

        model.addAttribute("listMsg", listMensajes);

        return "/temp_Mensaje/todos_mensajes";
    }


    @GetMapping("/{id}")//Esto nos retorna el mensaje
    public String getMessagge(Model model, @PathVariable Long id) {
        Mensaje aux_mensaje = servMensajes.findById(id).orElseThrow();

        model.addAttribute("msg", aux_mensaje);

        return "/temp_Mensaje/mensaje";
    }

    @GetMapping("/crearMensaje")
    public String crearMensaje(Model model){
        model.addAttribute("listaProtectoras", servProtectoras.findAll());
        return "/temp_Mensaje/guardarMensaje";
    }

    @PostMapping("/crearMensaje")
    public String crearMensajeProcess(Model model, @ModelAttribute("mensaje") Mensaje aux2, @RequestParam Protectora pselected){
        aux2.setPrtInstance(pselected);
        servMensajes.save(aux2);
        return "redirect:/Mensajes/" + aux2.getId();
    }

    @GetMapping("/borrarMensaje/{id}")
    public String removeMensaje(Model model, @PathVariable Long id){
        Optional<Mensaje> mensaje = servMensajes.findById(id);
        if(mensaje.isPresent()){
            servMensajes.delete(id);
            model.addAttribute("mensaje", mensaje.get());
        }
        return "/temp_Mensaje/mensajeBorrado";
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
