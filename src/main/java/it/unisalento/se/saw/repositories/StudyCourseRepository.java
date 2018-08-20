package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Studycourse;

@Repository
public interface StudyCourseRepository extends JpaRepository<Studycourse, Integer> {

	@Query("select sc from Studycourse sc "
			+ "where sc.name=:name ")
	public Studycourse getStudycourseByName(@Param("name") String name );

}
