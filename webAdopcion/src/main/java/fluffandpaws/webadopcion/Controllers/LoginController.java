package fluffandpaws.webadopcion.Controllers;

//Importante meter en el pom o agregar directamente servlet sino da error

import fluffandpaws.webadopcion.BBDD.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
//De momento no posee ningún filtro de seguridad porque no se pide
@RestController
public class LoginController {


//creamos un petodo que nos creara una identificacion unica de sesion para un usuario, esto se crea gracias A uuid que es un estandar que viene en java
   public static String generateSessionId(){

       UUID uuid = UUID.randomUUID();
       return uuid.toString();
   }
    @PostMapping("/enter")
//verificamos si lo que ha ingresado el usuario está bien para poder acceder
    public String enter(@RequestParam("username") String username,//el nombre del usuario para iniciar sesion
                         @RequestParam("password") String password,//la contraseña
                         //tanto la contraseña como el nombre de usuario será verificado en la base de datos para poder permitir entrar o no
                         HttpServletRequest request,
                         HttpServletResponse response) {

        Usuarios usuario = usuarioBase.findByUsername(username);//buscamos el nombre de usuario en la base de datos, de momento usuarioBase no existe pero se va a crear la base de datos
        if (usuario != null && usuario.getPass().equals(password)) {//en usuario hemos guardado la busqueda realizada por nombre si no se encuentra se devuelve un null

            Cookie informacion = new Cookie("identificacionSesion",generateSessionId());//método ya creado por servlet para generar identificador de sesion
            response.addCookie(informacion);//addCookie tambien es un metodo de servlet
            return "redirect:/index";
//response es la respuesta del servidor al cliente en una solicitud
        } else {

            System.out.println("No se ha podido iniciar sesión, nombre de usuario o contraseña INCORRECTA");
            return  "redirect:/enter?error";//error all entrar
        }

        //Si los credenciales son correctos se iniciará sesión, cookie es un pequeño archivo de texto que sirve para recordar información del usuario
        //Así al iniciar sesión la pagina web se adapta al usuario
    }
    //A
    // hora vamos con el cierre de sesión

    @GetMapping("/logOut")//coge la petición que hace el usuario desde la pagina web

    public String logOut(HttpSession session){//esta sesion que metemos como parámetro es la actual

        session.invalidate();//se invalida
        return"redirect:/logOut";

    }





}