package fluffandpaws.webadopcionemailservice.controller;

import fluffandpaws.webadopcionemailservice.model.AdoptionCertificate;
import fluffandpaws.webadopcionemailservice.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdoptionController {
    // Para recoger los requests de email
    public record AdoptionRequest(String to, String subject, String body){

    }

    @Autowired
    AdoptionService adoptionService;

    @PostMapping("/peticionAdopcion")
    public void sendResponse (@RequestBody AdoptionRequest request){
        adoptionService.sendTestMessage(new AdoptionCertificate(request.to, request.subject, request.body));
    }

}
