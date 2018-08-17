package it.unisalento.se.saw.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ILectureSatisfactionService;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.exceptions.LectureSatisfactionNotFound;
import it.unisalento.se.saw.models.LecturesatisfactionModel;
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

	@Override
	public Lecturesatisfaction saveSatisfaction(Lecturesatisfaction lectureSatisfaction) {
		
		return lsRepository.save(lectureSatisfaction);
	}
	
	@Override
	public Lecturesatisfaction getLectureSatisfactionByIdUserAndIdLecture(int idUser, int idLecture) throws LectureSatisfactionNotFound {
		Lecturesatisfaction ls = lsRepository.getLectureSatisfactionByIdUserAndIdLecture(idUser, idLecture);
		if (ls == null) {
			throw new LectureSatisfactionNotFound();
		}
		return ls;
	}


}
