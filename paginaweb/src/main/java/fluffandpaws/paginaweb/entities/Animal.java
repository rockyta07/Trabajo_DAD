package fluffandpaws.paginaweb.entities;

import org.springframework.stereotype.Indexed;
import javax.persistence.*;

@Entity
public class Animal {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_anm;

    private String name_anm;

    protected Animal(){}

    public Animal(String newName){
        this.name_anm = newName;
    }

}
