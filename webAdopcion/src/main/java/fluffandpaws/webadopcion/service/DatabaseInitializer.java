package fluffandpaws.webadopcion.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import fluffandpaws.webadopcion.models.Protectora;
import fluffandpaws.webadopcion.models.Animal;
import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.models.Mensaje;
import fluffandpaws.webadopcion.repositories.ProtectoraRepository;
import fluffandpaws.webadopcion.repositories.AnimalRepository;
import fluffandpaws.webadopcion.repositories.UsuarioRepository;
import fluffandpaws.webadopcion.repositories.MensajeRepository;


import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class DatabaseInitializer {

    @Autowired
    private ProtectoraRepository protectoraRepo;
    @Autowired
    private AnimalRepository animalRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private MensajeRepository mensajeRepo;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {

        Protectora p1, p2, p3;
        p1 = new Protectora("Protectora 1","12312312","Madrid","67584637","Protectora1@gmail.com");//ponemos los datos tal cual el constructor
        p2 = new Protectora("Protectora 2","45645645","Valencia","680475243","Protectora2@gmail.com");
        p3 = new Protectora("Protectora 3","6767676","Madrid","692734123","Protectora3@gmail.com");

        //p1.setAnimalesProtectora(la_aux);

        protectoraRepo.save(p1);
        protectoraRepo.save(p2);
        protectoraRepo.save(p3);

        ArrayList<Usuario> usuariosACrear = new ArrayList<>();
        //Usuario(String name, String lastname, String dni, String username, String encodedPassword, String... roles)
        usuariosACrear.add(new Usuario("Juan", "Rodríguez",  "45484545Y", "30", "Juan","juanito@gmail.com", "juan123", "USER"));
        usuariosACrear.add(new Usuario("Pablo", "Leon", "5464554A", "19", "Pablo", "elLeon@hotmail.com","pablo123", "USER"));
        usuariosACrear.add(new Usuario("user", "", "", "0","user","user@user","pass", "USER"));
        usuariosACrear.add(new Usuario("admin","","", "0","admin", "admin@admin","adminpass", "USER", "ADMIN"));

        for(Usuario usuario : usuariosACrear){
            if (usuarioRepo.findByUsername(usuario.getUsername()) == null){
                usuarioRepo.save(usuario);
            }
        }
        /*
        usuarioRepo.save(new Usuario("Juan", "Rodríguez",  "45484545Y", "30", "Juan","juanito@gmail.com", "juan123", "USER"));
        usuarioRepo.save(new Usuario("Pablo", "Leon", "5464554A", "19", "Pablo", "elLeon@hotmail.com","pablo123", "USER"));
        usuarioRepo.save(new Usuario("user", "", "", "0","user","user@user","pass", "USER"));
        usuarioRepo.save(new Usuario("admin","","", "0","admin", "admin@admin","adminpass", "USER", "ADMIN"));
        */

        Mensaje m1, m2, m3, m4;
        m1 = new Mensaje("juanito@gmail.com","hola buenas", p1);
        m2 = new Mensaje("caravaggio@gmail.com","Quiero pedir información sobre un animal", p3);
        m3 = new Mensaje("superman9@gmail.com","Me gustaría visitar la protectora", p3);
        m4 = new Mensaje("alo_oficial@gmail.com","Contacto para adoptar uno de vuestros animales", p2);

        mensajeRepo.save(m1);
        mensajeRepo.save(m2);
        mensajeRepo.save(m3);
        mensajeRepo.save(m4);

        Animal a1, a2, a3, a4, a5;
        a1 = new Animal("Popi", "3 meses","Perro","Mestizo con boxer","Macho","6/12/2022",  "5",  "Mediano");
        a2 = new Animal("Luna", "4 meses","Perro","Mestizo","Hembra","5/07/2022",  "3",  "Pequeño");
        a3 = new Animal("Braco", "8 meses","Perro","Shiba","Macho","10/03/2017",  "12",  "Mediano");
        a4 = new Animal("Nino", "5 años","Perro","Mestizo","Macho","10/03/2017",  "10",  "Mediano");
        a5 = new Animal("Pryna", "2 años","Perro","Mestizo con mastín","Hembra","10/03/2017",  "40",  "Grande");


        setAnimalImage(a1,"/imagenesAnimales/perro1.png");
        setAnimalImage(a2,"/imagenesAnimales/perro2.png");
        setAnimalImage(a3,"/imagenesAnimales/perro3.png");
        setAnimalImage(a4, "/imagenesAnimales/nino.png");
        setAnimalImage(a5, "/imagenesAnimales/pryna.png");
        a1.setPrtOrigen(p3);
        a2.setPrtOrigen(p1);
        a3.setPrtOrigen(p2);
        a4.setPrtOrigen(p2);
        a5.setPrtOrigen(p3);

        animalRepo.save(a1);
        animalRepo.save(a2);
        animalRepo.save(a3);
        animalRepo.save(a4);
        animalRepo.save(a5);

    }

    public void setAnimalImage(Animal aux, String classpathResource) throws IOException {
        aux.setImagen(true);
        Resource image = new ClassPathResource(classpathResource);
        aux.setImagenanimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

}
