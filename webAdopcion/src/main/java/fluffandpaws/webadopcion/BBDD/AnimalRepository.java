package fluffandpaws.webadopcion.BBDD;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import jakarta.persistence.Entity;
import org.springframework.data.repository.CrudRepository;

//import org.springframework.data.repository.CrudRepository;
@Entity
public interface AnimalRepository extends CrudRepository<AnimalRepository, Integer> {
    //Igual que el repositorio de usuarios
}
