package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Usuarios;
import fluffandpaws.webadopcion.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UserController {
    @Autowired
    private userService user;

//creamos un petodo que nos creara una identificacion unica de sesion para un usuario, esto se crea gracias A uuid que es un estandar que viene en java

    @GetMapping("/")//buscamos todos los usuarios
    public List<Usuarios> getUsuarios() {

        return (List<Usuarios>) user.findAll();
    }

    @GetMapping("/{id}")//Esto nos retorna el usuario
    public Usuarios getUsuarios(@PathVariable long id) {

        return user.findById((int) id).orElseThrow();
    }

    @PostMapping("/")//creamos el usuario
    public Usuarios createUser(@RequestBody Usuarios us) {

        user.save(us);
        return us;
    }

    @PutMapping("/{id}")//para modificar usuario

    public Usuarios replacePost(@PathVariable long id, @RequestBody Usuarios newUser) {

        newUser.setId(id);
        user.replace(newUser);
        return newUser;

    }
    @DeleteMapping("/{id})")//borramos el usuario

    public Usuarios deleteUsuarios(@PathVariable long id){

        Usuarios u = user.findById(Math.toIntExact(id)).orElseThrow();
        user.deleteById(Math.toIntExact(id));
        return u;


    }

}

