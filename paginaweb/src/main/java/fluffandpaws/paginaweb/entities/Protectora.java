package fluffandpaws.paginaweb.entities;

import org.springframework.stereotype.Indexed;
import javax.persistence.*;

@Entity
public class Protectora {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_prt;

    private String name_prt;

    protected Protectora(){}

    public Protectora(String newName){
        this.name_prt = newName;
    }
}
