package app.controller;


import app.model.Career;
import app.model.Student;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todas las carreras");
        }
    }

    @GetMapping("/getReport")
    public ResponseEntity<?> getReport(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.getReport());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al intentar recuperar el reporte");
        }
    }

    @GetMapping("/OrderByCantStudentRegistered")
    public ResponseEntity<?> getWithIscriptosOrderByCant(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.findWithIscriptosOrderByCant());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todas las carreras por cantidad de inscriptos");
        }
    }

    @PostMapping("/{careerId}/{studentId}")
    public ResponseEntity<?> insertStudentInCareer(@PathVariable Long careerId, @PathVariable Long studentId) {

        Career career = careerService.findBy(careerId);
        Student student = studentService.findBy(studentId);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.matricularEstudianteEnCarrera(career, student));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al matricular el estudiante con dni "+studentId + " con la carrera de id "+ careerId);
        }
    }



    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Career entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al intentar registrar una nueva carrera");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Career entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al intentar actualizar la carrera de id "+ id );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(careerService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al intentar eliminar la carrera con id " + id);
        }
    }

}
