package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.models.Usuario;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames="usuarios")
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); //El username no se puede repetir

    @CacheEvict(allEntries=true)
    Usuario save(Usuario usuarios);
    @CacheEvict(allEntries = true)
    void deleteById(Long id);


    // @Cacheable //En los find by Id como ya son referenciados en el find all, los duplicará cada vez que se haga click
    Optional<Usuario> findById(Long l);

    @Cacheable
    List<Usuario> findAll();
    //void deleteById(Long id);
}
