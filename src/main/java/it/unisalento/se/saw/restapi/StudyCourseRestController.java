package it.unisalento.se.saw.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IStudyCourseService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.exceptions.StudycourseNotFoundException;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/studycourse")
public class StudyCourseRestController {
	
	@Autowired
	IStudyCourseService scService;
	
	//constructors//////////////////////////////////
	public StudyCourseRestController() {
		super();
	}
	
	public StudyCourseRestController(IStudyCourseService scService) {
		this.scService = scService;
	}
	

	//////////////////////////////////////////////
	
	
	
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
	

	

}
