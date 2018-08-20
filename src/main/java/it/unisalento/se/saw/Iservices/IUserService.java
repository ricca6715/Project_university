package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

public interface IUserService {
	
	public User getUserByMail_Pwd(String mail, String password) throws UserNotFoundException;
	public List<User> getUserEnrolledTeaching(String nameTeaching); 	//Get student enrolled in teaching
	public User saveUser(User user);
	public User getProfessorByNameTeaching(String nameTeaching);
	public User getById(int id);
	public User getUserByMail(String mail);
	public List<User> getAllProfessors();

}
