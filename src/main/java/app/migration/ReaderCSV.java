package app.migration;

import app.percistence.entities.Career;
import app.percistence.entities.RelationCareerStudent;
import app.percistence.entities.Student;
import app.repository.CareerRepository;
import app.repository.StudentRepository;
import app.service.CareerService;
import app.service.StudentService;
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
    private final CareerRepository careerRepository;
    @Autowired
    private final CareerService careerService ;
    @Autowired
    private final StudentService studentService;

    @Autowired
    public ReaderCSV(StudentRepository studentRepository, CareerRepository careerRepository, CareerService careerService, StudentService studentService){
        this.studentRepository = studentRepository;
        this.careerRepository = careerRepository;
        this.careerService = careerService;
        this.studentService = studentService;
    }

    public void loadEstudiantes() throws IOException {
        String fileEstudiantes = "src/main/java/app/migration/CSVs/estudiantes.csv";
        CSVParser file = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudiantes));

        for(CSVRecord row: file) {
            studentRepository.save(new Student(Integer.parseInt(row.get("DNI")), row.get("nombre"), row.get("apellido"), Integer.parseInt(row.get("edad")), row.get("genero"), row.get("ciudad"), Integer.parseInt(row.get("LU"))));
        }
    }

    public void loadCarreras() throws IOException {
        String fileCarrera = "src/main/java/app/migration/CSVs/carreras.csv";
        CSVParser file = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileCarrera));

        for(CSVRecord row: file) {
            careerRepository.save(new Career(row.get("carrera"), Long.parseLong(row.get("id_carrera"))));
        }
    }

    public void loadRelation() throws IOException {
        String fileEstudianteCarrera = "src/main/java/app/migration/CSVs/estudianteCarrera.csv";
        CSVParser file = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudianteCarrera));

        for(CSVRecord row: file) {
            Career c = careerService.findBy(Long.parseLong(row.get("id_carrera")));
            Student e = studentService.findBy(Long.parseLong(row.get("id_estudiante")));
            LocalDateTime inscription = LocalDateTime.of(Integer.parseInt(row.get("inscripcion")), 1, 1, 1, 1);
            LocalDateTime graduation = LocalDateTime.of(Integer.parseInt(row.get("graduacion")), 1, 1, 1, 1);
            RelationCareerStudent relation = new RelationCareerStudent(e, c, inscription, graduation);
            careerService.matricularEstudianteEnCarrera(relation);
        }
    }
}