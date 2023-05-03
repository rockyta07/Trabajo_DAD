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
    //@CacheEvict(value = "cache#{#cacheNames}", allEntries=true)
    Protectora save(Protectora protectoras);
    @CacheEvict(allEntries = true)
    void deleteById(Long id);

//    Protectora findByName(String name);

    //@Cacheable //En los find by Id como ya son referenciados en el find all, los duplicará cada vez que se haga click
    Optional<Protectora> findById(Long l);

    @Cacheable
    List<Protectora> findAll();
    //void deleteById(Long id);
}
