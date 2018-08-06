package it.unisalento.se.saw.Iservices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.exceptions.LectureNotFoundException;

public interface ILectureService {

	
	public List<Lecture> getLecturesByDate(Date date);
	public List<Lecture> getAll();
	public Lecture getLectureById(int id) throws LectureNotFoundException;
	public List<Lecture> getLecturesByClassroom(int idClassroom);
	public List<Lecture> getLecturesByIdTeaching(int idTeaching);
	public List<Lecture> getDailyLectureByIdProfAndDate(int idUser, Date date);
	public List<Lecture> getDailyLectureByIdTeachingAndDate(int idTeaching, Date date);
}
