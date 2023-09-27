package app.repository;

import app.percistence.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Carrera, Long> {
}
