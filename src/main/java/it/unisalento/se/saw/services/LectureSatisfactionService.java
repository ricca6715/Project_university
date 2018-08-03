package it.unisalento.se.saw.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ILectureSatisfactionService;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.repositories.LectureSatisfactionRepository;

@Service
public class LectureSatisfactionService implements ILectureSatisfactionService {

	@Autowired
	LectureSatisfactionRepository lsRepository;
	
	@Transactional
	public Double getAverageRatingByIdLecture(int idLecture) {
		return lsRepository.getAverageRatingByIdLecture(idLecture);
	}

	@Override
	public List<Lecturesatisfaction> getLectureSatisfactionsByIdLecture(int idLecture) {
		return lsRepository.getLectureSatisfactionsByIdLecture(idLecture);
	}


}
