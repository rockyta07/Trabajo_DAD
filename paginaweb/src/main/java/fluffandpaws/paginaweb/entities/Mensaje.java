package fluffandpaws.paginaweb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Mensaje {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_msg;

    private String content;

    @OneToOne
    private Adoptante adp;

    @OneToOne
    private Protectora prt;

    protected Mensaje(){}

    public Mensaje(String newCont){
        this.content = newCont;
    }

    public String getContent(){
        return this.content;
    }

    public Adoptante getAdopter(){
        return this.adp;
    }

    public void setAdopter(Adoptante newAdp){
        this.adp = newAdp;
    }

    public Protectora getPrt(){
        return this.prt;
    }

    public void setPrt(Protectora newPrt){
        this.prt = newPrt;
    }

}
