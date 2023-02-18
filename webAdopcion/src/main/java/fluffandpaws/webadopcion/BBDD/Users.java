package fluffandpaws.webadopcion.BBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_adp;

    private String name_adp;
    private String sname_adp1;  //Apellido 1
    private String sname_adp2;  //Apellido 2
    private String username; //Nombre identificativo (generado o establecido)
    private String email; //Correo
    //La contraseña deberá ser guardada en otra base de datos por seguridad
    private String pass; //Contraseña
    private String dni; //DNI
    private int edad; //Edad


    @OneToMany
    private List<Animals> family;

    protected Users(){}

    public Users (String newName, String newSName1, String newSName2){
        this.name_adp = newName;
        this.sname_adp1 = newSName1;
        this.sname_adp2 = newSName2;
        family = new ArrayList<Animals>();
    }


    //GETTERS Y SETTERS
    public String getName(){
        return this.name_adp;
    }

    public void setName(String name){
        this.name_adp = name;
    }

    public String getSName1(){
        return this.sname_adp1;
    }

    public String getSName2(){
        return this.sname_adp2;
    }

    public void adopt(Animals anm){
        this.family.add(anm);
    }

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
