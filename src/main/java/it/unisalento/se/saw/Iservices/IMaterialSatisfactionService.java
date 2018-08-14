package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.exceptions.MaterialSatisfactionNotFound;

public interface IMaterialSatisfactionService {
	public Double getAverageRatingByIdMaterial(int idMaterial);
	public List<Materialsatisfaction> getMaterialSatisfactionByIdMaterial(int idMaterial);
	public Materialsatisfaction getMaterialSatisfactionByIdUserAndIdMaterial(int idUser, int idMaterial) throws MaterialSatisfactionNotFound;
	public Materialsatisfaction saveSatisfaction(Materialsatisfaction ms);

}
