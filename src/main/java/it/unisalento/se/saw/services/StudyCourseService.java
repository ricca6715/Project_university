package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IStudyCourseService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.exceptions.StudycourseNotFoundException;
import it.unisalento.se.saw.repositories.StudyCourseRepository;

@Service
public class StudyCourseService implements IStudyCourseService {

	
	@Autowired
	StudyCourseRepository scRepository;
	
	@Transactional(readOnly=true)
	public Studycourse getStudycourseByName(String name) throws StudycourseNotFoundException {
		Studycourse sc = scRepository.getStudycourseByName(name);
		if(sc == null) {
			throw new StudycourseNotFoundException();
		}
		return sc;
	}
	
	
	@Transactional(readOnly=true)
	public List<Studycourse> getAll(){
		return scRepository.findAll();
	}

}
