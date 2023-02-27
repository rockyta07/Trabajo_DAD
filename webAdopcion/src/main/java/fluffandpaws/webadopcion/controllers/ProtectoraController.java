package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.model.Protectora;
import fluffandpaws.webadopcion.service.AnimalService;
import fluffandpaws.webadopcion.service.MensajeService;
import fluffandpaws.webadopcion.service.ProtectoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Protectoras")
public class ProtectoraController {

    @Autowired
    private ProtectoraService servProtectoras;
    @Autowired
    private MensajeService servMensajes;
    @Autowired
    private AnimalService servAnimales;

    @GetMapping("/")//buscamos todos los shelter
    public String mostrarProtectoras(Model model) {

        model.addAttribute("listaProtectoras", servProtectoras.findAll());

        return "todas_protectoras";

    }

    @GetMapping("/{id}")//Esto nos retorna el shelter
    public String getProtectora(Model model, @PathVariable Long id) {
        Protectora protectora = servProtectoras.findById(id).orElseThrow();

        model.addAttribute("protect", protectora);
        return "protectora";

    }

    @GetMapping("/crearProtectora")
    public String crearProtectora(){
        return "registroProtectora";
    }

    @PostMapping("/crearProtectora")
    public String crearProtectoraProcess(Model model, @ModelAttribute("protectora") Protectora pro){
        servProtectoras.save(pro);
        return "redirect:/Protectoras/"+pro.getId();
    }

    @PutMapping("/{id}/algo")//para modificar shelter

    public Protectora replaceShelter(@PathVariable long id, @RequestBody Protectora newShelter) {
        newShelter.setId(id);
        //servAnimales.replace(newShelter);
        return newShelter;
    }

    @DeleteMapping("/{id}/algo")//borramos el shelter

    public Protectora deleteShelter(@PathVariable Long id) {
        Protectora protectora = servProtectoras.findById(id).orElseThrow();
        //servAnimales.deleteById(id);
        return protectora;
    }

}