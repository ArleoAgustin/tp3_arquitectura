package app.percistence.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RelacionCarreraEstudiante", uniqueConstraints = @UniqueConstraint(columnNames = { "dni", "idCarrera" }))
public class RelationCareerStudent {

    @Id
    int id;
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



    //id,id_estudiante,id_carrera,inscripcion,graduacion,antiguedad
    public RelationCareerStudent(int id, Student estudiante, Career carrera, LocalDateTime fechaDeInscripcion, LocalDateTime fechaDeEgreso) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fechaDeInscripcion = fechaDeInscripcion;
        this.fechaDeEgreso = fechaDeEgreso;
    }

    public int getId() {
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
