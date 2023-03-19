package fluffandpaws.webadopcion.models;

import javax.persistence.*;

import java.util.List;

@Entity
public class Usuario {

    //meter un getRoles aqui
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "username")
    private String username; //Nombre identificativo (generado o establecido)
    private String email; //Correo
    //La contraseña deberá ser guardada en otra base de datos por seguridad
    private String pass; //Contraseña

    //private Integer id; //DNI
    private int edad; //Edad
    private String encodedPassword;//Salt de Hash

    @OneToMany(mappedBy="adopter")
    private List<Animal> animalesAdoptados;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    private String lastname;

    protected Usuario(){}

    public Usuario(String name, String username, String encodedPassword, String... roles) {
        this.name = name;
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.roles = List.of(roles);
    }
    //GETTERS Y SETTERS
    public String getName(){
        return this.name;
    }
    ////Estos getters son para seguridad//////////
    public List<String> getRoles() {return roles;}
    public String getEncodedPassword() {return encodedPassword;}
    //////////////////////////////////////////////77
    public void setName(String name){
        this.name = name;
    }

    public void setRoles(List<String>roles){this.roles = roles;}
    public String getUsername() {
        return username;
    }

    public void setEncodedPassword(String encodedPassword){this.encodedPassword = encodedPassword;}

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
        return "USER: " + this.name;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastname (String lastname) {
        this.lastname = lastname;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
