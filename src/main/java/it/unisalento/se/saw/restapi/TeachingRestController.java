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

import it.unisalento.se.saw.Iservices.ITeachingService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.TeachingModel;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/teaching")
public class TeachingRestController {

	@Autowired
	ITeachingService teachingService;

	public TeachingRestController(ITeachingService teachingService) {
		
		this.teachingService = teachingService;
	}
	
	
	@GetMapping(
			value = "/getTeachingByName/{name}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Teaching getTeachingByName(@PathVariable("name") String name) throws TeachingNotFoundException {
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
	

	@PostMapping(
			   value="/save",
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes= MediaType.APPLICATION_JSON_VALUE)
	public Teaching save(@RequestBody TeachingModel tModel) {
		Teaching t = new Teaching();
		User prof = new User();
		HashSet<Studycourse> scourses = new HashSet<>();
		if(tModel.getIdTeaching() != null) {
			t.setIdTeaching(tModel.getIdTeaching());
		}
		t.setName(tModel.getName());
		t.setCfu(tModel.getCfu());
		t.setCourseYear(tModel.getCourseYear());	
		prof.setIdUser(tModel.getUser().getIdUser());
		t.setUser(prof);
		List<StudyCourseModel> studycoursesModel = tModel.getStudycourses();
		if(studycoursesModel != null) {
			for (int i = 0; i < studycoursesModel.size(); i++) {
				Studycourse sc = new Studycourse();
				sc.setIdStudyCourse(studycoursesModel.get(i).getIdStudyCourse());
				sc.setDescription(studycoursesModel.get(i).getDescription());
				sc.setName(studycoursesModel.get(i).getName());
				scourses.add(sc);
			}
			t.setStudycourses(scourses);
		}
		
		return teachingService.save(t);
	}
	
}
