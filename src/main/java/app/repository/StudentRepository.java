package app.repository;

import app.DTOs.StudentDTO;
import app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT e FROM Student e ORDER BY e.apellido ASC")
    List<Student> findAllByOrderByApellidoAsc();

    @Query("SELECT e FROM Student e WHERE e.nroLibreta = :nroLibreta")
    StudentDTO findBynroLibreta(@Param("nroLibreta") int nroLibreta);

    @Query("SELECT e FROM Student  e WHERE e.genero = :genero")
    List<Student> findByGenre(@Param("genero") String genero);

    @Query("SELECT e FROM Student e JOIN e.carrerasInscriptas c WHERE c.carrera.nombre = :career AND e.ciudad = :city")
    List<Student> getAllBy(String career, String city);
}
