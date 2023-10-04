package app.repository;

import app.model.RelationCareerStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RelationCareerStudentRepository extends JpaRepository<RelationCareerStudent, Long> {}
