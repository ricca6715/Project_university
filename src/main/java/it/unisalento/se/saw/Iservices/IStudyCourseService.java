package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.exceptions.StudycourseNotFoundException;

public interface IStudyCourseService {

	public Studycourse getStudycourseByName(String name) throws StudycourseNotFoundException;
	public List<Studycourse> getAll();
	public Studycourse save(Studycourse sc);
}
