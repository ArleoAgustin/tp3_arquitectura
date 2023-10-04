package app.DTOs;

import app.percistence.entities.Career;
import app.percistence.entities.Student;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO {

    private Long dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private List<String> carrerasInscriptas;

    public List<String> getCarrerasInscriptas() {
        return new ArrayList<>(carrerasInscriptas);
    }

    public StudentDTO(Student student) {
        this.dni = (long) student.getDni();
        this.nombre = student.getNombre();
        this.apellido = student.getApellido();
        this.edad = student.getEdad();
        this.genero = student.getGenero();
        this.ciudad = student.getCiudad();
        this.carrerasInscriptas = new ArrayList<>( student.getCarrerasInscriptas().stream()
                .map(relacion -> relacion.getCarrera().getNombre())
                .collect(Collectors.toList()));

    }

    public Long getDni(){
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
