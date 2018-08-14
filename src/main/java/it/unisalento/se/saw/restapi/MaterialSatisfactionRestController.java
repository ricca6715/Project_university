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

import it.unisalento.se.saw.Iservices.IMaterialSatisfactionService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.MaterialSatisfactionNotFound;
import it.unisalento.se.saw.models.LecturesatisfactionModel;
import it.unisalento.se.saw.models.MaterialsatisfactionModel;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/materialsatisfaction")
public class MaterialSatisfactionRestController {
	
	@Autowired
	IMaterialSatisfactionService msService;
	
	public MaterialSatisfactionRestController(IMaterialSatisfactionService msService) {
		super();
		this.msService = msService;
	}

	@GetMapping(
			value = "/getAverageRatingByIdMaterial/{idMaterial}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Double getAverageRatingByIdMaterial(@PathVariable("idMaterial") int idMaterial) {
		return msService.getAverageRatingByIdMaterial(idMaterial);
	}
	
	@GetMapping(
			value = "/getMaterialSatisfactionByIdMaterial/{idMaterial}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Materialsatisfaction> getMaterialSatisfactionByIdMaterial(@PathVariable("idMaterial") int idMaterial) {
		return msService.getMaterialSatisfactionByIdMaterial(idMaterial);
	}
	
	@GetMapping(
			value = "/getMaterialSatisfactionByIdUserAndIdMaterial/{idUser}/{idMaterial}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Materialsatisfaction getMaterialSatisfactionByIdUserAndIdMaterial(@PathVariable("idUser") int idUser, @PathVariable("idMaterial") int idMaterial) throws MaterialSatisfactionNotFound {
		return msService.getMaterialSatisfactionByIdUserAndIdMaterial(idUser, idMaterial);
	}
	
	@PostMapping(
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Materialsatisfaction saveSatisfaction(@RequestBody MaterialsatisfactionModel materialSatModel) {
		Materialsatisfaction ms = new Materialsatisfaction();
		if(materialSatModel.getIdMaterialSatisfaction() != null)
			ms.setIdMaterialSatisfaction(materialSatModel.getIdMaterialSatisfaction());
		ms.setLevel(materialSatModel.getLevel());
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(materialSatModel.getTeachingmaterial().getIdTeachingMaterial());
		ms.setTeachingmaterial(tm);
		User user = new User();
		user.setIdUser(materialSatModel.getUser().getIdUser());
		ms.setUser(user);
		ms.setNote(materialSatModel.getNote());
		
		return msService.saveSatisfaction(ms);
	}
	

}
