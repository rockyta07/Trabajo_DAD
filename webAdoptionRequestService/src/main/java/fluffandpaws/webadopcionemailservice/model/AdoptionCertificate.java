package fluffandpaws.webadopcionemailservice.model;

public class AdoptionCertificate {

    //Esta clase declara los campos que van a componer el resultado de la petición de adopción

    ///////////// Atributos /////////////

    private String address;
    private String subject;
    private String body;

    ///////////// Constructor /////////////

    public AdoptionCertificate(String address, String subject, String body){
        this.address = address;
        this.subject = subject;
        this.body = body;
    }

    ///////////// Métodos /////////////

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
