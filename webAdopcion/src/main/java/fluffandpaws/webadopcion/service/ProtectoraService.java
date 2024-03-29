package fluffandpaws.webadopcion.service;

import fluffandpaws.webadopcion.models.Protectora;
import fluffandpaws.webadopcion.repositories.ProtectoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProtectoraService {

    @Autowired
    private ProtectoraRepository protectoraRepository;
    //No tener en cuenta ahora mismo los errores de los metodos findBy etc, Se necesita conectar A la base para que vaya bien!"

    public Optional<Protectora> findById(Long id) {
        return protectoraRepository.findById(id);
    }

    /*public Protectora findByName(String name){
        return protectoraRepository.findByName(name);
    }*/

    public List<Protectora> findAll(){//buscar todas las protectoras

        return protectoraRepository.findAll();
    }
    public void save(Protectora use){//guardar la protectora

        protectoraRepository.save(use);

    }

    public void delete(Long id){//borramos usuario

        this.protectoraRepository.deleteById(id);

    }
}
