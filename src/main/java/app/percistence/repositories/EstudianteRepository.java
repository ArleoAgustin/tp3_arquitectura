package app.percistence.repositories;

import app.percistence.entities.Career;
import app.percistence.entities.Student;
import app.percistence.entities.RelationCareerStudent;
import app.percistence.repositories.Interface.InterfaceEstudianteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Repository("StudentRepository")

public class EstudianteRepository implements InterfaceEstudianteRepository {

    private EntityManager entityManager;
    private static EstudianteRepository instance;

    public EstudianteRepository(){

    }

    public static EstudianteRepository getInstance(){
        if (instance == null) {
            instance = new EstudianteRepository();
        }
        return instance;

    }

    @Override
    public void addEstudiante(Student p) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateEstudiante(Student p, Student pNew) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.merge(pNew);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteEstudiante(int id) throws Exception {
        Student p = obtenerPorId(id);
        if (p != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(p);
            entityManager.getTransaction().commit();
        }
    }

    public Student obtenerPorId(int id) {
        return entityManager.find(Student.class, id);
    }


    @Override
    public List getEstudiantes() throws Exception {
        return  entityManager.createQuery("SELECT p FROM Student p").getResultList();
    }

    @Override
    public void addEstudianteToCarrera(Student e, Career c) throws Exception {
        RelationCareerStudent rce = new RelationCareerStudent(c, e);
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(rce);
        this.entityManager.getTransaction().commit();
    }
    @Override
    public void addEstudianteToCarrera(RelationCareerStudent rce) throws Exception {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(rce);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Student> buscarEstudiantesPorCarrera(Career c) throws Exception {
        TypedQuery<Student> query = this.entityManager.createQuery(
                "SELECT e FROM Student e JOIN RelationCareerStudent r WHERE r.carrera.id = :c",
                Student.class
        );
        query.setParameter("c", c.getIdCarrera());
        return query.getResultList();
    }

    @Override
    public List<Student> getEstudiantesOrderByLastName() throws Exception {
        return entityManager.createQuery("SELECT e FROM Student e ORDER BY e.apellido ASC", Student.class).getResultList();
    }

    @Override
    public Student getBy(int nroLibreta) throws Exception {
        return this.entityManager.createQuery("SELECT e FROM Student e WHERE e.nroLibreta = :nroLibreta",
                Student.class).setParameter("nroLibreta", nroLibreta).getSingleResult();
    }

    @Override
    public List getAllBy(String genero) throws Exception {
        return entityManager.createQuery("SELECT e FROM Student e WHERE e.genero = :genero").setParameter("genero", genero).getResultList();
    }

    @Override
    public List<Student> getAllBy(String carrera, String ciudad) throws Exception {
        String jpql = "SELECT e " +
                "FROM Student e " +
                "JOIN e.carrerasInscriptas c " +
                "WHERE c.carrera.nombre = :nombreCarrera " +
                "AND e.ciudad = :ciudad";
        TypedQuery<Student> query =  this.entityManager.createQuery(jpql, Student.class);
        query.setParameter("ciudad", ciudad);
        query.setParameter("nombreCarrera", carrera);
        return query.getResultList();
    }

    public List getCarrerasIscriptas() {
        this.entityManager.getTransaction().begin();
        TypedQuery<Career> query = this.entityManager.createQuery(
                "SELECT DISTINCT r.carrera FROM RelationCareerStudent r",
                Career.class
        );
        return query.getResultList();
    }


}
