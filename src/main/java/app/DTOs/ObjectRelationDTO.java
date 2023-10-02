package app.DTOs;


import app.percistence.entities.Career;
import app.percistence.entities.Student;
import app.percistence.entities.RelationCareerStudent;



public class ObjectRelationDTO {
    private Career carrera;
    private Student estudiante;
    private RelationCareerStudent relacionCarreraEstudiante;
    private int fechaInscripcion;
    private int fechaEgreso;

    public ObjectRelationDTO(Career carrera, Student estudiante, RelationCareerStudent relacionCarreraEstudiante) {
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.relacionCarreraEstudiante = relacionCarreraEstudiante;
    }

    public Career getCarrera() {
        return carrera;
    }

    public Student getEstudiante() {
        return estudiante;
    }

    public RelationCareerStudent getRelacionCarreraEstudiante() {
        return relacionCarreraEstudiante;
    }

}
