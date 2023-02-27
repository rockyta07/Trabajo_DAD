package fluffandpaws.webadopcion.Service;

import fluffandpaws.webadopcion.BBDD.Protectora;
import fluffandpaws.webadopcion.Repositories.ProtectoraRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProtectoraService {

    @Autowired
    private ProtectoraRepository shelter;
    //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"

    public Optional<Protectora> findById(Long id) {
        return shelter.findById(id);

    }

    public List<Protectora> findAll(){//buscar todos los usuarios

        return shelter.findAll();
    }
    public void save(Protectora use){//guardar el usuario

        shelter.save(use);

    }

    public void replace (Protectora updatedProtectora){//para modificar el usuario

        shelter.findById(updatedProtectora.getId()).orElseThrow();
        shelter.save(updatedProtectora);
    }

    public void deleteById(Long id){//borramos usuario

        this.shelter.deleteById(id);

    }
}
