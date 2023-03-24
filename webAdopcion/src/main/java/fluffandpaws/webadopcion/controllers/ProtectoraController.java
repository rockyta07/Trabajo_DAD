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

    @ModelAttribute //esto sirve para que si yo soy admin pueda ver el boton de borrado y si no lo soy pues no
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();//realizar la autenticacion y autorizacion web
//en resumen para obtener la identificacion del usuario
        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("name", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/")//buscamos todos los shelter
    public String mostrarProtectoras(Model model) {

        model.addAttribute("listaProtectoras", servProtectoras.findAll());

        return "/temp_Protectora/todas_protectoras";

    }


    @GetMapping("/{id}")//Esto nos retorna el shelter
    public String getProtectora(Model model, @PathVariable Long id) {

        Protectora prot = servProtectoras.findById(id).orElseThrow();
        //List<Animal> listAnimal = prot.getAnimalesProtectora();

        model.addAttribute("protectora", prot);
        model.addAttribute("nombre", prot.getName());
        model.addAttribute("description", prot.toString());
        return "/temp_Protectora/protectora";

    }

    @GetMapping("/crearProtectora")
    public String crearProtectora(){
        return "/temp_Protectora/registroProtectora";
    }

    @PostMapping("/crearProtectora")
    public String crearProtectoraProcess(Model model, @ModelAttribute("protectora") Protectora protectora){
        protectora.setDescription(protectora.toString());
        servProtectoras.save(protectora);
        return "redirect:/Protectoras/"+protectora.getId();
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

        aux2.setDescription(aux2.toString());
        aux2.setPrtOrigen(paux);
        servAnimales.save(aux2);

        return "redirect:/Animales/" + aux2.getId();
    }


    @GetMapping("/editProtectora/{id}")
    public String editProtectora(Model model, @PathVariable Long id){

        Optional<Protectora> protectora = servProtectoras.findById(id);
        if(protectora.isPresent()){
            model.addAttribute("protectora", protectora.get());
        }

        return "/temp_Protectora/editProtectoraPage";

    }

    @PostMapping("/editProtectora")
    public String editProtectoraProcess(Model model, Protectora protectora) throws IOException, SQLException {
        //Optional<Protectora> prtBefore = servProtectoras.findById(protectora.getId());
        //protectora.setId(prtBefore.orElseThrow().getId());
        protectora.setDescription(protectora.toString());
        servProtectoras.save(protectora);

        model.addAttribute("protectoraId", protectora.getId());

        return "redirect:/Protectoras/" + protectora.getId();
    }

    @GetMapping("/borrarProtectora/{id}")
    public String removeProtectora(Model model, @PathVariable Long id){
        Optional<Protectora> protectora = servProtectoras.findById(id);
        if(protectora.isPresent()){
            servProtectoras.delete(id);
            model.addAttribute("protectora", protectora.get());
        }
        return "/temp_Protectora/protectoraBorrada";
    }
}