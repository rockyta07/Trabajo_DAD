package fluffandpaws.webadopcion.Service;
import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.Repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void delete(Long id){

        this.animalRepo.deleteById(id);

    }




}
