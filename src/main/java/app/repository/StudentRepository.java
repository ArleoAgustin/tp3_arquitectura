package app.repository;

import app.percistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT t FROM Student t where t.nombre = :nombre")
    public List<Student> findAllByNombre(String nombre);

    @Query("SELECT t FROM Student t where t.apellido = :apellido")
    public List<Student> findAllByApellido(String apellido);





}
