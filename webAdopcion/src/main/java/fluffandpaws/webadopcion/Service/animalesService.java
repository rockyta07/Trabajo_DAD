package fluffandpaws.webadopcion.Service;
import fluffandpaws.webadopcion.BBDD.Animales;
import fluffandpaws.webadopcion.BBDD.Usuarios;
import fluffandpaws.webadopcion.Repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

//import static org.apache.catalina.valves.AbstractAccessLogValve.localDate;


//el @service sirve para conectar con la aplicación web a una base de datos, acceder y manipular los datos en esta base
@Service
public class animalesService {
    //Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
    @Autowired
    private AnimalRepository animales;
    //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
    @PostConstruct
    public void init(){

        save(new Animales("popi", (short) 10,"perro","Sin raza","macho","6/12/2022", (short) 23, (short) 14,123456));//ponemos los datos tal cual el constructor

    }
    public Optional<Animales> findById(Integer id) {
        return animales.findById(id);

    }

   /* public boolean exist(Integer id){

        return animales.existsById(id);

    }*/

    public Iterable<Animales> findAll(){//buscar todos los usuarios

        return animales.findAll();
    }
    public void save(Animales animal){

        animales.save(animal);

    }
    public void replace (Animales updatedAnimal){//para modificar el usuario

       animales.findById(updatedAnimal.getId()).orElseThrow();
        animales.save(updatedAnimal);
    }

    public void deleteById(Integer id){

        this.animales.deleteById(id);

    }


}
