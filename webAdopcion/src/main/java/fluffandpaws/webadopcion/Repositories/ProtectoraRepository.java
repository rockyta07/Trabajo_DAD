package fluffandpaws.webadopcion.Repositories;

import fluffandpaws.webadopcion.BBDD.Protectora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProtectoraRepository extends CrudRepository<Protectora, Long> {
    List<Protectora> findAll();
    Optional<Protectora> findById(Long l);
    void deleteById(Long id);
}
