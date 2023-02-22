package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.Service.animalesService;
import fluffandpaws.webadopcion.Service.mensaggesService;
import fluffandpaws.webadopcion.Service.shelterService;
import fluffandpaws.webadopcion.Service.userService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class MesagesController {

    @Autowired
    private mensaggesService mensaggeservice;

    @Autowired
    private shelterService shelterservice;

    //Esto ya esta en animales controller asi que se comenta
   /* @ModelAttribute

    public void addAttribute(Model model, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        if(principal != null){

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));

        }else{
            model.addAttribute("logged",false);
        }



    }*/
    @GetMapping("/")
    public String showMensajes(Model model) {

        model.addAttribute("mensajes",mensaggeservice.findAll());

        return "mensajes";
    }

    @GetMapping("/mensajes/{id}")
    public String showMensajes(Model model, @PathVariable long id) {

        Optional<Messages> mensaje = mensaggeservice.findById((int) id);
        if (mensaje.isPresent()) {
            model.addAttribute("mensajes", mensaje.get());
            return "mensaje";//habrá que crear estas paginas html
        } else {
            return "mensajes";
        }

    }


    @GetMapping("/removeMensaje/{id}")
    public String removeMensaje(Model model, @PathVariable long id) {

        Optional<Messages> mensaje = mensaggeservice.findById((int) id);
        if (mensaje.isPresent()) {
            mensaggeservice.deleteById((int) id);
            model.addAttribute("mensaje", mensaje.get());
        }
        return "removedmensaje";
    }

    @GetMapping("/newMensaje")
    public String newAnimales(Model model) {

        model.addAttribute("shelter", shelterservice.findAll());

        return "newMensaje";
    }

    @PostMapping("/newMensaje")
    public String newMensajeProcess(Model model, Messages mensaje, @RequestParam List<Long> selectedShops) throws IOException {


        //animal.setShops(shopService.findById(selectedShops));

      mensaggeservice.save(mensaje);

        model.addAttribute("mensajeId", mensaje.getId());

        return "redirect:/animales/"+mensaje.getId();
    }

    @GetMapping("/editMensaje/{id}")
    public String editAnimales(Model model, @PathVariable long id) {

        Optional<Messages> mensajes = mensaggeservice.findById((int) id);
        if (mensajes.isPresent()) {
            model.addAttribute("mensaje",mensajes.get());
            return "editMensaje";
        } else {
            return "mensajes";
        }
    }

    @PostMapping("/editMensaje")
    public String editMnesajesProcess(Model model, Messages mensaje)
            throws IOException, SQLException {



        mensaggeservice.save(mensaje);

        model.addAttribute("mensajeId", mensaje.getId());

        return "redirect:/mensaje/"+mensaje.getId();
    }



}