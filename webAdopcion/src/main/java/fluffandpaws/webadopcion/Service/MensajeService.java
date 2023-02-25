package fluffandpaws.webadopcion.Service;

import fluffandpaws.webadopcion.BBDD.Mensaje;
import fluffandpaws.webadopcion.Repositories.MensajeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {
        //Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
        @Autowired
        private MensajeRepository mensajeRepo;
        //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"
        @PostConstruct
        public void init(){

            save(new Mensaje("dd@gmail.com","hola buenas"));//ponemos los datos tal cual el constructor
            save(new Mensaje("booker@gmail.com","Quiero pedir información sobre un animal"));//ponemos los datos tal cual el constructor
            save(new Mensaje("dani909@gmail.com","Me gustaría visitar la protectora"));//ponemos los datos tal cual el constructor
            save(new Mensaje("furro444@gmail.com","Contacto para adoptar uno de vuestros animales"));//ponemos los datos tal cual el constructor

        }

        public Optional<Mensaje> findById(Long id) {
            return mensajeRepo.findById(id);

        }


        public List<Mensaje> findAll(){//buscar todos los usuarios

            return mensajeRepo.findAll();
        }
        public void save(Mensaje me){//guardar el usuario

            mensajeRepo.save(me);

        }

        public boolean exist(Long id){//ver si existen loos usuarios

            return mensajeRepo.existsById(id);

        }

        public void replace (Mensaje updatedMensaje){//para modificar el usuario

            mensajeRepo.findById(updatedMensaje.getId()).orElseThrow();
            mensajeRepo.save(updatedMensaje);
        }

        public void delete(Long id){//borramos usuario

            this.mensajeRepo.deleteById(id);

        }


}
