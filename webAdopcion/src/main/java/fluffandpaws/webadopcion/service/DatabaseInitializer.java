package fluffandpaws.webadopcion.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {


        List<Animal> la_aux = new ArrayList<Animal>();
        List<Mensaje> lm_aux = new ArrayList<Mensaje>();

        Protectora p1, p2, p3;
        p1 = new Protectora("Protectora 1","12312312","Madrid","67584637","Protectora1@gmail.com");//ponemos los datos tal cual el constructor
        p2 = new Protectora("Protectora 2","45645645","Valencia","680475243","Protectora2@gmail.com");
        p3 = new Protectora("Protectora 3","6767676","Madrid","692734123","Protectora2@gmail.com");

        //p1.setAnimalesProtectora(la_aux);

        protectoraRepo.save(p1);
        protectoraRepo.save(p2);
        protectoraRepo.save(p3);

        Animal a1, a2, a3;
        a1 = new Animal("popi", "2","perro","Sin raza","macho","6/12/2022",  "23",  "14");
        a2 = new Animal("luna", "3","perro","Sin raza","hembra","5/07/2022",  "23",  "14");
        a3 = new Animal("braco", "4","perro","Sin raza","macho","10/03/2017",  "23",  "14");

        setAnimalImage(a1,"/imagenesAnimales/perro1.png");
        setAnimalImage(a2,"/imagenesAnimales/perro2.png");
        setAnimalImage(a3,"/imagenesAnimales/perro3.png");
        a1.setPrtOrigen(p3);
        a2.setPrtOrigen(p1);
        a3.setPrtOrigen(p2);

        animalRepo.save(a1);
        animalRepo.save(a2);
        animalRepo.save(a3);

        Usuario u1, u2, u3,u4,u5;
        u1 = new Usuario("Juan","Garc??a","Rodriguez");
        u1.setEncodedPassword(passwordEncoder.encode("123"));
        ArrayList<String>roles1 = new ArrayList<>();
        roles1.add("USER");
        u1.setRoles(roles1);

        u2 = new Usuario("Pablo","Gil","P??rez");
        u2.setEncodedPassword(passwordEncoder.encode("123"));
        ArrayList<String>roles2 = new ArrayList<>();
        roles1.add("USER");
        u1.setRoles(roles2);

        u3 = new Usuario("Javier","Fernandez","Arribas");
        u3.setEncodedPassword(passwordEncoder.encode("123"));
        ArrayList<String>roles3 = new ArrayList<>();
        roles3.add("USER");
        roles3.add("ADMIN");
        u3.setRoles(roles3);

        usuarioRepo.save(u1);
        usuarioRepo.save(u2);
        usuarioRepo.save(u3);



        Mensaje m1, m2, m3, m4;
        m1 = new Mensaje("pepe@gmail.com","hola buenas");
        m2 = new Mensaje("caravaggio@gmail.com","Quiero pedir informaci??n sobre un animal");
        m3 = new Mensaje("dani909@gmail.com","Me gustar??a visitar la protectora");
        m4 = new Mensaje("alo_oficial@gmail.com","Contacto para adoptar uno de vuestros animales");

        mensajeRepo.save(m1);
        mensajeRepo.save(m2);
        mensajeRepo.save(m3);
        mensajeRepo.save(m4);

    }

    public void setAnimalImage(Animal aux, String classpathResource) throws IOException {
        aux.setImagen(true);
        Resource image = new ClassPathResource(classpathResource);
        aux.setImagenanimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

}
