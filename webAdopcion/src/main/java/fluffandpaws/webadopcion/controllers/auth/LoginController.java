package fluffandpaws.webadopcion.controllers.auth;


import fluffandpaws.webadopcion.security.jwt.AuthResponse;
import fluffandpaws.webadopcion.security.jwt.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fluffandpaws.webadopcion.security.jwt.UserLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserLoginService userService;//encontrado en la carpeta service


    @RequestMapping("/login")
//verificamos si lo que ha ingresado el usuario está bien para poder acceder
    public ResponseEntity<AuthResponse> login(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            @RequestBody LoginRequest loginRequest) {

        return userService.login(loginRequest, accessToken, refreshToken);
    }

    @RequestMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {

        return userService.refresh(refreshToken);
    }

    @RequestMapping("/logout")
    public ResponseEntity<AuthResponse> logOut(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, userService.logout(request, response)));
    }



}
