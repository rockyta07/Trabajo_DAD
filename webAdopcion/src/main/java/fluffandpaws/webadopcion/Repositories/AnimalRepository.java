package fluffandpaws.webadopcion.Repositories;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import fluffandpaws.webadopcion.BBDD.Animales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.repository.CrudRepository;
@Repository
public interface AnimalRepository extends CrudRepository<Animales, Long> {
    //Igual que el repositorio de usuarios
}


