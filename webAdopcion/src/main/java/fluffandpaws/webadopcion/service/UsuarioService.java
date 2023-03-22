package fluffandpaws.webadopcion.service;
import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//el @service sirve para conectar con la aplicación web a una base de datos, acceder y manipular los datos en esta base
@Service
public class UsuarioService {
//Autowired tiene estos métodos disponibles(findByld, findAll, save y delete, asi que los aprovechamos
        @Autowired
        private UsuarioRepository user;
//No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"

        public Optional<Usuario> findById(Long id) {
            return user.findById(id);

        }

        public List<Usuario> findAll(){//buscar todos los usuarios

            return user.findAll();
        }

        public Usuario findByUsername(String username){
            return user.findByUsername(username);
        }

        public Usuario getByUsername(String username) {
            return user.getByUsername(username);
        }

        public boolean exist(Long id){//ver si existen loos usuarios

            return user.existsById(id);

        }
        public void save(Usuario use){//guardar el usuario

            user.save(use);

        }

        public void replace (Usuario updatedUser){//para modificar el usuario

            user.findById(updatedUser.getId()).orElseThrow();
            user.save(updatedUser);
        }

        public void delete(Long id){//borramos usuario

            this.user.deleteById(id);

        }



}
