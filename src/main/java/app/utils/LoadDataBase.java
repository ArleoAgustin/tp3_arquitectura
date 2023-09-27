package app.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import app.model.Persona;
import app.repository.PersonaRepository;

@Configuration
@Slf4j
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(@Qualifier("personaRepository")PersonaRepository repository){

        return args -> {
            log.info("Preloading " + repository.save(new Persona(41969678L, "Agustin", "Arleo")));
            log.info("Preloading " + repository.save(new Persona(42463358L, "Mauricio", "Macri")));
        };
    }
}
