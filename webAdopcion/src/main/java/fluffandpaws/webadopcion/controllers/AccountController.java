package fluffandpaws.webadopcion.controllers;


import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.security.WebUserDetails;
import fluffandpaws.webadopcion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UsuarioService userService;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String viewDetails (@AuthenticationPrincipal WebUserDetails loggedUser, Model model){
        String username = loggedUser.getUsername();
        Usuario usuario = userService.getByUsername(username);
        model.addAttribute("user", usuario);
        return "account";
    }

    @PostMapping("/update")
    public String saveDetails(Usuario usuario, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal WebUserDetails loggedUser){
        loggedUser.setFirstName(usuario.getName());
        loggedUser.setLastName(usuario.getLastName());
        redirectAttributes.addFlashAttribute("message", "La información ha sido actualizada.");
        return "redirect:/account";
    }

    @GetMapping("/user-info")
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        Map<String, Object> data = new HashMap<>();
        // Add data about the logged-in user to the map
        return ResponseEntity.ok(data);
    }

}
