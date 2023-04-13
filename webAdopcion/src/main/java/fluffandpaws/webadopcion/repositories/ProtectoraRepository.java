package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.models.Protectora;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames="protectoras")
@Repository
public interface ProtectoraRepository extends JpaRepository<Protectora, Long> {
    @CacheEvict(allEntries=true)
    Protectora save(Protectora protectoras);
    void deleteById(Long id);

    @Cacheable
    Optional<Protectora> findById(Long l);

    @Cacheable
    List<Protectora> findAll();
    //void deleteById(Long id);
}
