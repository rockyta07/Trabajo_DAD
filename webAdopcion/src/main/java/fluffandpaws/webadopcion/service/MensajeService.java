package fluffandpaws.webadopcion.service;

import fluffandpaws.webadopcion.models.Mensaje;
import fluffandpaws.webadopcion.repositories.MensajeRepository;
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


        public Optional<Mensaje> findById(Long id) {
            return mensajeRepo.findById(id);
        }

        public List<Mensaje> findAll(){//buscar todos los usuarios
            return mensajeRepo.findAll();
        }

        public void save(Mensaje me){//guardar el usuario
            mensajeRepo.save(me);
        }

        public List<Mensaje> findBySender(String remitente){
            List<Mensaje> mensajes = findAll();
            return mensajes.stream()
                    .filter(m -> remitente.equals(m.getSender()))
                    .toList();
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
