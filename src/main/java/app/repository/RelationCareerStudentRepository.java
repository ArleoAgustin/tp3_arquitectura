package app.repository;

import app.percistence.entities.Career;
import app.percistence.entities.RelationCareerStudent;
import app.percistence.entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationCareerStudentRepository extends JpaRepository<RelationCareerStudent, Long> {}
