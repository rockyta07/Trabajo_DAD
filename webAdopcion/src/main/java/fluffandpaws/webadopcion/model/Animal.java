package fluffandpaws.webadopcion.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;


@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //Imagenes
    @Lob
    @JsonIgnore
    private Blob imagenAnimal;
    private boolean imagen;

    //Descripción
    @Column(columnDefinition = "TEXT")
    private String description; //Basada en la información de los atributos siguientes

    //Atributos del animal
    private String name_anm;
    private String age; //no es int porque va a almacenar si son meses o años
    private String species; //Perro, Gato, Conejo...
    private String breed; //Mastín, Labrador, Beagle...
    private String gender;
    private Date birthdate;
    private int weight; //kg
    private String size; // Pequeño, Mediano, Grande, Gigante



    //Dependencias
    @ManyToOne
    private Usuario adopter;

    @ManyToOne
    private Protectora prtOrigen;


    //Constructores
    protected Animal(){}

    //Para dar de alta un animal, se tiene que hacer como protectora e inmediatamente se le asigna la protectora al animal
    public Animal(String newName, String newSp, String newBreed,
                  String newGndr, String newBDate, int newWeight, String newSize){ //Las fechas se deben de dar tal que así: "yyyy-mm-dd"
        this.name_anm = newName;
        this.species = newSp;
        this.breed = newBreed;
        this.gender = newGndr;
        this.birthdate = Date.valueOf(newBDate); //Pasamos el string a una fecha date para mysql
        this.age = calculateAge(); //int newAge;
        this.weight = newWeight;
        this.size = newSize;
        /*
        this.adopter = null; //Mas adelante lo cambiamos
        this.prtOrigen = null; //Mas adelante lo cambiamos
        */

        this.description = setDescription();
    }


    //Funciones
    public String setDescription(){ //Descripción detallada de los atributos
        if(!species.equals("perro")){
            //si no es un perro, el tamaño nos da igual
            return (this.name_anm.toUpperCase() + " es un " + this.species + " " + this.breed + " " + this.gender +
                    " de " + this.age + ".");
        }
        return (this.name_anm.toUpperCase() + " es un " + this.species + " " + this.breed + " " + this.gender +
                " de " + this.age + " y de tamaño " + this.size + ".");
    }

    public String calculateAge(){
        LocalDate bday = this.birthdate.toLocalDate();
        Period edad = Period.between(bday, LocalDate.now());
        if(edad.getYears() < 1){
            return (edad.getMonths() + "meses");
        }
        return (edad.getYears() + " años y " + edad.getMonths() + " meses");
    }



    //Getters

    public String getName_anm(){
        return this.name_anm;
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

    public String getAge(){
        return this.age;
    }

    public Date getBirthdate(){
        return this.birthdate;
    }

    public int getWeight(){
        return this.weight;
    }

    public String getSize(){
        return this.size;
    }


    public Long getId() { return this.id;}

    //SETTERS

    public void setName_anm(String newName){
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

    public void setBirthdate(Date newBDate){
        this.birthdate = newBDate;
    }

    public void setWeight(int newWeight){
        this.weight = newWeight;
    }

    public void setSize(String newHeight){
        this.size = newHeight;
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

    public Blob getImageFile() {
        return imagenAnimal;
    }

    //Protectora y usuario adoptante
    public Protectora getPrtOrigen() {
        return prtOrigen;
    }

    public void setPrtOrigen(Protectora prtOrigen) {
        this.prtOrigen = prtOrigen;
    }

    public Usuario getAdopter() {
        return adopter;
    }

    public void setAdopter(Usuario adopter) {
        this.adopter = adopter;
    }
}
