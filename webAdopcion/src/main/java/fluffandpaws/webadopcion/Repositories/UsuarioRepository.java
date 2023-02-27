package fluffandpaws.webadopcion.Repositories;

import fluffandpaws.webadopcion.BBDD.Protectora;
import fluffandpaws.webadopcion.BBDD.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Spring lo implementa automáticamente
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long l);
    void deleteById(Long id);
}
