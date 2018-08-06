package it.unisalento.se.saw.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Lecturesatisfaction;


@Repository
public interface LectureSatisfactionRepository extends JpaRepository<Lecturesatisfaction, Integer> {

	
	@Query("select avg(ls.level) from Lecturesatisfaction ls where ls.lecture.idLecture=:idLecture")
	public Double getAverageRatingByIdLecture(@Param("idLecture") int idLecture );
	
	@Query("select ls from Lecturesatisfaction ls where ls.lecture.idLecture=:idLecture")
	public List<Lecturesatisfaction> getLectureSatisfactionsByIdLecture(@Param("idLecture") int idLecture );

	@Query("select ls from Lecturesatisfaction ls where ls.user.idUser=:idUser and ls.lecture.idLecture=:idLecture")
	public Lecturesatisfaction getLectureSatisfactionByIdUserAndIdLecture(@Param("idUser")int idUser, @Param("idLecture") int idLecture);

}
