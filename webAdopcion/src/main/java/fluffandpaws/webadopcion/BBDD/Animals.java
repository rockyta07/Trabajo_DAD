/*
package fluffandpaws.webadopcion.BBDD;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Animals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_anm;

    private String name_anm;
    private short age;

    private Integer id;
    private String species;
    private String breed;
    private String gender;
    private LocalDate birthdate;
    private short weight;
    private short height;   //cm



    @OneToOne
    private Users adopter;
    protected Animals(){}

    public Animals(String newName, short newAge, String newSp, String newBreed,
                   String newGndr, LocalDate newBDate, short newWeight, short newHeight, Integer Id){
        this.name_anm = newName;
        this.age = newAge;
        this.species = newSp;
        this.breed = newBreed;
        this.gender = newGndr;
        this.birthdate = newBDate;
        this.weight = newWeight;
        this.height = newHeight;
        this.adopter = null;
        this.id = Id;
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

    public LocalDate getBirthdate(){
        return this.birthdate;
    }

    public short getWeight(){
        return this.weight;
    }

    public short getHeight(){
        return this.height;
    }

    public Users getAdopter(){
        return this.adopter;
    }

    public Integer getId() { return this.id;}

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

    public void setBirthdate(LocalDate newBDate){
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

    public void setId(Integer id){this.id = id;}

    public void setAdopter(Users adp){
        this.adopter = adp;
    }
}
*/
