package app;
import app.utils.LoadData;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import java.io.IOException;

@SpringBootApplication
@EntityScan("app.model")
public class Practico3ArquitecturaApplication {

	@Autowired
	private LoadData loadDates;

	public static void main(String[] args) {
		SpringApplication.run(Practico3ArquitecturaApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		loadDates.loadEstudiantes();
		loadDates.loadCarreras();
		loadDates.loadRelation();
	}
}
