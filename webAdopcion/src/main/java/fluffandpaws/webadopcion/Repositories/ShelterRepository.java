package fluffandpaws.webadopcion.Repositories;

import fluffandpaws.webadopcion.BBDD.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShelterRepository  extends CrudRepository<Shelter, Long> {

}
