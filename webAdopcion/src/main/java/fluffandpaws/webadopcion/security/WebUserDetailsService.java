package fluffandpaws.webadopcion.security;

import fluffandpaws.webadopcion.models.Usuario;
import fluffandpaws.webadopcion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.getByUsername(username);

        if(usuario != null){
            return new WebUserDetails(usuario);
        }

        throw new UsernameNotFoundException("No se ha encontrado un usuarioo con ese nombre");
    }
}
