package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Mensaje;
import fluffandpaws.webadopcion.BBDD.Usuario;
import fluffandpaws.webadopcion.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /*
    @PostMapping("/")//creamos el usuario
    public Usuario createUser(@RequestBody Usuario us) {

        servUsuarios.save(us);
        return us;
    }

    @PutMapping("/{id}")//para modificar usuario

    public Usuario replacePost(@PathVariable Long id, @RequestBody Usuario newUser) {

        newUser.setId(id);
        servUsuarios.replace(newUser);
        return newUser;

    }
    @DeleteMapping("/{id})")//borramos el usuario

    public Usuario deleteUsuarios(@PathVariable Long id){

        Usuario u = servUsuarios.findById(id).orElseThrow();
        servUsuarios.delete(id);
        return u;


    }
*/
}

