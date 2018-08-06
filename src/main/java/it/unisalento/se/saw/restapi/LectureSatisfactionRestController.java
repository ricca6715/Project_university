package it.unisalento.se.saw.restapi;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.ILectureSatisfactionService;
import it.unisalento.se.saw.domain.Lecturesatisfaction;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/lecturesatisfaction")
public class LectureSatisfactionRestController {
	
	@Autowired
	ILectureSatisfactionService lsService;

	public LectureSatisfactionRestController(ILectureSatisfactionService lsService) {
		super();
		this.lsService = lsService;
	}

	@GetMapping(
			value = "/getAverageRatingByIdLecture/{idLecture}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Double getAverageRatingByIdLecture(@PathVariable("idLecture") int idLecture) {
		return lsService.getAverageRatingByIdLecture(idLecture);
	}
	
	@GetMapping(
			value = "/getLectureSatisfactionsByIdLecture/{idLecture}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Lecturesatisfaction> getLectureSatisfactionsByIdLecture(@PathVariable("idLecture") int idLecture) {
		return lsService.getLectureSatisfactionsByIdLecture(idLecture);
	}
	
	@GetMapping(
			value = "/getLectureSatisfactionByIdUserAndIdLecture/{idUser}/{idLecture}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Lecturesatisfaction getLectureSatisfactionByIdUserAndIdLecture(@PathVariable("idUser") int idUser, @PathVariable("idLecture") int idLecture) {
		return lsService.getLectureSatisfactionByIdUserAndIdLecture(idUser, idLecture);
	}
	
	
	

}
