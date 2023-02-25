package fluffandpaws.webadopcion.BBDD;

import java.util.List;

import jakarta.persistence.*;

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

    //private String social1;
    //private String social2;
    //private String social3;

    //private Integer id;
    //private String webPrt;

    @OneToMany(mappedBy="prtInstance")
    private List<Mensaje> mensajesProtectora;

    @OneToMany(mappedBy="prtOrigen")
    private List<Animal> animalesProtectora;

    //private List<Animals> total;

    protected Protectora(){}

    public Protectora(String newName, String newLicense, String newLocation, String newTlf, String newEMail){
        this.name_prt = newName;
        this.license = newLicense;
        this.location = newLocation;
        this.tlf = newTlf;
        this.eMail = newEMail;
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

    public Long getId(){ return this.id;}
    public String getTlf(){
        return this.tlf;
    }

    public String getEMail(){
        return this.eMail;
    }

    /*
    public String getSocial1(){
        return this.social1;
    }

    public String getSocial2(){
        return this.social2;
    }

    public String getSocial3(){
        return this.social3;
    }

    public String getWeb(){
        return this.webPrt;
    }
*/


    /*
    public ArrayList<Animal> getTotal(){
        return (ArrayList<Animal>)this.total;
    }
    */
    public void setId(Long id){ this.id = id;}

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

    /*
    public void setSocial1(String s1){
        this.social1 = s1;
    }

    public void setSocial2(String s2){
        this.social2 = s2;
    }

    public void setSocial3(String s3){
        this.social3 = s3;
    }

    public void setWeb(String newWeb){
        this.webPrt = newWeb;
    }

    public void adopt(Animal anm){
        //this.total.add(anm);
    }
*/
    @Override
    public String toString(){
        return "" + this.name_prt + " " + this.location + " " + this.tlf;
    }
}
