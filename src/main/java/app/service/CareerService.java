package app.service;


import app.percistence.entities.Career;
import app.percistence.entities.Student;
import app.repository.CareerRepository;
import app.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("careerService")
public class CareerService implements BaseService<Career> {

    @Autowired
    private CareerRepository careerRepository;

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

    @Override
    @Transactional
    public Career findById(Long id) throws Exception {
        try{
            Optional<Career> career = careerRepository.findById(id);
            return career.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
