package fluffandpaws.paginaweb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Adoptante {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_adp;

    private String name_adp;
    private String sname_adp1;  //Apellido 1
    private String sname_adp2;  //Apellido 2

    @OneToMany
    private List<Animal> family;

    protected Adoptante(){}

    public Adoptante(String newName, String newSName1, String newSName2){
        this.name_adp = newName;
        this.sname_adp1 = newSName1;
        this.sname_adp2 = newSName2;
        family = new ArrayList<Animal>();
    }

    public String getName(){
        return this.name_adp;
    }

    public String getSName1(){
        return this.sname_adp1;
    }

    public String getSName2(){
        return this.sname_adp2;
    }

    public void adopt(Animal anm){
        this.family.add(anm);
    }

    

}
