package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
	
	@Query("select sc.calendars from Studycourse sc where sc.idStudyCourse=:idStudyCourse")
	public List<Calendar> getCalendarsByIdStudycourse(@Param("idStudyCourse") int idStudyCourse );

	
}
