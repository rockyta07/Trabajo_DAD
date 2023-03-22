package fluffandpaws.webadopcion.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Protectora {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name_prt;
    private String license;
    private String location;
    private String tlf;
    private String eMail;
    private String description;

    //private String social1;
    //private String social2;
    //private String social3;

    //private Integer id;
    //private String webPrt;

    @OneToMany(mappedBy="prtInstance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensaje> mensajesProtectora;

    @OneToMany(mappedBy="prtOrigen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animalesProtectora;

    //private List<Animals> total;

    protected Protectora(){}

    public Protectora(String newName, String newLicense, String newLocation, String newTlf, String newEMail){
        this.name_prt = newName;
        this.license = newLicense;
        this.location = newLocation;
        this.tlf = newTlf;
        this.eMail = newEMail;
        this.description = this.toString();
        //this.total = new ArrayList<Animals>();
    }

    //GETTERS
    public String getName(){
        return this.name_prt;
    }

    public String getLicense(){
        return this.license;
    }

    public String getLocation(){
        return this.location;
    }


    public String getTlf(){
        return this.tlf;
    }

    public String getEMail(){
        return this.eMail;
    }

    public Long getId(){ return this.id;}

    public void setId(Long id){this.id = id;}

    public void setName(String newName){
        this.name_prt = newName;
    }

    public void setLicense(String newLicense){
        this.license = newLicense;
    }

    public void setLocation(String newLocation){
        this.location = newLocation;
    }

    public void setTlf(String newTlf){
        this.tlf = newTlf;
    }

    public void setEMail(String newEMail){
        this.eMail = newEMail;
    }

    public void addAnimal(Animal a){
        this.animalesProtectora.add(a);
    }


    public void setAnimalesProtectora(List<Animal> la){
        this.animalesProtectora = la;
    }

    public List<Animal> getAnimalesProtectora(){
        return this.animalesProtectora;
    }

    public List<Mensaje> getMensajesProtectora(){
        return this.mensajesProtectora;
    }

    public void setMensajesProtectora(List<Mensaje> lm){
        this.mensajesProtectora = lm;
    }

    @Override
    public String toString(){
        return "La protectora \"" + this.name_prt + "\" con número de licencia " + this.license + " se encuentra situada en " + this.location + ", se puede contactar llamando al " + this.tlf + " o escribiendo a " + this.eMail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
