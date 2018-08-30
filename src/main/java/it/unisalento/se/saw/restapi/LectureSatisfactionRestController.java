package it.unisalento.se.saw.restapi;


import java.util.ArrayList;
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

import it.unisalento.se.saw.Iservices.ILectureSatisfactionService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.LectureSatisfactionNotFound;
import it.unisalento.se.saw.models.ClassroomModel;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.models.LectureModel;
import it.unisalento.se.saw.models.LecturesatisfactionModel;
import it.unisalento.se.saw.models.SatisfactionFactory;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.TeachingModel;
import it.unisalento.se.saw.models.UserModel;
import it.unisalento.se.saw.models.UserTypeModel;

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
	public List<LecturesatisfactionModel> getLectureSatisfactionsByIdLecture(@PathVariable("idLecture") int idLecture) {
		List<Lecturesatisfaction> list = lsService.getLectureSatisfactionsByIdLecture(idLecture);
		List<LecturesatisfactionModel> lsmlist = new ArrayList<LecturesatisfactionModel>();
		SatisfactionFactory a = FactoryProducer.getSatisfactionFactory("lecture");
		for (int i = 0; i < list.size(); i++) {
			LecturesatisfactionModel l = a.createLectureFactory(list.get(i).getIdlectureSatisfaction(),
					list.get(i).getLecture(), list.get(i).getUser(), list.get(i).getLevel(), 
					list.get(i).getNote());
			lsmlist.add(l);
		}
		return lsmlist;
	}
	
	@GetMapping(
			value = "/getLectureSatisfactionByIdUserAndIdLecture/{idUser}/{idLecture}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public LecturesatisfactionModel getLectureSatisfactionByIdUserAndIdLecture(@PathVariable("idUser") int idUser, @PathVariable("idLecture") int idLecture) throws LectureSatisfactionNotFound {
		Lecturesatisfaction ls = lsService.getLectureSatisfactionByIdUserAndIdLecture(idUser, idLecture);
		SatisfactionFactory a = FactoryProducer.getSatisfactionFactory("lecture");
		LecturesatisfactionModel l = a.createLectureFactory(ls.getIdlectureSatisfaction(),ls.getLecture(), ls.getUser(), ls.getLevel(), ls.getNote());
		return l;
	}
	
	
	@PostMapping(
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Lecturesatisfaction saveSatisfaction(@RequestBody LecturesatisfactionModel lectureSatModel) {
		SatisfactionFactory a = FactoryProducer.getSatisfactionFactory("lecture");
		Lecturesatisfaction ls = a.createLectureDomainFactory(lectureSatModel);
		return lsService.saveSatisfaction(ls);
	}
	
	
	

}
