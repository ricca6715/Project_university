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

import it.unisalento.se.saw.Iservices.IMaterialSatisfactionService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.MaterialSatisfactionNotFound;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.models.LecturesatisfactionModel;
import it.unisalento.se.saw.models.MaterialsatisfactionModel;
import it.unisalento.se.saw.models.SatisfactionFactory;

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
	public List<MaterialsatisfactionModel> getMaterialSatisfactionByIdMaterial(@PathVariable("idMaterial") int idMaterial) {
		List<Materialsatisfaction> list = msService.getMaterialSatisfactionByIdMaterial(idMaterial);
		List<MaterialsatisfactionModel> msmlist = new ArrayList<MaterialsatisfactionModel>();
		SatisfactionFactory a = FactoryProducer.getSatisfactionFactory("material");
		for (int i = 0; i < list.size(); i++) {
			MaterialsatisfactionModel m = a.createMaterialFactory(list.get(i).getIdMaterialSatisfaction(),
					list.get(i).getTeachingmaterial(), list.get(i).getUser(), list.get(i).getLevel(),
					list.get(i).getNote());
			msmlist.add(m);
		}
		return msmlist;
	}
	
	@GetMapping(
			value = "/getMaterialSatisfactionByIdUserAndIdMaterial/{idUser}/{idMaterial}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public MaterialsatisfactionModel getMaterialSatisfactionByIdUserAndIdMaterial(@PathVariable("idUser") int idUser, @PathVariable("idMaterial") int idMaterial) throws MaterialSatisfactionNotFound {
		Materialsatisfaction ms = msService.getMaterialSatisfactionByIdUserAndIdMaterial(idUser, idMaterial);
		SatisfactionFactory a = FactoryProducer.getSatisfactionFactory("material");
		MaterialsatisfactionModel m = a.createMaterialFactory(ms.getIdMaterialSatisfaction(), ms.getTeachingmaterial(), ms.getUser(), ms.getLevel(), ms.getNote());
		return m;
	}
	
	@PostMapping(
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Materialsatisfaction saveSatisfaction(@RequestBody MaterialsatisfactionModel materialSatModel) {
		SatisfactionFactory a = FactoryProducer.getSatisfactionFactory("material");
		Materialsatisfaction ms = a.createMaterialDomainFactory(materialSatModel);
		return msService.saveSatisfaction(ms);
	}
	

}
