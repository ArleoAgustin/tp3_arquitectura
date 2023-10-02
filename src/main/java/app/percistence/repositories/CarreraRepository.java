package app.percistence.repositories;



import app.DTOs.CarreraReporteDTO;
import app.DTOs.EstudianteReporteDTO;
import app.DTOs.ObjectRelationDTO;
import app.DTOs.ReporteDeCarrerasDTO;
import app.percistence.entities.Career;
import app.percistence.entities.Student;
import app.percistence.entities.RelationCareerStudent;
import app.percistence.repositories.Interface.InterfaceCarreraRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CarreraRepository implements InterfaceCarreraRepository {

    private EntityManager entityManager;
    private static CarreraRepository instance;

    private CarreraRepository(){
        //this.entityManager = app.percistence.connection.EntityManager.getEntityManager();
    }

    public static CarreraRepository getInstance(){
        if (instance == null) {
            instance = new CarreraRepository();
        }
        return instance;
    }

    @Override
    public void addCarrera(Career c) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCarrera(int id) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Career.class, id));
        entityManager.getTransaction().commit();
    }

    @Override
    public Career getCarrera(int id) throws Exception {
        return entityManager.find(Career.class, id);
    }

    @Override
    public void updateCarrera(Career c) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.merge(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public List getCarreras() throws Exception {
        return  entityManager.createQuery("SELECT p FROM Career p").getResultList();
    }

    @Override
    public void matricularACarrera(Student e, Career c) throws Exception {
        RelationCareerStudent rce = new RelationCareerStudent(c, e);
        entityManager.getTransaction().begin();
        entityManager.persist(rce);
        entityManager.getTransaction().commit();
    }

    public List getInscriptosA(Career c) throws Exception{
        return this.entityManager.createQuery("SELECT r FROM RelationCareerStudent r WHERE r.carrera = " + c.getIdCarrera()).getResultList();
    }

    @Override
    public List<Career> getWithIscriptosOrderByCant() throws Exception {
        String jpql = "SELECT c " +
                "FROM Career c " +
                "WHERE SIZE(c.inscriptos) > 0 " +
                "ORDER BY SIZE(c.inscriptos) DESC";
        return this.entityManager.createQuery(jpql, Career.class).getResultList();
    }

    public ReporteDeCarrerasDTO getReport(){
        String jpql = "SELECT c, r, e " +
                "FROM Career c " +
                "INNER JOIN c.inscriptos r " +
                "INNER JOIN r.estudiante e";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> resultados = query.getResultList();
        List<ObjectRelationDTO> data = new ArrayList<>();
        for (Object[] resultado : resultados){
            data.add(new ObjectRelationDTO((Career)resultado[0], (Student)resultado[2], (RelationCareerStudent)resultado[1]));
        }
        TreeMap<String, CarreraReporteDTO> carreras = new TreeMap<>();
        for (ObjectRelationDTO objectRelationDTO : data) {
            String nombreCarrera = objectRelationDTO.getCarrera().getNombre();
            if(!carreras.containsKey(objectRelationDTO.getCarrera().getNombre())){
                carreras.put(nombreCarrera, new CarreraReporteDTO(nombreCarrera));
            }
            carreras.get(nombreCarrera).addIngresante(new EstudianteReporteDTO(objectRelationDTO.getEstudiante().getDni(), objectRelationDTO.getEstudiante().getNombre(), objectRelationDTO.getEstudiante().getApellido()) , objectRelationDTO.getRelacionCarreraEstudiante().getFechaDeInscripcion().getYear());
            if(objectRelationDTO.getRelacionCarreraEstudiante().getFechaDeEgreso() != null) {
                carreras.get(nombreCarrera).addEgresado(new EstudianteReporteDTO(objectRelationDTO.getEstudiante().getDni(), objectRelationDTO.getEstudiante().getNombre(), objectRelationDTO.getEstudiante().getApellido()), objectRelationDTO.getRelacionCarreraEstudiante().getFechaDeEgreso().getYear());
            }
        }
        return new ReporteDeCarrerasDTO(new ArrayList<>(carreras.values()));
    }

}
