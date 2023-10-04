package app.DTOs;

import app.percistence.entities.Career;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CareerDTO {

    private Long idCarrera;
    private String nombre;
    private List<String> inscriptos;

    public CareerDTO(Career career) {
        this.idCarrera = career.getIdCarrera();
        this.nombre = career.getNombre();
        this.inscriptos = new ArrayList<>(career.getInscriptos().stream()
                .map(relacion -> relacion.getEstudiante().getNombre() + " " + relacion.getEstudiante().getApellido())
                .collect(Collectors.toList()));
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(List<String> inscriptos) {
        this.inscriptos = inscriptos;
    }
}
