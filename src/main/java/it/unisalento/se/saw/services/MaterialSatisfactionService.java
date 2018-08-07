package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IMaterialSatisfactionService;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.repositories.MaterialSatisfactionRepository;

@Service
public class MaterialSatisfactionService implements IMaterialSatisfactionService {
	
	@Autowired
	MaterialSatisfactionRepository msRepository;

	@Override
	public Double getAverageRatingByIdMaterial(int idMaterial) {
		return msRepository.getAverageRatingByIdMaterial(idMaterial);
	}

	@Override
	public List<Materialsatisfaction> getMaterialSatisfactionByIdMaterial(int idMaterial) {
		return msRepository.getMaterialSatisfactionByIdMaterial(idMaterial);
	}

	@Override
	public Materialsatisfaction getMaterialSatisfactionByIdUserAndIdMaterial(int idUser, int idMaterial) {
		// TODO Auto-generated method stub
		return msRepository.getMaterialSatisfactionByIdUserAndByIdMaterial(idUser, idMaterial);
	}

	@Override
	public Materialsatisfaction saveSatisfaction(Materialsatisfaction ms) {
		// TODO Auto-generated method stub
		return msRepository.save(ms);
	}

}
