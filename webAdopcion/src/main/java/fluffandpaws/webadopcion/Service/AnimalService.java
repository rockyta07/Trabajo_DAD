package fluffandpaws.webadopcion.Service;
import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.Repositories.AnimalRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.Optional;
import java.util.List;

//import static org.apache.catalina.valves.AbstractAccessLogValve.localDate;


//el @service sirve para conectar con la aplicación web a una base de datos, acceder y manipular los datos en esta base
@Service
public class AnimalService {
    //Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
    @Autowired
    private AnimalRepository animalRepo;
    //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
    @PostConstruct
    public void init() throws IOException{

        Animal a1, a2, a3;
        a1 = new Animal("popi", "2","perro","Sin raza","macho","6/12/2022",  "23",  "14");
        a2 = new Animal("luna", "3","perro","Sin raza","hembra","5/07/2022",  "23",  "14");
        a3 = new Animal("braco", "4","perro","Sin raza","macho","10/03/2017",  "23",  "14");

        setAnimalImage(a1,"/imagenesAnimales/perro1.png");
        setAnimalImage(a2,"/imagenesAnimales/perro2.png");
        setAnimalImage(a3,"/imagenesAnimales/perro3.png");
        save(a1);//ponemos los datos tal cual el constructor
        save(a2);
        save(a3);

    }
    public Optional<Animal> findById(Long id) {
        return animalRepo.findById(id);

    }

    /*
    public List<Animal> findById(List<Long> ids){
        return animalRepo.findAllById(ids);
    }*/

    public boolean exist(Long id){

        return animalRepo.existsById(id);

    }

    public List<Animal> findAll(){//buscar todos los usuarios

        return animalRepo.findAll();
    }

    public void save(Animal animal){

        animalRepo.save(animal);

    }
    public void replace (Animal updatedAnimal){//para modificar el usuario

       animalRepo.findById(updatedAnimal.getId()).orElseThrow();
        animalRepo.save(updatedAnimal);
    }

    public void delete(Long id){

        this.animalRepo.deleteById(id);

    }

    public void setAnimalImage(Animal aux, String classpathResource) throws IOException {
        aux.setImagen(true);
        Resource image = new ClassPathResource(classpathResource);
        aux.setImagenanimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }


}
