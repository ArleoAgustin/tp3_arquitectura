package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import app.model.Persona;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

    @Query("SELECT t FROM Persona t where t.nombre = :nombre")
    public List<Persona> findAllByNombre(String nombre);

    @Query("SELECT t FROM Persona t where t.apellido = :apellido")
    public List<Persona> findAllByApellido(String apellido);
}
