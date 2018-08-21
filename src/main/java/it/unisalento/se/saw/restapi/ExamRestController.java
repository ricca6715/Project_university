package it.unisalento.se.saw.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IExamService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.models.ExamModel;

@CrossOrigin
@RestController()
@RequestMapping("/exam")
public class ExamRestController {
	
	@Autowired
	IExamService examService;

	public ExamRestController(IExamService examService) {
		this.examService = examService;
	}
	
	@PostMapping(
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Exam save(@RequestBody ExamModel examModel) {
		Exam e = new Exam();
		Classroom cls = new Classroom();
		Teaching t = new Teaching();
		if(examModel.getIdExam() != null)
			e.setIdExam(examModel.getIdExam());
		e.setDate(examModel.getDate());
		e.setHour(examModel.getHour());
		cls.setIdClassroom(examModel.getClassroom().getIdClassroom());
		e.setClassroom(cls);
		t.setIdTeaching(examModel.getTeaching().getIdTeaching());
		e.setTeaching(t);
		return examService.save(e);
	}
	

}
