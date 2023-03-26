package fluffandpaws.webadopcionemailservice.service;

import fluffandpaws.webadopcionemailservice.model.AdoptionCertificate;
//import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;


@Service
public class AdoptionService {
    //TODO creo que lo de los valores era para ponerlo por pantalla sin definirlo en el código
    @Value("${email.user}") //poner aqui directamente el correo que se va a usar para mandar el mensaje
    private String user;

    @Value("${email.password}")//esto se incluye en el properties con la contraseña de aplicacion generada porque sino te da error ya que hay mucha seguridad en gmail
    private String password;

    private final Session session;
    private Transport transport;

    public AdoptionService() throws MessagingException {
        //TODO Cambiar esto a properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); //Para usar gmail como servidor smtp
        properties.put("mail.smtp.starttls.enable", "true"); //Para usar TLS
        properties.put("mail.smtp.auth", true); //Para activar la autenticación
        properties.put("mail.smtp.port", 587); //El puerto activado para mandar mensajes
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //Para usar la lista de confianza ssl de gmail
        session = Session.getDefaultInstance(properties);
    }
//En este método despues del to es importante poner el new para establecer el campo to del mensaje en la direccion de correo electronico proporcionada
    public void sendTestMessage(AdoptionCertificate adoptionCertificate){
        if(transport == null){//importante aqui quitar el distinto de null ya que saltaban errores porque no estaba inicializado
            try{

                transport = session.getTransport("smtp"); //Usaremos smtp para enviar emails
                transport.connect("smtp.gmail.com", user, password); ///Nos conectamos al servidor smtp con nuestro usuario y contraseña
                System.out.printf(user);
                System.out.printf(password);
            } catch (Exception exception){
                System.out.println("No se ha podido conectar al servidor smtp");
                exception.printStackTrace();
                exception.getCause();
                //Si no se ha podido establecer esta conexión salimos del método, ya que al capturar el error el proceso puede continuar
                return;
            }
        }
//En setText también es importante que aparezca como lo hemos puesto ya que establece el cuerpo del mensaje en el texto proporcionado en el objeto
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adoptionCertificate.getAddress())); //Esto lo recibes del otro servidor -> hay que implementar ese método: adoptionCertificate.getEmailAdress()
            message.setSubject("¡Enhorabuena,adopción aprobada!");
            message.setText(adoptionCertificate.getBody());
            transport.sendMessage(message, message.getAllRecipients()); //Una vez inicializados los valores del mensaje lo enviamos a todos los recipientes
        } catch (MessagingException exception){
            System.out.println("Ha ocurrido un error al enviar el mensaje");
            exception.printStackTrace();
            exception.getCause();
        }
    }
}
