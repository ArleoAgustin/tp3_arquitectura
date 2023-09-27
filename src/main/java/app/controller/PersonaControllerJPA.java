package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import app.model.Persona;
import app.repository.PersonaRepository;

import java.util.Optional;

@RestController
@RequestMapping("personas")
public class PersonaControllerJPA {

    @Qualifier("personaRepository")
    @Autowired
    private final PersonaRepository repository;

    public PersonaControllerJPA(@Qualifier("personaRepository") PersonaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Iterable<Persona> getPersonas(){
        return repository.findAll();
    }

    @GetMapping("/byApellido/{apellido}")
    public Iterable<Persona> getPersonasByApellido(@PathVariable String apellido){
        return repository.findAllByApellido(apellido);
    }

    @GetMapping("/byNombre/{nombre}")
    public Iterable<Persona> getPersonasByNombre(@PathVariable String nombre){
        return repository.findAllByNombre(nombre);
    }

    @PostMapping("/")
    public Persona newPersona(@RequestBody Persona p){
        return repository.save(p);
    }

    @GetMapping("/{id}")
    Optional<Persona> one(@PathVariable Long id){
        return repository.findById(id);
    }

    @PutMapping("/personas/{id}")
    Persona replacePersona(@RequestBody Persona newPersona, @PathVariable Long id){
        return repository.findById(id)
                .map(persona -> {
                    persona.setNombre(newPersona.getNombre());
                    persona.setApellido(newPersona.getApellido());
                    return repository.save(persona);
                })
                .orElseGet(() -> {
                    newPersona.setDni(id);
                    return repository.save(newPersona);
                });
    }

    @DeleteMapping("/{id}")
    void deletePersona(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
