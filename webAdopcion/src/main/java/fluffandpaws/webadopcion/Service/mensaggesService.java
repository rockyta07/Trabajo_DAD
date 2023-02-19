package fluffandpaws.webadopcion.Service;

import fluffandpaws.webadopcion.BBDD.Messages;
import fluffandpaws.webadopcion.BBDD.Usuarios;
import fluffandpaws.webadopcion.Repositories.MessagesRepository;
import fluffandpaws.webadopcion.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class mensaggesService {
        //Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
        @Autowired
        private MessagesRepository mens;
        //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
        @PostConstruct
        public void init(){

            save(new Messages("hola buenas"));//ponemos los datos tal cual el constructor

        }
        public Optional<Messages> findById(Integer id) {
            return mens.findById(id);

        }
      /*  public boolean exist(Integer id){//ver si existen loos usuarios

            return user.existsById(id);

        }*/

        public Iterable<Messages> findAll(){//buscar todos los usuarios

            return mens.findAll();
        }
        public void save(Messages me){//guardar el usuario

            mens.save(me);

        }

        public void replace (Messages updatedMessages){//para modificar el usuario

            mens.findById(updatedMessages.getId()).orElseThrow();
            mens.save(updatedMessages);
        }

        public void deleteById(Integer id){//borramos usuario

            this.mens.deleteById(id);

        }


}
