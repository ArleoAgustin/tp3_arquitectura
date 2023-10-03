package app.repository;

import app.percistence.entities.Career;
import app.percistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

/*
    @Query("INSERT INTO RelationCareerStudent ()")
    void matricularACarrera(Long dni);
*/


    /*
    *     @Override
        public void matricularACarrera(Estudiante e, Carrera c) throws Exception {
        RelacionCarreraEstudiante rce = new RelacionCarreraEstudiante(c, e);
        entityManager.getTransaction().begin();
        entityManager.persist(rce);
        entityManager.getTransaction().commit();
    }
    *
    * */
}
