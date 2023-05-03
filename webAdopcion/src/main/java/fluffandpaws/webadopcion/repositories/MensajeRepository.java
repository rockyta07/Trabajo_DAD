package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.models.Mensaje;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames="mensajes")
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    @CacheEvict(allEntries=true)
    Mensaje save(Mensaje mensajes);

    List<Mensaje> findBySender(String remitente);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);
    @Cacheable
    Optional<Mensaje> findById(Long id);

    @Cacheable
    List<Mensaje> findAll();
}
