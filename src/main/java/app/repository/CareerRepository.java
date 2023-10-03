package app.repository;

import app.percistence.entities.Career;
import app.percistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {}
