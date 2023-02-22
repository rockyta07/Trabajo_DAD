package fluffandpaws.webadopcion.BBDD;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;


@Entity
public class Animales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_anm;

    private String name_anm;
    private short age;

    //private Integer id;
    private String species;
    private String breed;
    private String gender;
    private String birthdate;
    private short weight;
    private short height;   //cm



    @ManyToOne
    private Usuarios adopter;

    @ManyToOne
    private Shelter shel;
    protected Animales(){}

    public Animales(String newName, short newAge, String newSp, String newBreed,
                   String newGndr, String newBDate, short newWeight, short newHeight,Integer Id){
        this.name_anm = newName;
        this.age = newAge;
        this.species = newSp;
        this.breed = newBreed;
        this.gender = newGndr;
        this.birthdate = newBDate;
        this.weight = newWeight;
        this.height = newHeight;
        this.adopter = null;
        this.id_anm = Id;
    }

    //Getters

    public String getName(){
        return this.name_anm;
    }

    public short getAge(){
        return this.age;
    }

    public String getSpecies(){
        return this.species;
    }

    public String getBreed(){
        return this.breed;
    }

    public String getGender(){
        return this.gender;
    }

    public String getBirthdate(){
        return this.birthdate;
    }

    public short getWeight(){
        return this.weight;
    }

    public short getHeight(){
        return this.height;
    }

    public Usuarios getAdopter(){
        return this.adopter;
    }

    public long getId() { return this.id_anm;}

    //SETTERS

    public void setName(String newName){
        this.name_anm = newName;
    }


    public void setAge(short newAge){
        this.age = newAge;
    }

    public void setAge(int newAge){
        this.age = (short)newAge;
    }

    public void setSpecies(String newSpecies){
        this.species = newSpecies;
    }

    public void setBreed(String newBreed){
        this.breed = newBreed;
    }

    public void setGender(String newGender){
        this.gender = newGender;
    }

    public void setBirthdate(String newBDate){
        this.birthdate = newBDate;
    }

    public void setWeight(short newWeight){
        this.weight = newWeight;
    }

    public void setWeight(int newWeight){
        this.weight = (short)newWeight;
    }

    public void setHeight(short newHeight){
        this.height = newHeight;
    }

    public void setHeight(int newHeight){
        this.height = (short)newHeight;
    }

    public void setId(Integer id){this.id_anm = id;}

    public void setAdopter(Usuarios adp){
        this.adopter = adp;
    }
}
