package app.percistence.repositories.Interface;

import app.percistence.entities.Career;
import app.percistence.entities.Student;
import app.percistence.entities.RelationCareerStudent;

import java.util.List;

public interface InterfaceEstudianteRepository {

    void addEstudiante(Student p) throws Exception;
    void updateEstudiante(Student p, Student pNew) throws Exception;
    void deleteEstudiante(int id) throws Exception;
    List<Student> getEstudiantes() throws Exception;

    void addEstudianteToCarrera(Student e, Career c) throws Exception;
    void addEstudianteToCarrera(RelationCareerStudent rce) throws Exception;

    List<Student> buscarEstudiantesPorCarrera(Career c) throws Exception;

    List<Student> getEstudiantesOrderByLastName() throws Exception;

    Student getBy(int nroLibreta) throws Exception;

    List<Student> getAllBy(String genero) throws Exception;

    List<Student> getAllBy(String carrera, String ciudad) throws Exception;


}
