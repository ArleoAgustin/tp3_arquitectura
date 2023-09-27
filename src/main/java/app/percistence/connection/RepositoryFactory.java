package app.percistence.connection;

import app.percistence.repositories.CarreraRepository;
import app.percistence.repositories.EstudianteRepository;
import org.springframework.context.annotation.Bean;


public class RepositoryFactory {

    public RepositoryFactory() {}

    public CarreraRepository getInstanceCarreraRepository(){
        return  CarreraRepository.getInstance();
    }

    public EstudianteRepository getInstanceEstudianteRepository(){
        return  EstudianteRepository.getInstance();
    }
}
