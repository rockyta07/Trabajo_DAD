package fluffandpaws.webadopcion.Repositories;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import org.springframework.data.repository.CrudRepository;

//import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<AnimalRepository, Integer> {
    //Igual que el repositorio de usuarios
}
