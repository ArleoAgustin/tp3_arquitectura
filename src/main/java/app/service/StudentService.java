package app.service;

import app.DTOs.StudentDTO;
import app.model.Career;
import app.model.Student;
import app.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service("studentService")
public class StudentService implements Service<Student> {

    @Autowired
    private StudentRepository studentRepository;


    public Student findBy(Long dni) {

        Optional<Student> studentOptional = studentRepository.findById(dni);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new EntityNotFoundException("Estudiante no encontrado con DNI: " + dni);
        }
    }
    @Override
    @Transactional
    public Optional findAll() throws Exception {
        try {
            List<StudentDTO> studentDTOs = this.converToStudentDTO(studentRepository.findAllByOrderByApellidoAsc());
            return Optional.ofNullable(studentDTOs);
        }catch (Exception e){
            throw new Exception("Error al buscar todos los estudiantes: " + e.getMessage(), e);
        }
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
            Optional<Student> entityOptional = studentRepository.findById(dni);
            if (entityOptional.isPresent()) {
                Student existingStudent = entityOptional.get();
                existingStudent.setNombre(student.getNombre());
                existingStudent.setApellido(student.getApellido());
                existingStudent.setEdad(student.getEdad());
                existingStudent.setCiudad(student.getCiudad());
                existingStudent.setGenero(student.getGenero());
                existingStudent.setNroLibreta(student.getNroLibreta());
                studentRepository.save(existingStudent);
                return true;
            } else
                throw new Exception("Estudiante no encontrado con DNI: " + dni);
        } catch (Exception e) {
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
                throw new Exception("No se encontró ningún estudiante con DNI: " + dni);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public StudentDTO findByLibreta(int nroLibreta) throws Exception {
        StudentDTO student = studentRepository.findBynroLibreta(nroLibreta);
        if (student == null) {
            throw new Exception("No se encontró ningún estudiante con número de libreta: " + nroLibreta);
        }
        return student;
    }

    @Transactional
    public Optional findByGenre(String genero) throws Exception {
        try {
            List<StudentDTO> studentDTOs = this.converToStudentDTO(studentRepository.findByGenre(genero));
            return Optional.ofNullable(studentDTOs);
        } catch (DataAccessException e) {
            throw new Exception("Error al buscar estudiantes por género: " + e.getMessage(), e);
        }
    }


    public Optional getAllBy(String career, String city) throws Exception {
        try {
            List<StudentDTO> studentsDTOs = this.converToStudentDTO(studentRepository.getAllBy(career, city));
            return Optional.ofNullable(studentsDTOs);
        } catch (DataAccessException e) {
            throw new Exception("Error al buscar estudiantes por carrera y ciudad: " + e.getMessage(), e);
        }
    }

    private List<StudentDTO> converToStudentDTO(List<Student> students){

        List<StudentDTO> result = new ArrayList<>();
        students.forEach(student -> {
            StudentDTO studentDTO = new StudentDTO(student);
            result.add(studentDTO);
        });
        return result;
    }


}
