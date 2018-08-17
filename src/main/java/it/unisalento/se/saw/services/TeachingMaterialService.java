package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ITeachingMaterialService;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.exceptions.TeachingMaterialNotFound;
import it.unisalento.se.saw.repositories.TeachingMaterialRepository;

@Service
public class TeachingMaterialService implements ITeachingMaterialService {
	
	@Autowired
	TeachingMaterialRepository teachingMaterialRepository;

	@Transactional
	public List<Teachingmaterial> getTeachingMaterialByIdLecture(int idLecture) {
		return teachingMaterialRepository.getTeachingMaterialByIdLecture(idLecture);
	}

	@Transactional
	public Teachingmaterial saveMaterial(Teachingmaterial tm) {
		
		return teachingMaterialRepository.save(tm);
	}
	

	@Transactional
	public boolean removeMaterial(int idTeachingmaterial) {
		// TODO Auto-generated method stub
		teachingMaterialRepository.deleteById(idTeachingmaterial);
		return true;
	}

	@Transactional
	public Teachingmaterial getTeachingMaterialById(int idTeachingmaterial) throws TeachingMaterialNotFound {
		// TODO Auto-generated method stub
		Teachingmaterial tm = teachingMaterialRepository.getTeachingMaterialById(idTeachingmaterial);
		if (tm ==null) {
			throw new TeachingMaterialNotFound();
		}
		return tm;
	}

}
