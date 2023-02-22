package fluffandpaws.webadopcion.Service;

import fluffandpaws.webadopcion.BBDD.Shelter;
import fluffandpaws.webadopcion.Repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Service
public class shelterService {

    @Autowired
    private ShelterRepository shelter;
    //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
    @PostConstruct
    public void init(){

        save(new Shelter("Protectora1","12312312","Madrid",67584637,"Protectora1@gmail.com"));//ponemos los datos tal cual el constructor

    }
    public Optional<Shelter> findById(Integer id) {
        return shelter.findById(id);

    }

    public Iterable<Shelter> findAll(){//buscar todos los usuarios

        return shelter.findAll();
    }
    public void save(Shelter use){//guardar el usuario

        shelter.save(use);

    }

    public void replace (Shelter updatedShelter){//para modificar el usuario

        shelter.findById(Math.toIntExact(updatedShelter.getId())).orElseThrow();
        shelter.save(updatedShelter);
    }

    public void deleteById(Integer id){//borramos usuario

        this.shelter.deleteById(id);

    }
}
