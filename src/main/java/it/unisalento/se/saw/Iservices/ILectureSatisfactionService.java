package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Lecturesatisfaction;

public interface ILectureSatisfactionService {
	
	public Double getAverageRatingByIdLecture(int idLecture);
	public List<Lecturesatisfaction> getLectureSatisfactionsByIdLecture(int idLecture);
	public Lecturesatisfaction getLectureSatisfactionByIdUserAndIdLecture(int idUser, int idLecture);

}
