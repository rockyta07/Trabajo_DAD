package fluffandpaws.webadopcion.BBDD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Animales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String raza;

    public Animales(Long id,String nombre, String raza){

       this.id = id;
       this.nombre = nombre;
       this.raza = raza;

    }
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
