package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.model.Mensaje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
    List<Mensaje> findAll();
    Optional<Mensaje> findById(Long id);
    void deleteById(Long id);
}
