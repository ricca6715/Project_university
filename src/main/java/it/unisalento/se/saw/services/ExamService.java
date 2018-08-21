package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IExamService;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.repositories.ExamRepository;

@Service
public class ExamService implements IExamService {

	@Autowired
	ExamRepository examRepository;
	
	@Transactional
	public Exam save(Exam e) {
		return examRepository.save(e);
	}
	
	@Transactional
	public List<Exam> getAll(){
		return examRepository.findAll();
	}

}
