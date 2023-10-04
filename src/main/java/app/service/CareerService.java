package app.service;


import app.DTOs.*;
import app.model.Career;
import app.model.RelationCareerStudent;
import app.model.Student;
import app.repository.CareerRepository;
import app.repository.RelationCareerStudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.*;

@org.springframework.stereotype.Service("careerService")

public class CareerService implements Service<Career> {

    @Autowired
    private CareerRepository careerRepository;
    private RelationCareerStudentRepository relationRepository;

    @Autowired
    public CareerService(RelationCareerStudentRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    public CareerService() {}

    public boolean matricularEstudianteEnCarrera(Career carrera, Student estudiante) {
        if (carrera == null || estudiante == null) {
            throw new IllegalArgumentException("Carrera y estudiante no pueden ser nulos.");
        }
        RelationCareerStudent relation = new RelationCareerStudent(carrera, estudiante);
        relationRepository.save(relation);
        return true;
    }

    public boolean matricularEstudianteEnCarrera(RelationCareerStudent rcs) {
        relationRepository.save(rcs);
        return true;
    }

    @Override
    public Career findBy(Long id_career) {

        Optional<Career> careerOptional = careerRepository.findById(id_career);
        if (careerOptional.isPresent()) {
            return careerOptional.get();
        } else {
            throw new EntityNotFoundException("Carrera no encontrada con id: " + id_career);
        }
    }

    @Override
    @Transactional
    public Optional<List<CareerDTO>> findAll() {
        List<CareerDTO> careers = this.converToCareerDTO(careerRepository.findAll());
        return Optional.ofNullable(careers);
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
    @Transactional
    public boolean update(Long id, Career career) throws Exception {
        try {
            Optional<Career> entityOptional = careerRepository.findById(id);
            if (entityOptional.isPresent()) {
                Career existingCareer = entityOptional.get();
                existingCareer.setNombre(career.getNombre());
                existingCareer.setAntiguedad(career.getAntiguedad());
                careerRepository.save(existingCareer);
                return true;
            } else {
                throw new ChangeSetPersister.NotFoundException();
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar la carrera: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if (careerRepository.existsById(id)) {
                careerRepository.deleteById(id);
                return true;
            } else {
                throw new ChangeSetPersister.NotFoundException();
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar la carrera: " + e.getMessage());
        }
    }

    @Transactional
    public Optional findWithIscriptosOrderByCant() throws Exception {
        try {
            List<CareerDTO> careerDTOS = this.converToCareerDTO(careerRepository.getWithIscriptosOrderByCant());
            Optional<List<CareerDTO>> result = Optional.ofNullable(careerDTOS);
            if(result.isPresent()){
                return result;
            }else {
                throw new Exception("No se encontraron carreras con alumnos matriculados");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

//////reporte dto

    public Optional<ReporteDeCarrerasDTO> getReport(){

        List<Object[]> result = careerRepository.getReport();
        List<ObjectRelationDTO> data = new ArrayList<>();
        for (Object[] res: result){
            data.add(new ObjectRelationDTO((Career)res[0],(Student)res[2],(RelationCareerStudent)res[1]));
        }
        TreeMap<String, CarreraReporteDTO> careers = new TreeMap<>();
        for (ObjectRelationDTO objectRelationDTO: data){
            String careerName = objectRelationDTO.getCarrera().getNombre();
            if (!careers.containsKey(objectRelationDTO.getCarrera().getNombre())){
                careers.put(careerName, new CarreraReporteDTO((careerName)));
            }
            careers.get(careerName).addIngresante(new EstudianteReporteDTO(objectRelationDTO.getEstudiante().getDni(),objectRelationDTO.getEstudiante().getNombre(), objectRelationDTO.getEstudiante().getApellido()) , objectRelationDTO.getRelacionCarreraEstudiante().getFechaDeInscripcion().getYear());
            if(objectRelationDTO.getRelacionCarreraEstudiante().getFechaDeEgreso() != null) {
                careers.get(careerName).addEgresado(new EstudianteReporteDTO(objectRelationDTO.getEstudiante().getDni(), objectRelationDTO.getEstudiante().getNombre(), objectRelationDTO.getEstudiante().getApellido()), objectRelationDTO.getRelacionCarreraEstudiante().getFechaDeEgreso().getYear());
            }
        }
        List<CarreraReporteDTO> carrers = new ArrayList<>(careers.values());
        return Optional.of(new ReporteDeCarrerasDTO(carrers));
    }

    private List<CareerDTO> converToCareerDTO(List<Career> careers){

        List<CareerDTO> result = new ArrayList<>();
        careers.forEach(career -> {
            CareerDTO careerDTO = new CareerDTO(career);
            result.add(careerDTO);
        });
        return result;
    }

}
