package fluffandpaws.webadopcion.Repositories;

import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.BBDD.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findAll();
    Optional<Mensaje> findById(Long id);
    void deleteById(Long id);
}
