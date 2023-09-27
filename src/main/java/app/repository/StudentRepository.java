package app.repository;

import app.model.Persona;
import app.percistence.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Estudiante, Long> {
/*
    @Query("SELECT t FROM Persona t where t.nombre = :nombre")
    public List<Persona> findAllByNombre(String nombre);

    @Query("SELECT t FROM Persona t where t.apellido = :apellido")
    public List<Persona> findAllByApellido(String apellido);*/

}
