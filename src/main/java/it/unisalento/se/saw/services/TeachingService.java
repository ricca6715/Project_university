package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ITeachingService;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.repositories.TeachingRepository;

@Service
public class TeachingService implements ITeachingService {

	@Autowired
	TeachingRepository teachingRepository;
	
	@Transactional
	public Teaching getTeachingByName(String name) throws TeachingNotFoundException {
		Teaching t = teachingRepository.getTeachingByName(name);
		if (t == null) {
			throw new TeachingNotFoundException();
		}
		return t;
	}
	
	@Transactional
	public List<Teaching> getAll(){
		return teachingRepository.findAll();
	}

	@Transactional
	public List<Teaching> getTeachingByStudyCourse(int id) {
		return teachingRepository.getTeachingByStudyCourse(id);
	}
	
	@Transactional
	public List<Teaching> getTeachingsByIdProfessor(int idProfessor){
		return teachingRepository.getTeachingsByIdProfessor(idProfessor);
	}

	@Override
	public List<Teaching> getTeachingsByIdStudent(int idStudent) {
		// TODO Auto-generated method stub
		return teachingRepository.getTeachingsByIdStudent(idStudent);
	}
	
	@Transactional
	public Teaching save(Teaching t) {
		return teachingRepository.save(t);
	}

	@Transactional
	public Teaching getTeachingById(int id) throws TeachingNotFoundException {
		// TODO Auto-generated method stub
		Teaching t = teachingRepository.getOne(id);
		if (t == null) {
			throw new TeachingNotFoundException();
		}
		return t;
	}

}
