package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<Usuario> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            Usuario usuario = userRepository.findByUsername(principal.getName());
            if(usuario == null){
                throw new UsernameNotFoundException("User not found");
            } else return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
