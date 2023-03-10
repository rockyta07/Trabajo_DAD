package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioService servUsuarios;

//creamos un petodo que nos creara una identificacion unica de sesion para un usuario, esto se crea gracias A uuid que es un estandar que viene en java

    @GetMapping("/")//buscamos todos los usuarios
    public String getUsuarios(Model model) {
        List<Usuario> listUsr = servUsuarios.findAll();

        model.addAttribute("listUsuarios", listUsr);

        return "/temp_Usuario/todos_usuarios";
    }

    @GetMapping("/{id}")//Esto nos retorna el usuario
    public String getUsuario(Model model, @PathVariable Long id) {

        Usuario adoptante = servUsuarios.findById(id).orElseThrow();

        model.addAttribute("user", adoptante);

        return "/temp_Usuario/usuario";
    }

    @GetMapping("/registroUsuario")
    public String registraUsuario(){
        return "/temp_Usuario/registrarUsuario";
    }

    @PostMapping("/registroUsuario")
    public String registraUsuarioProcess(Model model, @ModelAttribute("animal") Usuario aux2){
        servUsuarios.save(aux2);
        return "redirect:/Usuarios/" + aux2.getId();
    }

    @GetMapping("/borrarUsuario/{id}")
    public String removeUsuario(Model model,@PathVariable Long id){
        Optional<Usuario> user = servUsuarios.findById(id);
        if(user.isPresent()){
            servUsuarios.delete(id);
            model.addAttribute("usuario", user.get());
        }
        return "/temp_Usuario/usuarioBorrado";
    }

    @GetMapping("/editUsuario/{id}")
    public String editUsuario(Model model, @PathVariable Long id) {

        Optional<Usuario> usuario = servUsuarios.findById(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());

        }

        return "/temp_Usuario/editUsuarioPage";

    }

    @PostMapping("/editUsuario")
    public String editUsuario(Model model, Usuario usuario, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {

        //updateImage(book, removeImage, imageField);

        servUsuarios.save(usuario);

        model.addAttribute("usuarioId", usuario.getId());

        return "redirect:/Usuarios/"+usuario.getId();
    }


}

