package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.models.LecturesatisfactionModel;

public interface ILectureSatisfactionService {
	
	public Double getAverageRatingByIdLecture(int idLecture);
	public List<Lecturesatisfaction> getLectureSatisfactionsByIdLecture(int idLecture);
<<<<<<< HEAD
	public Lecturesatisfaction saveSatisfaction(Lecturesatisfaction lectureSatisfaction);
=======
	public Lecturesatisfaction getLectureSatisfactionByIdUserAndIdLecture(int idUser, int idLecture);
>>>>>>> branch 'master' of https://github.com/ricca6715/Project_university.git

}
