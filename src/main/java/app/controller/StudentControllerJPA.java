package app.controller;


import app.percistence.entities.Student;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentControllerJPA {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/byGenre/{genero}")
    public ResponseEntity<?> getAllByGenre(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findByGenre(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{nroLibreta}")
    public ResponseEntity<?>getByNroLibreta(@PathVariable int nroLibreta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findByLibreta(nroLibreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }


    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Student entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> update(@PathVariable Long dni,@RequestBody Student entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.update(dni,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> delete(@PathVariable Long dni){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(studentService.delete(dni));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
