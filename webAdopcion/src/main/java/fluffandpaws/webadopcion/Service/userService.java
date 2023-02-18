package fluffandpaws.webadopcion.Service;
import fluffandpaws.webadopcion.BBDD.Usuarios;
import fluffandpaws.webadopcion.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

//el @service sirve para conectar con la aplicación web a una base de datos, acceder y manipular los datos en esta base
@Service
public class userService {
//Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
        @Autowired
        private UserRepository users;
//No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
        @PostConstruct
        public void init(){

            save(new Usuarios("Juan","juna","Juan@gmail.com"));//ponemos los datos tal cual el constructor

        }
        public Optional<Usuarios> findById(Integer id) {
            return users.findById(id);

        }

        public boolean exist(long id){

            return users.existsById(id);

        }

        public List<Usuarios> findAll(){

            return users.findAll();
        }
        public void save(Usuarios user){

            users.save(user);

        }

        public void deleteById(long id){

            this.users.deleteById(id);

        }


}
