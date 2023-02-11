package fluffandpaws.webadopcion.BBDD;

import org.springframework.data.repository.CrudRepository;

//Spring lo implementa automáticamente

public interface UserRepository extends CrudRepository<Users, Integer> {
    //CRUD = Create, Read, Update, Delete
}
