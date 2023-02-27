package fluffandpaws.webadopcion.service;
import fluffandpaws.webadopcion.model.Animal;
import fluffandpaws.webadopcion.repositories.AnimalRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
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


    }

    //Funciones

    public void changeAnimalName(long id, String name){
        Animal animal;
        animal = animalRepo.findById(id).get();
        animal.setName_anm(name);
        animal.setDescription(); //Actualizamos la info de la descripción

    }

    public void changeAnimalBday(long id, String name){
        Animal animal;
        animal = animalRepo.findById(id).get();
        animal.setBirthdate(name);
        animal.setDescription(); //Actualizamos la info de la descripción

    }

    public void changeAnimalBreed(long id, String name){
        Animal animal;
        animal = animalRepo.findById(id).get();
        animal.setBreed(name);
        animal.setDescription(); //Actualizamos la info de la descripción
    }

    public void changeAnimalWeight(long id, String name){
        Animal animal;
        animal = animalRepo.findById(id).get();
        animal.setName_anm(name);
        animal.setDescription(); //Actualizamos la info de la descripción

    }

    public void changeAnimalSize(long id, String size){
        Animal animal;
        animal = animalRepo.findById(id).get();
        animal.setName_anm(size);
        animal.setDescription(); //Actualizamos la info de la descripción

    }

    public void changeAnimalImage(long id, MultipartFile image) throws IOException {
        Animal animal;
        animal = animalRepo.findById(id).get();
        animal.setImageAnimal(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        animal.setImage(true);
    }

    public Optional<Animal> findById(Long id) {
        return animalRepo.findById(id);

    }

    public boolean exist(Long id){
        return animalRepo.existsById(id);
    }

    public List<Animal> findAll(){//buscar todos los usuarios
        return animalRepo.findAll();
    }

    public List<Animal> findByProtectora(){//buscar todos los usuarios
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
        aux.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        aux.setImageAnimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }


}
