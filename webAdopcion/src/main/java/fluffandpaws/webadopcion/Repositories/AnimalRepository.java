package fluffandpaws.webadopcion.Repositories;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import fluffandpaws.webadopcion.BBDD.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAll();
    Optional<Animal> findById(Long id);
    //List<Animal> findAllById(List<Long> ids);
}


