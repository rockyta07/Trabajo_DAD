package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Animal;
import fluffandpaws.webadopcion.models.Protectora;
import fluffandpaws.webadopcion.service.AnimalService;
import fluffandpaws.webadopcion.service.ProtectoraService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

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
   /* @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }*/

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


    @GetMapping("/borrarProtectora/{id}")
    public String removeAnimal(Model model, @PathVariable Long id){
        Optional<Protectora> protectora = servProtectoras.findById(id);
        if(protectora.isPresent()){
            servAnimales.delete(id);
            model.addAttribute("protectora", protectora.get());
        }
        return "/temp_Protectora/protectoraBorrada";
    }


    @GetMapping("/editProtectora/{id}")
    public String editProtectora(Model model, @PathVariable Long id){

        Optional<Protectora> protectora = servProtectoras.findById(id);
        if(protectora.isPresent()){
            model.addAttribute("shelter", protectora.get());
        }

        return "/temp_Protectora/editProtectoraPage";

    }

    @PostMapping("/editProtectora")
    public String editProtectoraProcess(Model model, Protectora protectora) throws IOException, SQLException {

        servProtectoras.save(protectora);

        model.addAttribute("protectoraId", protectora.getId());

        return "redirect:/Protectoras/" + protectora.getId();

    }
}