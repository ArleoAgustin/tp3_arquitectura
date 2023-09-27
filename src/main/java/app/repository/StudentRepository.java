package app.repository;

import app.percistence.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface StudentRepository extends RepoBase<Estudiante, Long>{

    @Query("SELECT t FROM Estudiante t where t.nombre = :nombre")
    public List<Estudiante> findAllByNombre(String nombre);

    @Query("SELECT t FROM Estudiante t where t.apellido = :apellido")
    public List<Estudiante> findAllByApellido(String apellido);



}
