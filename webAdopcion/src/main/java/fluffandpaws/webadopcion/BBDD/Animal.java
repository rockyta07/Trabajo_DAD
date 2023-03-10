package fluffandpaws.webadopcion.BBDD;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.sql.Blob;


@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //@JsonIgnore
    @Lob
    @JsonIgnore
    private Blob imagenAnimal;
    private boolean imagen;

    private String name_anm;
    private String age;
    private String species;
    private String breed;
    private String gender;
    private String birthdate;
    private String weight;
    private String height;   //cm



    @ManyToOne
    private Usuario adopter;

    @ManyToOne
    private Protectora prtOrigen;
    protected Animal(){}

    public Animal(String newName, String newAge, String newSp, String newBreed,
                  String newGndr, String newBDate, String newWeight, String newHeight){
        this.name_anm = newName;
        this.age = newAge;
        this.species = newSp;
        this.breed = newBreed;
        this.gender = newGndr;
        this.birthdate = newBDate;
        this.weight = newWeight;
        this.height = newHeight;
        this.adopter = null;
    }

    //Getters

    public String getName(){
        return this.name_anm;
    }

    public String getAge(){
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

    public String getWeight(){
        return this.weight;
    }

    public String getHeight(){
        return this.height;
    }


    public Long getId() { return this.id;}

    //SETTERS

    public void setName(String newName){
        this.name_anm = newName;
    }


    public void setAge(String newAge){
        this.age = newAge;
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

    public void setWeight(String newWeight){
        this.weight = newWeight;
    }

    public void setHeight(String newHeight){
        this.height = newHeight;
    }

    public void setId(Long id){this.id = id;}

    public Blob getImagenAnimal() {
        return imagenAnimal;
    }

    public void setImagenanimal(Blob image) {
        this.imagenAnimal = image;
    }

    public boolean getImagen(){
        return this.imagen;
    }

    public void setImagen(boolean image){
        this.imagen = image;
    }

    public Protectora getPrtOrigen(){
        return this.prtOrigen;
    }

    public void setPrtOrigen(Protectora p){
        this.prtOrigen = p;
    }



    @Override
    public String toString(){
        return "" + this.name_anm + ": " + this.species + " Edad: " + this.age + " Raza: " + this.breed;
    }
}
