package app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RelationCareerStudent", uniqueConstraints = @UniqueConstraint(columnNames = { "dni", "idCarrera" }))
public class RelationCareerStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "dni")
    private Student estudiante;

    @ManyToOne
    @JoinColumn(name = "idCarrera")
    private Career carrera;

    private LocalDateTime fechaDeInscripcion;

    private LocalDateTime fechaDeEgreso;


    public RelationCareerStudent(){}

    public RelationCareerStudent(Career carrera, Student estudiante) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaDeInscripcion = LocalDateTime.now();
        this.fechaDeEgreso = null;
    }

    public RelationCareerStudent(Student estudiante, Career carrera, LocalDateTime fechaDeInscripcion, LocalDateTime fechaDeEgreso) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaDeInscripcion = fechaDeInscripcion;
        this.fechaDeEgreso = fechaDeEgreso;
    }

    public Long getId() {
        return id;
    }

    public Student getEstudiante() {
        return estudiante;
    }

    public Career getCarrera() {
        return carrera;
    }

    public LocalDateTime getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(LocalDateTime fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }

    public LocalDateTime getFechaDeEgreso() {
        return fechaDeEgreso;
    }

    public void setFechaDeEgreso() {
        this.fechaDeEgreso = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return  "{carrera: "+carrera.getNombre() +
                " inscripto: " + estudiante.getNombre()  +
                " fechaDeInscripcion: " + fechaDeInscripcion +"}"+ "\n";
    }
}
