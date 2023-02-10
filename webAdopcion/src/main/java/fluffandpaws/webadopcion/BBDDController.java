package fluffandpaws.webadopcion;

import fluffandpaws.webadopcion.BBDD.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import fluffandpaws.webadopcion.BBDD.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BBDDController {
    @Autowired
    private UserRepository userRepository;

    //Definimos el POST y el GET
    @PostMapping
    public @ResponseBody String addNewUser(@RequestParam String username,
                                           @RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String pass,
                                           @RequestParam int edad,
                                           @RequestParam String dni){
        //Creamos un usuario auxiliar e iniciamos sus atributos
        Usuarios u = new Usuarios();
        u.setUsername(username);
        u.setName(name);
        u.setEmail(email);
        u.setPass(pass);
        u.setDni(dni);
        u.setEdad(edad);

        //Guardamos el usuario
        userRepository.save(u);
        return "Usuario añadido correctamente";
    }

    @GetMapping(path="/usuarios")
    public @ResponseBody Iterable<Usuarios> getAllUsers(){
        //Devuelve un JSON
        return userRepository.findAll();
    }

}
