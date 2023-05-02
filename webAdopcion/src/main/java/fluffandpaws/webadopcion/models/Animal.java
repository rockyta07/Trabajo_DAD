package fluffandpaws.webadopcion.models;

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
    private String size;   //cm
    private String description;

    //Para el servicio interno
    //Los Json ignore nos evita entrar a un objeto que sea null y que de error al entrar a la cache
    @JsonIgnore
    private String cuerpoCertificado;



    @ManyToOne
    @JsonIgnore
    private Usuario adopter;


    @ManyToOne
    @JsonIgnore
    private Protectora prtOrigen;
    protected Animal(){}

    public Animal(String newName, String newAge, String newSp, String newBreed,
                  String newGndr, String newBDate, String newWeight, String newSize){
        this.name_anm = newName;
        this.age = newAge;
        this.species = newSp;
        this.breed = newBreed;
        this.gender = newGndr;
        this.birthdate = newBDate;
        this.weight = newWeight;
        this.size = newSize;
        this.adopter = null;
        this.description = this.toString();
        this.prtOrigen = null;
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

    public String getSize(){
        return this.size;
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

    public void setSize(String newSize){
        this.size = newSize;
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
        return this.name_anm + " es un " + this.species + " " + this.gender + " de " + this.age + ", tamaño " + this.size + " y raza " + this.breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Usuario getAdopter() {
        return adopter;
    }

    public void setAdopter(Usuario adopter) {
        this.adopter = adopter;
    }

    public String getCuerpoCertificado() {
        this.cuerpoCertificado = "¡Hola, " + this.adopter.getName() + "! \n ¡Tenemos buenas noticias! Has concluido el proceso de adopción para " + this.name_anm +
                ".\n Tu nuevo amigo peludito se encuentra en la protectora \"" +  prtOrigen.getName() + "\" situada en: " + prtOrigen.getLocation() +
                "Puedes pasar a recogerle de la protectora, los voluntarios te ayudarán con lo que necesites. \n\n" +
                "------------------------------------------------------------------------------------------ \n" +
                "| El certificado de adopción es el siguiente: \n" +
                "------------------------------------------------------------------------------------------ \n" +
                "| Nombre: " + this.name_anm +
                "\n| Especie: " + this.species +
                "\n| Raza: " + this.breed +
                "\n| Sexo: " + this.gender +
                "\n| Fecha de nacimiento (estimada): " + this.birthdate +
                "\n| Protectora: " + this.prtOrigen.getName() +
                "\n| Vacunado: Si (de todas las vacunas)" +
                "\n| Castrado: No (la castración corre a cuenta del adoptante y es obligatoria para todos los perros mayores de 9 meses)" +
                "\n| Tamaño: " + this.size +
                "\n| Peso actual: " + this.weight +

                "\n------------------------------------------------------------------------------------------ \n" +
                "\n\n ¡Os deseamos una muy feliz aventura!";
        return this.cuerpoCertificado;
    }

    public void setCuerpoCertificado(String cuerpoCertificado) {
        this.cuerpoCertificado = cuerpoCertificado;
    }
}
