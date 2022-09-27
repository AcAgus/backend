package com.portfolio.agusAc.Controller;

import com.portfolio.agusAc.Entity.Persona;
import com.portfolio.agusAc.Service.ImPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    ImPersonaService imPersonaService;

    @GetMapping("personas/obtener")
    public List<Persona> getPersona() {
        return imPersonaService.getPersona();
    }

    @PostMapping("personas/crear")
    public String crearPersona(@RequestBody Persona persona) {
        imPersonaService.guardar(persona);
        return "La persona se creo con exito";
    }

    @DeleteMapping("personas/borrar/{id}")
    public String borrarPersona(@PathVariable int id) {
        imPersonaService.eliminar(id);
        return "La personsa con id:" + id + " fue eliminada";
    }

    @PutMapping("personas/editar/{id}")
    public Persona editarPersona(@PathVariable int id, @RequestParam("nombre") String nuevoNombre,
                                 @RequestParam("descripcion") String nuevoDescripcion,
                                 @RequestParam("imagen") String nuevoImg,
                                 @RequestParam("titulo") String nuevotitulo) {

        Persona persona = imPersonaService.buscar(id);
        persona.setNombre(nuevoNombre);
        persona.setDescripcion(nuevoDescripcion);
        persona.setImg(nuevoImg);
        persona.setTitulo(nuevotitulo);
        imPersonaService.guardar(persona);
        return persona;
    }

    @GetMapping("personas/obtener/perfil")
    public Persona buscarPersona() {
        return imPersonaService.buscar((int) 1);
    }


}

