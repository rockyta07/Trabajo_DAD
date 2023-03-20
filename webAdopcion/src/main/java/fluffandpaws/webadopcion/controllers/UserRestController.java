package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            return ResponseEntity.ok(userRepository.findByUsername(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
