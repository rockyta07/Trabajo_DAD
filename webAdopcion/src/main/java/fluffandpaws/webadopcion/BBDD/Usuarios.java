package fluffandpaws.webadopcion.BBDD;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuarios {
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
    //private Integer id; //DNI
    private int edad; //Edad


    @OneToMany(mappedBy = ("adopter"))
    private List<Animales> family;

    protected Usuarios(){}

    public Usuarios (String newName, String newSName1, String newSName2){
        this.name_adp = newName;
        this.sname_adp1 = newSName1;
        this.sname_adp2 = newSName2;
        family = new ArrayList<Animales>();
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

    public void adopt(Animales anm){
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

    public long getId() {
        return id_adp;
    }

    public void setId(Long dni) {
        this.id_adp = id_adp;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
