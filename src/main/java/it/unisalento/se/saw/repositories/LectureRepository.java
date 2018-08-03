package it.unisalento.se.saw.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.User;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

	@Query("select l from Lecture l "
			+ "where l.date=:date ")
	public List<Lecture> getLecturesByDate(@Param("date") Date date );

	@Query("select l from Lecture l "
			+ "where l.idLecture=:id")
	public Lecture getLectureById(@Param("id") int id);
	
	@Query("select l from Lecture l "
			+ "where l.classroom.idClassroom=:idClassroom")
	public List<Lecture> getLecturesByClassroom(@Param("idClassroom" ) int idClassroom);
	
	@Query("select l from Lecture l "
			+ "where l.teaching.idTeaching=:idTeaching")
	public List<Lecture> getLecturesByIdTeaching(@Param("idTeaching" ) int idTeaching);
	

	@Query("select l from Lecture l "
			+ "where l.teaching.user.idUser=:idUser and l.date=:date ")
	public List<Lecture> getDailyLectureByIdProfAndDate(@Param("idUser") int idUser, @Param("date") Date date);
	
}
