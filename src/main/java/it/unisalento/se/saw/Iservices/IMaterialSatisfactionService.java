package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Materialsatisfaction;

public interface IMaterialSatisfactionService {
	public Double getAverageRatingByIdMaterial(int idMaterial);
	public List<Materialsatisfaction> getMaterialSatisfactionByIdMaterial(int idMaterial);

}
