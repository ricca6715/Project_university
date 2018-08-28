package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Exam;

public interface IExamService {
	
	public Exam save(Exam e);
	public List<Exam> getAll();
	public List<Exam> getExamsByIdTeaching(int idTeaching);

}
