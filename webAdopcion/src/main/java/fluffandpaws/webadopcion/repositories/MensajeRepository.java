package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.models.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findAll();
    Optional<Mensaje> findById(Long id);
    void deleteById(Long id);
}
