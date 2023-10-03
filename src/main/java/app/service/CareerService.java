package app.service;


import app.percistence.entities.Career;
import app.percistence.entities.RelationCareerStudent;
import app.percistence.entities.Student;
import app.repository.CareerRepository;
import app.repository.RelationCareerStudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service("careerService")

public class CareerService implements Service<Career> {

    @Autowired
    private CareerRepository careerRepository;
    private RelationCareerStudentRepository relationRepository;

    @Autowired
    public CareerService(RelationCareerStudentRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Transactional
    public boolean matricularEstudianteEnCarrera(Career carrera, Student estudiante) throws Exception {
        try {
            RelationCareerStudent relacion = new RelationCareerStudent(carrera, estudiante);
            relacion.setFechaDeInscripcion(LocalDateTime.now());
            relationRepository.save(relacion);
            return true;
        }catch (Exception e){
        throw new Exception(e.getMessage());
    }
    }

    @Override
    public Career findBy(Long id_career) {
        return  careerRepository.findById(id_career).get();
    }



    @Override
    @Transactional
    public List findAll() throws Exception {
        return careerRepository.findAll();
    }

    @Override
    @Transactional
    public boolean save(Career career) throws Exception {
        try {
            careerRepository.save(career);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean update(Long id, Career career) throws Exception {
        try {
            Optional<Career> entityOpcional = careerRepository.findById(id);
            Career e = entityOpcional.get();
            e = careerRepository.save(career);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if(careerRepository.existsById(id)){
                careerRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
