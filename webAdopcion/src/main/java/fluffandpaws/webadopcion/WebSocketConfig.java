package fluffandpaws.webadopcion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//Hemos ensado en usar websocket porque serviria para implementar notificaciones de tiempo real
//de nuevas mascotas disponibles, o esatdo de animales, comunicarse los usuarios con las protectoras en nuestro caso
//kafka por ejemplo no lo veiamos optimo ya que no vamos a procesar grandes cantidades de datos
//grpc tampoco la veiamos necesaria ya que solo queremos conectar a los usuarios
//Este codigo lo unico que hace es configurar websocket para una aplicacion web
//registra un websockethandler para manejar las solicitudes websocket en la ruta notifications
@Configuration
@EnableWebSocket
//Registra el webhandler definido y establece una ruta en la que estara disponible la conexion websocket
//estara en la ruta/notifications
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    WebSocketHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/notifications");
    }
}