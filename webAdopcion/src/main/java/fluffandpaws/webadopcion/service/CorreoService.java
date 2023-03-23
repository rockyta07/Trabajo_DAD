package fluffandpaws.webadopcion.service;

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


    public record EmailRequest(String to, String subject, String body) {
    }

    //Envio una solicitud de correo electronico a la url de la api de correo electronico utilizando
    //un objeto restTemplate, el async sirve para ejecutarse en un hilo separado y bloq el principal
    @Async
    void sendMail(EmailRequest request, URI url) {
        var restTemplate = new RestTemplate();
        var head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);

        var reqEntity = new HttpEntity<>(request, head);

        restTemplate.postForEntity(url, reqEntity, EmailRequest.class);
    }


}