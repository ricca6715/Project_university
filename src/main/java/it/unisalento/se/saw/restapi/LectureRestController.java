package it.unisalento.se.saw.restapi;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.ILectureService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.exceptions.LectureNotFoundException;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/lecture")
public class LectureRestController {

	@Autowired
	ILectureService lectureService;

	public LectureRestController(ILectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	public LectureRestController() {
		super();
	}
	
	
	@GetMapping(
			value = "/getLecturesByDate/{date}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Lecture> getLecturesByDate(@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("date") Date date) {
		return lectureService.getLecturesByDate(date);
	}
	
	@GetMapping(
			value = "/getAll",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Lecture> getAll() {
		return lectureService.getAll();
	}
	
	
	@GetMapping(
			value = "/getLectureById/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture getLectureById(@PathVariable("id") int id) throws LectureNotFoundException {
		
		
		return lectureService.getLectureById(id);
	}
	
	
	@GetMapping(
			value = "/getLectureByClassroom/{idClassroom}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lecture> getLectureByClassroom(@PathVariable("idClassroom") int idClassroom){
		return lectureService.getLecturesByClassroom(idClassroom);
	}
	
	
	@GetMapping(
			value = "/getLecturesByIdTeaching/{idTeaching}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lecture> getLecturesByIdTeaching(@PathVariable("idTeaching") int idTeaching){
		return lectureService.getLecturesByIdTeaching(idTeaching);
	}
	
	@GetMapping(
			value = "/getDailyLectureByIdProfAndDate/{idUser}/{date}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lecture> getDailyLectureByIdProfAndDate(@PathVariable("idUser") int idUser, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") Date date){
		return lectureService.getDailyLectureByIdProfAndDate(idUser, date);
	}
	
}
