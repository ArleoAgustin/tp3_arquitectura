package app.repository;

import app.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    @Query("SELECT c FROM Career c WHERE SIZE(c.inscriptos) > 0 ORDER BY SIZE(c.inscriptos) DESC")
    List getWithIscriptosOrderByCant();


    @Query("SELECT c, r, e FROM Career c INNER JOIN c.inscriptos r INNER JOIN r.estudiante e")
    List<Object[]> getReport();

}
