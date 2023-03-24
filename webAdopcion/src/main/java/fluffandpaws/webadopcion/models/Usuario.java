package fluffandpaws.webadopcion.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {

    //meter un getRoles aqui
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "username", unique = true)
    private String username; //Nombre identificativo (generado o establecido)

    @Column(unique = true)
    private String email; //Correo
    //La contraseña deberá ser guardada en otra base de datos por seguridad
    //private String pass; //Contraseña
    private String dni;

    //private Integer id; //DNI
    private int edad; //Edad

    private String encodedPassword;//Salt de Hash

    @OneToMany(mappedBy="adopter", cascade = CascadeType.ALL)
    private List<Animal> animalesAdoptados;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    private String lastname;

    private String description;

    protected Usuario(){}

    public Usuario(String name, String lastname, String dni, String edad, String username, String email, String encodedPassword, String... roles) {
        PasswordEncoder passEncoder = new BCryptPasswordEncoder(10, new SecureRandom()); //Para que se pueda encriptar
        this.name = name;
        this.username = username;
        this.dni = dni;
        this.edad = Integer.parseInt(edad);
        this.lastname = lastname;
        this.email = email;
        this.encodedPassword = passEncoder.encode(encodedPassword);
        this.roles = List.of(roles);
        this.description = this.toString();
        this.animalesAdoptados = null;
    }
    //GETTERS Y SETTERS
    public String getName(){
        return this.name;
    }

    ////Estos getters son para seguridad//////////
    public List<String> getRoles() {return roles;}
    public String getEncodedPassword() {return encodedPassword;}

    ////////////////////////////////////////////////
    public void setName(String name){
        this.name = name;
    }

    public void setRoles(List<String>roles){this.roles = roles;}
    public String getUsername() {
        return username;
    }

    public void setEncodedPassword(String encodedPassword){   PasswordEncoder passEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        this.encodedPassword = passEncoder.encode(encodedPassword);}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        String info = "Nombre de Usuario: " + this.username + "\n" +
                "Nombre: " + this.name + "\n" +
                "Apellidos: " + this.lastname + "\n"+
                "Edad: " + this.edad + "\n" +
                "DNI: " + this.dni + "\n" +
                "Roles: " + this.roles + "\n";
        if(animalesAdoptados != null) {
            return info + "Familia peluda: " + this.animalesAdoptados;
        } else return info +  "Familia peluda: Aún por formar";
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDescription() {
        return this.toString();
    }

    public String getAtrDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Animal> getAnimalesAdoptados(){
        if(animalesAdoptados.isEmpty()) return null;
        return this.animalesAdoptados;
    }

    public Animal getAdoptado(Long id){
        if(!animalesAdoptados.isEmpty()){
            for(Animal animal : animalesAdoptados){
                if(Objects.equals(animal.getId(), id)){
                    return animal;
                }
            }
        }
        return null;
    }

    public void setAnimalesAdoptados(List<Animal> animales){
        this.animalesAdoptados = animales;
    }

    //Otros métodos
    public void adoptar(Animal animal){
        animalesAdoptados.add(animal);
        this.description = toString();
    }

    public void abandonar(Animal animal){ //Probablemente se use para editar usuario
        animalesAdoptados.remove(animal);
    }
}
