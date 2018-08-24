package it.unisalento.se.saw.restapi;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IStudyCourseService;
import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.exceptions.StudycourseNotFoundException;
import it.unisalento.se.saw.models.CalendarModel;
import it.unisalento.se.saw.models.StudyCourseModel;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/studycourse")
public class StudyCourseRestController {
	
	@Autowired
	IStudyCourseService scService;
	
	public StudyCourseRestController(IStudyCourseService scService) {
		this.scService = scService;
	}
	
	@GetMapping(
			value = "/getStudycourseByName/{name}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Studycourse getStudycourseByName(@PathVariable("name") String name) throws StudycourseNotFoundException {
		return scService.getStudycourseByName(name);
	}
	
	
	@GetMapping(
			value = "/getAll",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Studycourse> getAll() {
		return scService.getAll();
	}
	
	@PostMapping(
			   value="/save",
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes= MediaType.APPLICATION_JSON_VALUE)
	public Studycourse save(@RequestBody StudyCourseModel scModel) {
		Studycourse sc = new Studycourse();
		if(scModel.getIdStudyCourse() != null )
			sc.setIdStudyCourse(scModel.getIdStudyCourse());
		sc.setDescription(scModel.getDescription());
		sc.setName(scModel.getName());
		
		List<CalendarModel> calendarsModel = scModel.getCalendars();
		HashSet<Calendar> calendarsSet = new HashSet<>();
		if (calendarsModel != null) {
			for (int i = 0; i < calendarsModel.size(); i++) {
				Calendar c = new Calendar();
				c.setIdCalendar(calendarsModel.get(i).getIdCalendar());
				c.setAcademicYear(calendarsModel.get(i).getAcademicYear());
				
				calendarsSet.add(c);
			}
			sc.setCalendars(calendarsSet);
		}
		
		return scService.save(sc);
	}

	
	@GetMapping(
			value = "/getStudycoursesByIdTeaching/{idTeaching}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Studycourse> getStudycoursesByIdTeaching(@PathVariable("idTeaching") int idTeaching) {
		return scService.getStudycourseByIdTeaching(idTeaching);
	}

}
