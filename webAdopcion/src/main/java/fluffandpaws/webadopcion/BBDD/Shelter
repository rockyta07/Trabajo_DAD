package fluffandpaws.webadopcion.BBDD;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_prt;

    private String name_prt;
    private String license;
    private String location;
    private int tlf;
    private String eMail;

    private String social1;
    private String social2;
    private String social3;

    private Integer id;
    private String webPrt;

    //private List<Animals> total;

    protected Shelter(){}

    public Shelter(String newName, String newLicense, String newLocation, int newTlf, String newEMail){
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

    public Integer getId(){ return this.id;}
    public int getTlf(){
        return this.tlf;
    }

    public String getEMail(){
        return this.eMail;
    }

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



    /*
    public ArrayList<Animal> getTotal(){
        return (ArrayList<Animal>)this.total;
    }
    */
    public void setId(Integer id){ this.id = id;}
    public void setName(String newName){
        this.name_prt = newName;
    }

    public void setLicense(String newLicense){
        this.license = newLicense;
    }

    public void setLocation(String newLocation){
        this.location = newLocation;
    }

    public void setTlf(int newTlf){
        this.tlf = newTlf;
    }

    public void setEMail(String newEMail){
        this.eMail = newEMail;
    }

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

    public void adopt(Animales anm){
        //this.total.add(anm);
    }
}
