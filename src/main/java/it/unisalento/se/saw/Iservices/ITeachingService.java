package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Teaching;

public interface ITeachingService {

	public Teaching getTeachingByName(String name);
	public List<Teaching> getAll();
	public List<Teaching> getTeachingByStudyCourse(int id);
	public List<Teaching> getTeachingsByIdProfessor(int idProfessor);
	public List<Teaching> getTeachingsByIdStudent(int idStudent);
}
