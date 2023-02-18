package fluffandpaws.webadopcion.Repositories;

import fluffandpaws.webadopcion.BBDD.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//Spring lo implementa automáticamente
@Repository
public interface UserRepository extends CrudRepository<Usuarios, Integer> {
    //CRUD = Create, Read, Update, Delete
}
