package app.percistence.repositories.Interface;

import app.percistence.entities.Carrera;
import app.percistence.entities.Estudiante;
import app.percistence.entities.RelacionCarreraEstudiante;

import java.util.List;

public interface InterfaceEstudianteRepository {

    void addEstudiante(Estudiante p) throws Exception;
    void updateEstudiante(Estudiante p, Estudiante pNew) throws Exception;
    void deleteEstudiante(int id) throws Exception;
    List<Estudiante> getEstudiantes() throws Exception;

    void addEstudianteToCarrera(Estudiante e, Carrera c) throws Exception;
    void addEstudianteToCarrera(RelacionCarreraEstudiante rce) throws Exception;

    List<Estudiante> buscarEstudiantesPorCarrera(Carrera c) throws Exception;

    List<Estudiante> getEstudiantesOrderByLastName() throws Exception;

    Estudiante getBy(int nroLibreta) throws Exception;

    List<Estudiante> getAllBy(String genero) throws Exception;

    List<Estudiante> getAllBy(String carrera, String ciudad) throws Exception;


}
