package it.unisalento.se.saw.restapi;

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

import it.unisalento.se.saw.Iservices.ICalendarService;
import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.models.CalendarModel;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/calendar")
public class CalendarRestController {
	
	@Autowired
	ICalendarService calService;

	public CalendarRestController(ICalendarService calService) {
		super();
		this.calService = calService;
	}
	
	@PostMapping(
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Calendar save(@RequestBody CalendarModel calModel) {
		Calendar c = new Calendar();
		if(calModel.getIdCalendar() != null)
			c.setIdCalendar(calModel.getIdCalendar());
		c.setAcademicYear(calModel.getAcademicYear());
		
		return calService.save(c);
	}
	
	@GetMapping(
			value = "/getAll",
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Calendar> getAll() {
		List<Calendar> calendars =  calService.getAll();
		System.out.println(calendars.get(0).getStudycourses().size());
		
		return calendars;
	}
	
	@GetMapping(
			value = "/getCalendarsByIdStudycourse/{idStudycourse}",
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Calendar> getCalendarsByIdStudycourse(@PathVariable("idStudycourse") int idStudycourse) {
		return calService.getCalendarsByIdStudycourse(idStudycourse);
	}

}
