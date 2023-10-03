package app.controller;


import app.percistence.entities.Career;
import app.percistence.entities.Student;
import app.service.CareerService;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/careers")
public class CareerControllerJPA {

    @Autowired
    private CareerService careerService;
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    /*  //carga las relaciones, pero se rompe cuando trae todas las carreras / alumnos

    @PostMapping("/enrollStudent/{careerId}/{studentId}")
    public ResponseEntity<?> insertStudentInCareer(@PathVariable Long careerId, @PathVariable Long studentId) {

        Career career = careerService.findBy(careerId);
        Student student = studentService.findBy(studentId);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.matricularEstudianteEnCarrera(career, student));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

*/

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Career entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Career entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(careerService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
