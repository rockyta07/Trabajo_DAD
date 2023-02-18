package fluffandpaws.webadopcion.Service;
import fluffandpaws.webadopcion.BBDD.Animales;
import fluffandpaws.webadopcion.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

//el @service sirve para conectar con la aplicación web a una base de datos, acceder y manipular los datos en esta base
@Service
public class animalesService {
    //Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
    @Autowired
    private animalesService animales;
    //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
    @PostConstruct
    public void init(){

        save(new Animales(123456788L,"Popi","Sin raza"));//ponemos los datos tal cual el constructor

    }
    public Optional<Animales> findById( String nombre) {
        return animales.findById(nombre);

    }

    public boolean exist(long id){

        return animales.existsById(id);

    }

    public List<Animales> findAll(){

        return animales.findAll();
    }
    public void save(Animales animal){

        animales.save(animal);

    }

    public void deleteById(long id){

        this.animales.deleteById(id);

    }


}
