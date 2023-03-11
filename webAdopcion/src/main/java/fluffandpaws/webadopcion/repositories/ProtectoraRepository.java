package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.models.Protectora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProtectoraRepository extends JpaRepository<Protectora, Long> {
    List<Protectora> findAll();
    Optional<Protectora> findById(Long l);
    void deleteById(Long id);
}
