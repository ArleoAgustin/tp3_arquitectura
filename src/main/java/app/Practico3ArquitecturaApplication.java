package app;

import app.migration.ReaderCSV;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOError;
import java.io.IOException;

@SpringBootApplication
public class Practico3ArquitecturaApplication {

	@Autowired
	private ReaderCSV loadDates;

	public static void main(String[] args) {
		SpringApplication.run(Practico3ArquitecturaApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException{
		loadDates.loadEstudiantes();
	}
}
