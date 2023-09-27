package app.migration;

import app.percistence.entities.Estudiante;
import app.repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ReaderCSV {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    public ReaderCSV(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void loadEstudiantes() throws IOException {

        String fileEstudiantes = "src/main/java/app/migration/CSVs/estudiantes.csv";
        CSVParser file = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudiantes));

        for(CSVRecord row: file) {
            studentRepository.save(new Estudiante(Integer.parseInt(row.get("DNI")), row.get("nombre"), row.get("apellido"), Integer.parseInt(row.get("edad")), row.get("genero"), row.get("ciudad"), Integer.parseInt(row.get("LU"))));
        }
    }
/*
    public void loadCarreras() throws Exception {
        String fileCarrera = "src/main/java/app/migration/CSVs/carreras.csv";
        CSVParser file = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileCarrera));

        for(CSVRecord row: file) {
            carreraRepository.addCarrera(new Carrera(row.get("carrera"),Integer.parseInt(row.get("id_carrera"))));
        }
    }

    public void loadRelation() throws Exception {
        String fileEstudianteCarrera = "src/main/app/java/migration/CSVs/estudianteCarrera.csv";
        CSVParser file = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudianteCarrera));
        EstudianteRepository  estudianteRepository = repositoryFactory.getInstanceEstudianteRepository();
        CarreraRepository  carreraRepository = repositoryFactory.getInstanceCarreraRepository();

        for(CSVRecord row: file) {
            Carrera c = carreraRepository.getCarrera(Integer.parseInt(row.get("id_carrera")));
            percistence.entities.Estudiante e = estudianteRepository.obtenerPorId(Integer.parseInt(row.get("id_estudiante")));
            RelacionCarreraEstudiante rce = new RelacionCarreraEstudiante(Integer.parseInt(row.get("id")),
                    e, c, LocalDateTime.of(Integer.parseInt(row.get("inscripcion")),
                    1, 1, 1, 1),
                    LocalDateTime.of(Integer.parseInt(row.get("graduacion")),
                            1, 1, 1, 1));
            estudianteRepository.addEstudianteToCarrera(rce);
        }
    }*/
}