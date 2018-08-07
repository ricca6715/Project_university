package it.unisalento.se.saw.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ILectureSatisfactionService;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
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
<<<<<<< HEAD
	public Lecturesatisfaction saveSatisfaction(Lecturesatisfaction lectureSatisfaction) {
		
		return lsRepository.save(lectureSatisfaction);
=======
	public Lecturesatisfaction getLectureSatisfactionByIdUserAndIdLecture(int idUser, int idLecture) {
		return lsRepository.getLectureSatisfactionByIdUserAndIdLecture(idUser, idLecture);
>>>>>>> branch 'master' of https://github.com/ricca6715/Project_university.git
	}


}
