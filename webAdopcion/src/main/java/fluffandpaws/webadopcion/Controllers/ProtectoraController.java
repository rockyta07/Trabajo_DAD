package fluffandpaws.webadopcion.Controllers;

import fluffandpaws.webadopcion.BBDD.Protectora;
import fluffandpaws.webadopcion.Service.ProtectoraService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/Protectoras")
public class ProtectoraController {

    @Autowired
    private ProtectoraService servProtectoras;


    @GetMapping("/")//buscamos todos los shelter
    public String mostrarProtectoras(Model model) {

        model.addAttribute("listaProtectoras", servProtectoras.findAll());

        return "todas_protectoras";

    }

    @GetMapping("/{id}")//Esto nos retorna el shelter
    public String getProtectora(Model model, @PathVariable Long id) {
        Protectora prot = servProtectoras.findById(id).orElseThrow();

        model.addAttribute("protect", prot);
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