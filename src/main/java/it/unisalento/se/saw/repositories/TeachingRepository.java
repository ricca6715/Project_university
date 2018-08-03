package it.unisalento.se.saw.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Teaching;

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Integer> {
	
	@Query("select t from Teaching t "
			+ "where t.name=:name ")
	public Teaching getTeachingByName(@Param("name") String name);
	
	
	@Query("select t from Teaching t JOIN  t.studycourses sc "
			+ "where sc.idStudyCourse=:id ")
	public List<Teaching> getTeachingByStudyCourse(@Param("id") int id);
	
	@Query("select t from Teaching t "
			+ "where t.user.idUser=:idProfessor ")
	public List<Teaching> getTeachingsByIdProfessor(@Param("idProfessor") int idProfessor);

	@Query("select u.teachings from User u where u.idUser=:idStudent")
	public List<Teaching> getTeachingsByIdStudent(@Param("idStudent") int idStudent);
	
}
