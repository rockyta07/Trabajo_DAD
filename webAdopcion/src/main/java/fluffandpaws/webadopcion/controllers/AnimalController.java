package fluffandpaws.webadopcion.controllers;

import fluffandpaws.webadopcion.models.Animal;
import fluffandpaws.webadopcion.models.Mensaje;
import fluffandpaws.webadopcion.models.Protectora;
import fluffandpaws.webadopcion.service.AnimalService;
import fluffandpaws.webadopcion.service.MensajeService;
import fluffandpaws.webadopcion.service.ProtectoraService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


//El controller va conectado con el service, asi que aprovechamos los métodos de service y los llamamos
@Controller
@RequestMapping("/Animales")
public class AnimalController {

    @Autowired
    private AnimalService servAnimales;

    @Autowired
    private MensajeService servMensajes;

    @Autowired
    private ProtectoraService servProtectoras;

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

    @GetMapping("/")//buscamos todos los animales
    public String getAnimales(Model model) {

        List<Animal> listAnimals = servAnimales.findAll();

        model.addAttribute("listaTodos", listAnimals);
        //model.addAttribute("image", listAnimals.getImage());

        return "/temp_Animal/todos_animales";
    }

    @GetMapping("/{id}")//Esto nos retorna el animal
    public String getAnimal(Model model, @PathVariable Long id) throws SQLException {
        Animal aux = servAnimales.findById(id).orElseThrow();
        byte[] imagenBytes = aux.getImagenAnimal().getBytes(1, (int) aux.getImagenAnimal().length()); // convertimos Blob a byte[]
        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes); // codificamos a base64
        model.addAttribute("animal", aux.getName());
        model.addAttribute("description", aux.toString());
        model.addAttribute("imagenBase64", imagenBase64); // agregamos el atributo a la vista
        model.addAttribute("image", aux.getImagenAnimal());
        return "/temp_Animal/animal";
    }

    @GetMapping("/animal/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Animal animal = servAnimales.getAnimalById(id);
        if (animal == null || animal.getImagenAnimal() == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Blob blob = animal.getImagenAnimal();
            int blobLength = (int) blob.length();
            byte[] imageBytes = blob.getBytes(1, blobLength);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageBytes.length);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/crearAnimal")
    public String crearAnimal(Model model){

        model.addAttribute("listaProtectoras", servProtectoras.findAll());
        return "/temp_Animal/registrarAnimal";
    }

    @PostMapping("/crearAnimal")
    public String crearAnimalProcess(Model model, Animal aux2, MultipartFile imagenAnimal, @RequestParam Protectora pselected) throws IOException{


        if (!imagenAnimal.isEmpty()) {
            aux2.setImagenanimal(BlobProxy.generateProxy(imagenAnimal.getInputStream(), imagenAnimal.getSize()));
            aux2.setImagen(true);
        }

        aux2.setPrtOrigen(pselected);

        servAnimales.save(aux2);
        return "redirect:/Animales/" + aux2.getId();
    }


    @GetMapping("/borrarAnimal/{id}")
    public String removeAnimal(Model model, @PathVariable Long id){
        Optional<Animal> animal = servAnimales.findById(id);
        if(animal.isPresent()){
            servAnimales.delete(id);
            model.addAttribute("animal", animal.get());
        }
        return "/temp_Animal/animalBorrado";
    }

    @GetMapping("/editAnimal/{id}")
    public String editAnimal(Model model, @PathVariable Long id) {

        Optional<Animal> animal = servAnimales.findById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());

        }

        return "/temp_Animal/editAnimalPage";

    }

    @PostMapping("/editAnimal")
    public String editAnimalProcess(Model model, Animal animal, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {

        //updateImage(book, removeImage, imageField);

        servAnimales.save(animal);

        model.addAttribute("animalId", animal.getId());

        return "redirect:/Animales/"+animal.getId();
    }

    @GetMapping("/adoptarAnimal/{id}")
    public String adoptAnimal(Model model, @PathVariable Long id) {

        Optional<Animal> animal = servAnimales.findById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());

        }

        return "/temp_Animal/adoptarAnimal";

    }

    @PostMapping("/adoptarAnimal")
    public String adoptAnimalProcess(@RequestParam Protectora prtId, @ModelAttribute("mensaje") Mensaje msg){ //
        msg.setPrtInstance(prtId);
        servMensajes.save(msg);

        //return "redirect:/Mensajes/" + msg.getId();
        //return "redirect:/Animales/";
        //return "redirect:/temp_Animal/todos_animales"
        return "redirect:/Animales/";
    }

    public void setAnimalImage(Animal aux, String classpathResource) throws IOException {
        aux.setImagen(true);
        Resource image = new ClassPathResource(classpathResource);
        aux.setImagenanimal(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

}
