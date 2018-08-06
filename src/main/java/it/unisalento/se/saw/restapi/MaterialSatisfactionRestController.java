package it.unisalento.se.saw.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IMaterialSatisfactionService;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;

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
	

}
