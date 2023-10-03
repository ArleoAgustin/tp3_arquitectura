package app.service;

import app.percistence.entities.Student;
import app.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service("studentService")
public class StudentService implements Service<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findBy(Long dni){
        return studentRepository.findById(dni).get();
    }

    @Override
    @Transactional
    public List findAll() throws Exception {
        return studentRepository.findAllByOrderByApellidoAsc();
    }

    @Override
    @Transactional
    public boolean save(Student student) throws Exception {
        try {
            studentRepository.save(student);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean update(Long dni, Student student) throws Exception {
        try {
            Optional<Student> entityOpcional = studentRepository.findById(dni);
            Student e = entityOpcional.get();
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

    @Transactional
    public Student findByLibreta(int nroLibreta) throws Exception {
        try{
            Optional<Student> student = Optional.ofNullable(studentRepository.findBynroLibreta(nroLibreta));
            return student.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Student> findByGenre(String genero) throws Exception {
        try {
            return studentRepository.findByGenre(genero);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
