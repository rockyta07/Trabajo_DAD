package fluffandpaws.webadopcion.repositories;
/*---------------------------------------------------------

    La interfaz que recogerá los animales en adopción

-----------------------------------------------------------*/

import fluffandpaws.webadopcion.models.Animal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames="animales")//la cache se llama animales
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {


    @CacheEvict(allEntries=true)//cuando hagamos save o delete se borrara la cache anterior
    Animal save(Animal animales);
    @CacheEvict(allEntries=true)
    void deleteById(Long id);

    //En los find by Id como ya son referenciados en el find all, los duplicará cada vez que se haga click
    //@Cacheable//cachea el resultado, si se llama al metodo varias veces se ejecuta solo la primera llamada y las llamadas posteriores se leeran desde cache
    Optional<Animal> findById(Long id);

    @Cacheable
    List<Animal> findAll();
    //List<Animal> findAllById(List<Long> ids);
}




