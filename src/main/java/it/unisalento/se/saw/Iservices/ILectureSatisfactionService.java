package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.models.LecturesatisfactionModel;

public interface ILectureSatisfactionService {
	
	public Double getAverageRatingByIdLecture(int idLecture);
	public List<Lecturesatisfaction> getLectureSatisfactionsByIdLecture(int idLecture);
	public Lecturesatisfaction saveSatisfaction(Lecturesatisfaction lectureSatisfaction);

}
