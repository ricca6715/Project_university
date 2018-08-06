package it.unisalento.se.saw.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IClassroomService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.models.ClassroomModel;

@CrossOrigin
@RestController()
@RequestMapping("/classroom")
public class ClassroomRestController {
	
	@Autowired
	IClassroomService classroomService;

	public ClassroomRestController(IClassroomService classroomService) {
		super();
		this.classroomService = classroomService;
	}
	
	@PostMapping(
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Classroom saveClassroom(@RequestBody ClassroomModel classroomModel) {
		Classroom cls = new Classroom();
		cls.setName(classroomModel.getName());
		cls.setDescription(classroomModel.getDescription());
		return classroomService.save(cls);
	}
	
	@GetMapping(
			value = "/getAll",
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Classroom> getAll() {
		return classroomService.getAll();
	}

}
