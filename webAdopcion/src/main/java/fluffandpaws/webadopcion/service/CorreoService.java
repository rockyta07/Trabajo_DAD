package fluffandpaws.webadopcion.service;

import fluffandpaws.webadopcion.models.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

//import static io.github.nationalaudience.thetribunal.constant.InternalServicesStaticValues.URL_FOLLOW_BY_USER_MAIL;
//meter la url que quieras
@Service
@EnableAsync
public class CorreoService {

 //  @Value("${service.email}")
    private String service;

//Este método se encarga de encapsular los detalles del correo
    public record EmailRequest(String to, String subject, String body) {
    }
//Tomamos tres argumentos en este metodo,se utiliza la url base y la ruta que se especifica
    public void sendAdoptionRequestMail(Animal animal, String destinatario)
            throws URISyntaxException {
        var url = new URI(service + "/adoptionRequest");
        String subject=("Adopción aprobada para: " + animal.getName());
        var adoptionCertificateRequest = new EmailRequest(
                destinatario,
                subject,
                animal.getCuerpoCertificado()
        );
        sendAdoptionRequest(adoptionCertificateRequest, url);//llamamos a metodo con el objetico emailrequest y la url que se ha creado
    }

    //Envio una solicitud de correo electronico a la url de la api de correo electronico utilizando
    //un objeto restTemplate, el async sirve para ejecutarse en un hilo separado y bloq el principal
    @Async
    void sendAdoptionRequest(EmailRequest request, URI url) {
        var restTemplate = new RestTemplate();
        var head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(request, head);
        restTemplate.postForEntity(url, httpEntity, EmailRequest.class);
    }


}