package it.unisalento.se.saw.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.ITeachingService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/teaching")
public class TeachingRestController {

	@Autowired
	ITeachingService teachingService;

	
	
	public TeachingRestController() {
		super();
	}



	public TeachingRestController(ITeachingService teachingService) {
		
		this.teachingService = teachingService;
	}
	
	
	@GetMapping(
			value = "/getTeachingByName/{name}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Teaching getTeachingByName(@PathVariable("name") String name) {
		return teachingService.getTeachingByName(name);
	}
	
	@GetMapping(
			value = "/getAll",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Teaching> getAll(){
		return teachingService.getAll();
	}
	
	@GetMapping(
			value = "/getTeachingByStudyCourse/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Teaching> getTeachingByStudyCourse(@PathVariable("id") int id){
		return teachingService.getTeachingByStudyCourse(id);
	}
	
	@GetMapping(
			value = "/getTeachingByIdProfessor/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Teaching> getTeachingsByIdProfessor(@PathVariable("id") int idProfessor){
		return teachingService.getTeachingsByIdProfessor(idProfessor);
	}
	
	@GetMapping(
			value = "/getTeachingByIdStudent/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Teaching> getTeachingsByIdStudent(@PathVariable("id") int idStudent){
		return teachingService.getTeachingsByIdStudent(idStudent);
	}
	
}
