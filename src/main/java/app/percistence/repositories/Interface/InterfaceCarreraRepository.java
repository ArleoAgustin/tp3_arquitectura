package app.percistence.repositories.Interface;


import app.DTOs.ReporteDeCarrerasDTO;
import app.percistence.entities.Career;
import app.percistence.entities.Student;


import java.util.List;

public interface InterfaceCarreraRepository {

    void addCarrera(Career c) throws Exception;

    void deleteCarrera(int id) throws Exception;

    Career getCarrera(int id) throws Exception;

    void updateCarrera(Career c) throws Exception;

    List<Career> getCarreras() throws Exception;

    void matricularACarrera(Student e, Career c) throws Exception;

    List getInscriptosA(Career c) throws Exception;

    List<Career> getWithIscriptosOrderByCant() throws Exception;

    ReporteDeCarrerasDTO getReport();

}
