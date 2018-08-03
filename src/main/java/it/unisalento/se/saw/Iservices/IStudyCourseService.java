package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Studycourse;

public interface IStudyCourseService {

	public Studycourse getStudycourseByName(String name);
	public List<Studycourse> getAll();
}
