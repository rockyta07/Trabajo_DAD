package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Mensaje;
import fluffandpaws.webadopcion.models.Protectora;
import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.service.MensajeService;
import fluffandpaws.webadopcion.service.ProtectoraService;
import fluffandpaws.webadopcion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Mensajes")
public class MensajeController {

    @Autowired
    private MensajeService servMensajes;

    @Autowired
    private ProtectoraService servProtectoras;

    @Autowired
    private UsuarioService userService;

    @ModelAttribute //esto sirve para que si yo soy admin pueda ver el boton de borrado y si no lo soy pues no
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();//realizar la autenticacion y autorizacion web
//en resumen para obtener la identificacion del usuario
        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("name", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/")//buscamos todos los mensajes
    public String getMessages(Model model, Principal principal) {
        //Filtramos lo que se devuelve según si es user o admin
        if(principal.getName().equals("admin")){
            List<Mensaje> listMensajes = servMensajes.findAll();
            model.addAttribute("listMsg", listMensajes);
        } else{
            Usuario usuario = userService.findByUsername(principal.getName());
            List<Mensaje> listMensajesUser = servMensajes.findBySender(usuario.getEmail()); //Esto va a buscar todos los mensajes del usuario loggeado
            model.addAttribute("mails", listMensajesUser);
        }
        return "/temp_Mensaje/todos_mensajes";
    }


    @GetMapping("/{id}")//Esto nos retorna el mensaje
    public String getMessagge(Model model, @PathVariable Long id) {
        Mensaje aux_mensaje = servMensajes.findById(id).orElseThrow();

        model.addAttribute("msg", aux_mensaje);
        model.addAttribute("description", aux_mensaje.toString());

        return "/temp_Mensaje/mensaje";
    }

    @GetMapping("/crearMensaje")
    public String crearMensaje(Model model){
        model.addAttribute("listaProtectoras", servProtectoras.findAll());
        return "/temp_Mensaje/guardarMensaje";
    }

    @PostMapping("/crearMensaje")
    public String crearMensajeProcess(@ModelAttribute("mensaje") Mensaje aux2, @RequestParam Protectora pselected){
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
            model.addAttribute("description", mensaje.toString());
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
