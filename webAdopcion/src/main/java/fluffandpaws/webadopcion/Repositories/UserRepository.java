package fluffandpaws.webadopcion.Repositories;

import fluffandpaws.webadopcion.BBDD.Usuarios;
import org.springframework.data.repository.CrudRepository;

//Spring lo implementa automáticamente

public interface UserRepository extends CrudRepository<Usuarios, Integer> {
    //CRUD = Create, Read, Update, Delete
}
