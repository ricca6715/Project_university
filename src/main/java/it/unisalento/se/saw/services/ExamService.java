package it.unisalento.se.saw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IExamService;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.repositories.ExamRepository;

@Service
public class ExamService implements IExamService {

	@Autowired
	ExamRepository examRepository;
	
	@Override
	public Exam save(Exam e) {
		// TODO Auto-generated method stub
		return examRepository.save(e);
	}

}
