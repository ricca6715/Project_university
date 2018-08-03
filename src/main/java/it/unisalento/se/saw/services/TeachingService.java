package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ITeachingService;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.repositories.TeachingRepository;

@Service
public class TeachingService implements ITeachingService {

	@Autowired
	TeachingRepository teachingRepository;
	
	@Transactional
	public Teaching getTeachingByName(String name) {
		return teachingRepository.getTeachingByName(name);
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

}
