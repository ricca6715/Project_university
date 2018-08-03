package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Classroom;

public interface IClassroomService {
	
	public Classroom save(Classroom cls);
	public List<Classroom> getAll();
	

}
