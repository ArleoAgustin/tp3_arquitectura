package app.controller;


import app.model.Student;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todos los estudiantes.");
        }
    }


    @GetMapping("/byGenre/{genero}")
    public ResponseEntity<?> getAllByGenre(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findByGenre(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener los estudiantes con el genero "+ genero);
        }
    }

    @GetMapping("/{career}/{city}")
    public ResponseEntity<?> getAllBy(@PathVariable String career, @PathVariable String city){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllBy(career,city));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener estudiantes de " + city + " en la carrera " + career);
        }
    }

    @GetMapping("/{nroLibreta}")
    public ResponseEntity<?>getByNroLibreta(@PathVariable int nroLibreta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findByLibreta(nroLibreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener estudiante con numero de libreta "+ nroLibreta);
        }
    }


    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Student entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar un nuevo estudiante");
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> update(@PathVariable Long dni,@RequestBody Student entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.update(dni,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar estudiante con numero de dni "+ dni);
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> delete(@PathVariable Long dni){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.delete(dni));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al intentar eliminar el estudiante con dni "+dni);
        }
    }

}
