package app.percistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dni")
public class Student {

    @Id
    private int dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;
    @Column
    private int edad;

    @Column
    private String genero;


    @Column
    private String ciudad;

    @Column(name = "nro_libreta")
    private int nroLibreta;

    @OneToMany(mappedBy = "estudiante")
    private List<RelationCareerStudent> carrerasInscriptas;

    public Student() {}

    public Student(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int nroLibreta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.nroLibreta = nroLibreta;
        this.genero = genero;
        this.carrerasInscriptas = new ArrayList<>();
    }


    public int getDni() {
        return dni;
    }

    public String getGenero() {
        return genero;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(int nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public List<RelationCareerStudent> getCarrerasInscriptas() {
        return carrerasInscriptas;
    }

    public void addCarrera(Career c) {
        RelationCareerStudent carreraInscripta = new RelationCareerStudent(c, this);
        this.carrerasInscriptas.add(carreraInscripta);
    }

    public void addCarrera(RelationCareerStudent carreraInscripta) {
        this.carrerasInscriptas.add(carreraInscripta);
    }

    public void setCarrerasInscriptas(List<RelationCareerStudent> carrerasInscriptas) {
        this.carrerasInscriptas = carrerasInscriptas;
    }

    private String obtenerListaCarreras() {
        String result = "";
        if(this.carrerasInscriptas != null) {
            for (RelationCareerStudent carrera : this.carrerasInscriptas) {
                result += carrera.getCarrera().getNombre() + ", ";
            }
            return result;
        } else {
            return "ninguna";
        }
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "dni= " + dni +
                ", nombre= " + nombre +
                ", apellido= " + apellido +
                ", edad= " + edad +
                ", genero= "+genero+
                ", ciudad= " + ciudad +
                ", nroLibreta= " + nroLibreta +
                ", carrerasInscriptas = " + this.obtenerListaCarreras() +
                '}'+'\n';
    }
}
