package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Animal;
import fluffandpaws.webadopcion.BBDD.Protectora;
import fluffandpaws.webadopcion.Service.AnimalService;
import fluffandpaws.webadopcion.Service.ProtectoraService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/Protectoras")
public class ProtectoraController {

    @Autowired
    private ProtectoraService servProtectoras;

    @Autowired
    private AnimalService servAnimales;


    @GetMapping("/")//buscamos todos los shelter
    public String mostrarProtectoras(Model model) {

        model.addAttribute("listaProtectoras", servProtectoras.findAll());

        return "/temp_Protectora/todas_protectoras";

    }

    @GetMapping("/{id}")//Esto nos retorna el shelter
    public String getProtectora(Model model, @PathVariable Long id) {

        Protectora prot = servProtectoras.findById(id).orElseThrow();
        //List<Animal> listAnimal = prot.getAnimalesProtectora();

        model.addAttribute("protect", prot);
        return "/temp_Protectora/protectora";

    }

    @GetMapping("/crearProtectora")
    public String crearProtectora(){
        return "/temp_Protectora/registroProtectora";
    }

    @PostMapping("/crearProtectora")
    public String crearProtectoraProcess(Model model, @ModelAttribute("protectora") Protectora pro){
        servProtectoras.save(pro);
        return "redirect:/Protectoras/"+pro.getId();
    }

    @GetMapping("/{id}/crear_Animal")
    public String crearAnimalxProtectora(@PathVariable Long id, Model model){

        model.addAttribute("id", id);
        return "/temp_Protectora/registroAnimalProt";
    }

    @PostMapping("/{idShel}/crear_Animal")
    public String crearAnimalProcessxProtectora(Model model, Animal aux2, MultipartFile imgAnimalxProtectora, @PathVariable Long idShel) throws IOException{

        Protectora paux = servProtectoras.findById(idShel).get();


        if (!imgAnimalxProtectora.isEmpty()) {
            aux2.setImagenanimal(BlobProxy.generateProxy(imgAnimalxProtectora.getInputStream(), imgAnimalxProtectora.getSize()));
            aux2.setImagen(true);
        }


        aux2.setPrtOrigen(paux);
        servAnimales.save(aux2);

        return "redirect:/Animales/" + aux2.getId();
    }





/*
    @PutMapping("/{id}/algo")//para modificar shelter

    public Shelter replaceShelter(@PathVariable Integer id, @RequestBody Shelter newShelter) {

        newShelter.setId(id);
        shel.replace(newShelter);
        return newShelter;

    }

    @DeleteMapping("/{id}/algo")//borramos el shelter

    public Shelter deleteShelter(@PathVariable Long id) {

        Shelter s = shel.findById(id).orElseThrow();
        shel.deleteById(id);
        return s;


    }

*/
}