package app.service;

import app.percistence.entities.Estudiante;
import app.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentService implements BaseService<Estudiante>{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public List findAll() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public boolean save(Estudiante student) throws Exception {
        try {
            studentRepository.save(student);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean update(Long dni, Estudiante student) throws Exception {
        try {
            Optional<Estudiante> entityOpcional = studentRepository.findById(dni);
            Estudiante e = entityOpcional.get();
            e = studentRepository.save(student);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long dni) throws Exception {
        try{
            if(studentRepository.existsById(dni)){
                studentRepository.deleteById(dni);
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
    public Estudiante findById(Long dni) throws Exception {
        try{
            Optional<Estudiante> student = studentRepository.findById(dni);
            return student.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
