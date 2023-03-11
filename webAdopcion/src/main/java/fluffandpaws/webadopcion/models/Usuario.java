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
    private String sname1;  //Apellido 1
    private String sname2;  //Apellido 2

    @Column(name = "username")
    private String username; //Nombre identificativo (generado o establecido)
    private String email; //Correo
    //La contraseña deberá ser guardada en otra base de datos por seguridad
    private String pass; //Contraseña

    private String encriptado; //Salt de Hash

    //private Integer id; //DNI
    private int edad; //Edad
    private String encodedPassword;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    @OneToMany(mappedBy="adopter")
    private List<Animal> familiaUsuario;

    protected Usuario(){}

    public Usuario(String newName, String newSName1, String newSName2){
        this.name = newName;
        this.sname1 = newSName1;
        this.sname2 = newSName2;
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
        return "USER: " + this.name;
    }

    public void setEncriptado(String encriptado) {
        this.encriptado = encriptado;
    }

    //public void getEncriptado(){}
}
