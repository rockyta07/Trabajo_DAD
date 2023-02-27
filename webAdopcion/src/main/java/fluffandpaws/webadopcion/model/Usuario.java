package fluffandpaws.webadopcion.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios") //El nombre para las tablas
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name_adp;
    @Column(nullable = false)
    private String surname;  //Apellido 1
    private String username; //Nombre identificativo (generado o establecido)
    @Column(nullable = false)
    private String email; //Correo
    //La contraseña deberá ser guardada en otra base de datos por seguridad
    private String pass; //Contraseña
    private Integer dni; //DNI
    private int edad; //Edad


    @OneToMany(mappedBy="adopter")
    private List<Animal> familiaUsuario;

    protected Usuario(){}

    public Usuario(String newName, String newSurname, String newEmail){
        this.name_adp = newName;
        this.surname = newSurname;
        this.email = newEmail;
    }


    //GETTERS Y SETTERS
    public String getName(){
        return this.name_adp;
    }

    public void setName(String name){
        this.name_adp = name;
    }

    public String getSurame(){
        return this.surname;
    }

    public void setSurname(String newSn1){
        this.surname = newSn1;
    }

    /*
    public void adopt(Animal anm){
        this.family.add(anm);
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public String getPass() {
        return pass;
    }*/

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long dni) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString(){
        return "USER: " + this.name_adp;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }
}
