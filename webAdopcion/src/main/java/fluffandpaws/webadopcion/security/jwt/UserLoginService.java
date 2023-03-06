package fluffandpaws.webadopcion.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {


    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest, String encryptedAccessToken, String
            encryptedRefreshToken){
        HttpHeaders responseHeaders = new HttpHeaders();// crea una instancia de la clase HttpHeaders para agregar encabezados a una respuesta HTTP
        //para encabezado personalizado
        AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.SUCCESS,
                "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
        // indica que la respuesta HTTP tiene un estado de "200 OK", lo que indica que la solicitud se ha completado correctamente.
        //El método headers() de la clase ResponseEntity se utiliza para establecer encabezados HTTP personalizados
        //se pasa un objeto que contiene los encabezados, crea un objeto authresponse con success y un mensaje de que fue todo bien
    }
    //Falta pulirlo
    public ResponseEntity<AuthResponse> refresh(String encryptedRefreshToken){

        HttpHeaders responseHeaders = new HttpHeaders();// crea una instancia de la clase HttpHeaders para agregar encabezados a una respuesta HTTP
        //para encabezado personalizado
        AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.SUCCESS,
                "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
        // indica que la respuesta HTTP tiene un estado de "200 OK", lo que indica que la solicitud se ha completado correctamente.
        //El método headers() de la clase ResponseEntity se utiliza para establecer encabezados HTTP personalizados
        //se pasa un objeto que contiene los encabezados, crea un objeto authresponse con success y un mensaje de que fue todo bien

    }

    public String logout(HttpServletRequest request, HttpServletResponse response) {

        return "Logout successfully";
    }

}
