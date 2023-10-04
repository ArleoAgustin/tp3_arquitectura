package app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCarrera")
public class Career {

    @Id
    private Long idCarrera;
    @OneToMany(mappedBy = "carrera")
    private List<RelationCareerStudent> inscriptos;

    @Column(nullable = false, unique=true)
    private String nombre;



    @Column
    private int antiguedad;

    public Career(){}

    public Career(String nombre) {
        this.nombre = nombre;
    }

    public Career(String nombre, Long idCarrera) {
        this.nombre = nombre;
        this.idCarrera = idCarrera;
    }

    public Career(String nombre, Long idCarrera, int antiguedad) {
        this.nombre = nombre;
        this.idCarrera = idCarrera;
        this.antiguedad = antiguedad;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public List<RelationCareerStudent> getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(List<RelationCareerStudent> inscriptos) {
        this.inscriptos = inscriptos;
    }

    public void matricularEstudiante(Student e) {
        RelationCareerStudent inscripto = new RelationCareerStudent(this, e);
        this.inscriptos.add(inscripto);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    private String getEstudiantesInscriptos(){
        if(this.inscriptos == null || this.inscriptos.size() == 0) return "no hay inscritos";
        String estudiantes = "";
        for (RelationCareerStudent inscripto : inscriptos) {
            estudiantes += inscripto.getEstudiante().getApellido() + " " + inscripto.getEstudiante().getNombre() + ", ";
        }
        return estudiantes;
    }


    @Override
    public String toString() {
        return "Carrera{" +nombre +
                ", inscriptos = " + this.getEstudiantesInscriptos() +
                '}'+'\n';
    }
}
