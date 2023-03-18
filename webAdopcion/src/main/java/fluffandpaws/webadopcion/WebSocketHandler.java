package fluffandpaws.webadopcion;

//Esta ckase se encargara de definir como se va a encargar el servidor de manejar las conexiones websocket
//y como se comunican los usaurios con el servidor a través de este protocolo seleccionado
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    //creamos una instancia logger con el nombre websockethander usando el metodo getlogger
    //ese metodo ya viene en la clase loggerfactory
    Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    //Este metodo sera el que se encargue de manejar los mensajes que llegan al servidor, reciben la session como parametro y el mensaje de texto
    //
    @Override
    //Este metodo solo se ejecuta cuando se recibe un mensaje
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
//Esto indica si se ha recibido un mensaje y el contenido de dicho mensaje
        logger.info("Mensaje recibido:" + message.getPayload());
//Esto se encargara de enviar un mensaje de texto como respuesta, el mensaje de respuesta es el mensaje original con el prefijo Echo
        session.sendMessage(new TextMessage("Echo: "+ message.getPayload()));
    }

    @Override
    //Este metodo se ejcuta cuando la conexion websocket se cierra sin ningun motivo y te devuelve el mensaje de user no encontrado
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("El usuario no se encuentra conectado"+session.getId());
    }

    @Override
    //Se ejecuta cuando la conexion websocket se ha establecido , envia un mensaje de bienvenida
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("El usuario se encuentra conectado "+session.getId());

        session.sendMessage(new TextMessage("Hola usuario"));
    }
}