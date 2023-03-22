package fluffandpaws.webadopcion.repositories;

import fluffandpaws.webadopcion.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Spring lo implementa automáticamente
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long l);
    Usuario findByUsername(String username); //El username no se puede repetir
    void deleteById(Long id);
    Usuario getByUsername(String username);
}
