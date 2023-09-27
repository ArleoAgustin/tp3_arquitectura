package app.percistence.connection;

import org.springframework.context.annotation.Bean;
import percistence.repositories.CarreraRepository;
import percistence.repositories.EstudianteRepository;

@Bean
public class RepositoryFactory {

    public RepositoryFactory() {}

    public CarreraRepository getInstanceCarreraRepository(){
        return  CarreraRepository.getInstance();
    }

    public EstudianteRepository getInstanceEstudianteRepository(){
        return  EstudianteRepository.getInstance();
    }
}
