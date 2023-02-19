/*
package fluffandpaws.webadopcion.BBDD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; //Nombre y apellidos
    private String username; //Nombre identificativo (generado o establecido)
    private String email; //Correo
    private String pass; //Contraseña
    private String dni; //DNI
    private int edad; //Edad

    public Users(){//constructor vacío

    }

    public Users(String name, String username, String email, String pass){//constructor con nombre,identificacion y email
        this.name = name;
        this.username = username;
        this.email = email;
        this.pass = pass;
    }

    ////////////// Getters & Setters //////////////
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
*/