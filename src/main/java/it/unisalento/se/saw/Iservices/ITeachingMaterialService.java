package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Teachingmaterial;

public interface ITeachingMaterialService {
	
	public List<Teachingmaterial> getTeachingMaterialByIdLecture(int idLecture);
	public Teachingmaterial saveMaterial(Teachingmaterial tm);
	public boolean removeMaterial(int idTeachingmaterial);
	public Teachingmaterial getTeachingMaterialById(int idTeachingmaterial);

}
