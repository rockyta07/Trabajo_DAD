package fluffandpaws.webadopcion.repositories;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import fluffandpaws.webadopcion.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    //Todos los animales que se muestren deben no tener usuario adoptante
    @Query("SELECT animal FROM Animal animal WHERE animal.adopter = NULL")
    List<Animal> findAll();

    //Filtrados por protectora
    @Query("SELECT animal FROM Animal animal WHERE animal.prtOrigen.id = :id_protectora AND animal.adopter = NULL")
    List<Animal> findByProtectora(@Param("id_protectora") long id_protectora);

    Optional<Animal> findById(Long id);
}


