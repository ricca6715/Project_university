package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IUserService;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.ElementNotValidException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public User getUserByMail_Pwd(String mail, String password) throws UserNotFoundException {
		System.out.println("user service: " + password);
		User user = userRepository.getUserByMail_Pwd(mail, password);
		
		
		if(user == null) {
			throw new UserNotFoundException();
		} 
		return user;

		
	}

	@Transactional
	public List<User> getUserEnrolledTeaching(int idTeaching)  {
		return userRepository.getUserEnrolledTeaching(idTeaching); 	//Get student enrolled in teaching
	}




	@Transactional
	public User saveUser(User user) throws ElementNotValidException {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Transactional
	public User getProfessorByidTeaching(int idTeaching) {
		return userRepository.getProfessorByidTeaching(idTeaching);
		
	}
	

	@Transactional
	public User getById(int id) {
		return userRepository.getOne(id);
		
	}
	
	@Transactional
	public User getUserByMail(String mail) {
		return userRepository.userByMail(mail);
	}

	@Override
	public List<User> getAllProfessors() {
		// TODO Auto-generated method stub
		return userRepository.getAllProfessors();
	}



}
