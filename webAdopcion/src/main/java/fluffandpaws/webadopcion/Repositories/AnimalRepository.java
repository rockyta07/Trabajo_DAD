package fluffandpaws.webadopcion.Repositories;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import fluffandpaws.webadopcion.BBDD.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.repository.CrudRepository;
@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    List<Animal> findAll();
    Optional<Animal> findById(Long id);
    //List<Animal> findAllById(List<Long> ids);
}


