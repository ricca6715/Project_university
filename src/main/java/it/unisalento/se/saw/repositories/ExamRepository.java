package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {


	@Query("select t.exams from Teaching t "+ "where t.idTeaching=:idTeaching")
	public List<Exam> getExamsByIdTeaching(@Param("idTeaching") int idTeaching);
	
}
