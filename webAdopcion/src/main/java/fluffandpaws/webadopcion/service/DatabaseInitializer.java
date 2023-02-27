//package fluffandpaws.webadopcion.service;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//
//import fluffandpaws.webadopcion.model.Animal;
//import fluffandpaws.webadopcion.repositories.AnimalRepository;
//import fluffandpaws.webadopcion.repositories.MensajeRepository;
//import fluffandpaws.webadopcion.repositories.ProtectoraRepository;
//import fluffandpaws.webadopcion.repositories.UsuarioRepository;
//import jakarta.annotation.PostConstruct;
//
//import org.hibernate.engine.jdbc.BlobProxy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//@Service
//public class DatabaseInitializer {
//
//        AnimalService animalService;
//
//        @PostConstruct
//        public void init() throws IOException, URISyntaxException {
//
//            Protectora p1, p2, p3;
//            p1 = new Protectora("Protectora 1","12312312","Madrid","67584637","Protectora1@gmail.com");//ponemos los datos tal cual el constructor
//            p2 = new Protectora("Protectora 2","45645645","Valencia","680475243","Protectora2@gmail.com");
//            p3 = new Protectora("Protectora 3","6767676","Madrid","692734123","Protectora2@gmail.com");
//
//            //p1.setAnimalesProtectora(la_aux);
//
//
//            Animal a1, a2, a3;
//            a1 = new Animal("popi", "perro","Beagle","macho","2022-12-06",  16,  "Mediano");
//            a2 = new Animal("luna", "perro","Mestizo","hembra","2022-07-05",  23,  "Mediano");
//            a3 = new Animal("braco", "perro","Mastin","macho","2017-03-10",  35,  "Grande");
//
//            setAnimalImage(a1,"/imagenesAnimales/perro1.png");
//            setAnimalImage(a2,"/imagenesAnimales/perro2.png");
//            setAnimalImage(a3,"/imagenesAnimales/perro3.png");
//            save(a1);//ponemos los datos tal cual el constructor
//            save(a2);
//            save(a3);
//
//
//            Usuario u1, u2, u3;
//            u1 = new Usuario("Juan","García","Rodriguez");
//            u2 = new Usuario("Pablo","Gil","Trader");
//            u3 = new Usuario("Javier","Fernandez","Arribas");
//
//            Mensaje m1, m2, m3, m4;
//            m1 = new Mensaje("dd@gmail.com","hola buenas");
//            m2 = new Mensaje("booker@gmail.com","Quiero pedir información sobre un animal");
//            m3 = new Mensaje("dani909@gmail.com","Me gustaría visitar la protectora");
//            m4 = new Mensaje("furro444@gmail.com","Contacto para adoptar uno de vuestros animales");
//
//            animalRepo.save(a1);
//            animalRepo.save(a2);
//            animalRepo.save(a3);
//
//
//            protectoraRepo.save(p1);
//            protectoraRepo.save(p2);
//            protectoraRepo.save(p3);
//
//
//            usuarioRepo.save(u1);
//            usuarioRepo.save(u2);
//            usuarioRepo.save(u3);
//
//            mensajeRepo.save(m1);
//            mensajeRepo.save(m2);
//            mensajeRepo.save(m3);
//            mensajeRepo.save(m4);
//
//
//
//        }
//
//        public void setAnimalImage(Animal aux, String classpathResource) throws IOException {
//            aux.setImagen(true);
//            Resource image = new ClassPathResource(classpathResource);
//            aux.setImagenanimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
//        }
//
//    }
//
//}
