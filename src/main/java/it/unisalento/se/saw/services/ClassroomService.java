package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IClassroomService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.repositories.ClassroomRepository;

@Service
public class ClassroomService implements IClassroomService {
	
	@Autowired
	ClassroomRepository classroomRepository;

	@Transactional
	public Classroom save(Classroom cls) {
		return classroomRepository.save(cls);
	}
	
	@Transactional
	public List<Classroom> getAll() {
		return classroomRepository.findAll();
	}
	

}
