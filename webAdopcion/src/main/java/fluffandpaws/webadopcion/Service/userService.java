package fluffandpaws.webadopcion.Service;
import fluffandpaws.webadopcion.BBDD.Usuarios;
import fluffandpaws.webadopcion.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

//el @service sirve para conectar con la aplicación web a una base de datos, acceder y manipular los datos en esta base
@Service
public class userService {
//Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
        @Autowired
        private UserRepository user;
//No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
        @PostConstruct
        public void init(){

            save(new Usuarios("Juan","García","Rodriguez"));//ponemos los datos tal cual el constructor

        }
        public Optional<Usuarios> findById(Integer id) {
            return user.findById(id);

        }
      /*  public boolean exist(Integer id){//ver si existen loos usuarios

            return user.existsById(id);

        }*/

        public Iterable<Usuarios> findAll(){//buscar todos los usuarios

            return user.findAll();
        }
        public void save(Usuarios use){//guardar el usuario

            user.save(use);

        }

        public void replace (Usuarios updatedUser){//para modificar el usuario

            user.findById(Math.toIntExact(updatedUser.getId())).orElseThrow();
            user.save(updatedUser);
        }

        public void deleteById(Integer id){//borramos usuario

            this.user.deleteById(id);

        }


}
