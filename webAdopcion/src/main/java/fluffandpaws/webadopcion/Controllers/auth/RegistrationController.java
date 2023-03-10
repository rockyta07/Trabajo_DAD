package fluffandpaws.webadopcion.Controllers.auth;

import fluffandpaws.webadopcion.BBDD.Usuario;
import fluffandpaws.webadopcion.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class RegistrationController {
    @Autowired
    private UsuarioService userService;//encontrado en la carpeta service

    @GetMapping("/register")
    public String registrationForm(Model model){
        model.addAttribute("user", new Usuario("","",""));
        return "registro";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Usuario usuario, BindingResult bindingResult, Model model){
        //Validamos los datos del usuario
        if(bindingResult.hasErrors()){
            return "registro"; //O RegisterError?
        }

        //Encriptamos con hash la contraseña del usuario
        String encriptado = BCrypt.gensalt();
        String passHash = BCrypt.hashpw(usuario.getPass(), encriptado);

        //Guardamos este usuario en la base de datos
        usuario.setPass(passHash); //O creamos otro método para las contraseñas encriptadas getEncoded(), setEncoded()
        usuario.setEncriptado(encriptado);
        userService.save(usuario);

        model.addAttribute("successMessage", "¡Te has registrado correctamente!"); //Mensaje de registrado correcto
        return "registro";

    }

}
