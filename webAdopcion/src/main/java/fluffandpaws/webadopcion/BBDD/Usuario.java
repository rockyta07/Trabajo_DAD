package fluffandpaws.webadopcion.BBDD;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name_adp;
    private String sname1;  //Apellido 1
    private String sname2;  //Apellido 2
    private String username; //Nombre identificativo (generado o establecido)
    private String email; //Correo
    //La contraseña deberá ser guardada en otra base de datos por seguridad
    private String pass; //Contraseña
    //private Integer id; //DNI
    private int edad; //Edad


    @OneToMany(mappedBy="adopter")
    private List<Animal> familiaUsuario;

    protected Usuario(){}

    public Usuario(String newName, String newSName1, String newSName2){
        this.name_adp = newName;
        this.sname1 = newSName1;
        this.sname2 = newSName2;
    }


    //GETTERS Y SETTERS
    public String getName(){
        return this.name_adp;
    }

    public void setName(String name){
        this.name_adp = name;
    }

    public String getSName1(){
        return this.sname1;
    }

    public String getSName2(){
        return this.sname2;
    }

    public void setSname1(String newSn1){
        this.sname1 = newSn1;
    }
    public void setSname2(String newSn2){
        this.sname2 = newSn2;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

   public void setId(Long id) {
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
}
