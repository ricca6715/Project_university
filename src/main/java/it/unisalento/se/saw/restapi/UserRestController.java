package it.unisalento.se.saw.restapi;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IUserService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.TokenFormModel;
import it.unisalento.se.saw.models.UserModel;
import it.unisalento.se.saw.models.UserTypeModel;

@CrossOrigin
@RestController //contiene due annotation, Controller e response body
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	IUserService userService;
	
	//constructors//////////////////////////////////
	public UserRestController() {
		super();
	}
	
	public UserRestController(IUserService userService) {
		this.userService = userService;
	}
	//////////////////////////////////////////////
	
	
	@GetMapping(
			   value = "/getUserByMail_Pwd/{mail}/{password}",
			   produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
			 public UserModel getUserByMail_Pwd(@PathVariable("mail") String mail, @PathVariable("password") String password) throws UserNotFoundException {

			  User user =  userService.getUserByMail_Pwd(mail, password);
			  UserModel userModel = new UserModel();
			  userModel.setIdUser(user.getIdUser());
			  userModel.setEmail(user.getEmail());
			  userModel.setName(user.getName());
			  userModel.setPassword(user.getPassword());
			  userModel.setSurname(user.getSurname());
			  userModel.setFcmToken(user.getFcmtoken());

			  UserTypeModel utm = new UserTypeModel();
			  utm.setIdUserType(user.getUsertype().getIdUserType());
			  utm.setTypeName(user.getUsertype().getTypeName());
			  userModel.setUsertype(utm);
			  if(utm.getTypeName() == "student") {
			   StudyCourseModel scm = new StudyCourseModel();
			   scm.setIdStudyCourse(user.getStudycourse().getIdStudyCourse());
			   scm.setName(user.getStudycourse().getName());
			   scm.setDescription(user.getStudycourse().getDescription());
			   userModel.setStudycourse(scm);
			  }
			  return userModel;
			 }
	
	@GetMapping(
			value= "/getUserEnrolledTeaching/{nameTeaching}",
			produces= MediaType.APPLICATION_JSON_VALUE )
	public List<User> getUserEnrolledTeaching(@PathVariable("nameTeaching") String nameTeaching){
		return userService.getUserEnrolledTeaching(nameTeaching);
	}
	

	
	@GetMapping(
			value= "/getProfessorByNameTeaching/{nameTeaching}",
			produces= MediaType.APPLICATION_JSON_VALUE )
	public User getProfessorByNameTeaching(@PathVariable("nameTeaching") String nameTeaching){
		return userService.getProfessorByNameTeaching(nameTeaching);
	}

	
	@GetMapping(
			value= "/getUsersByTeachingName/{nameTeaching}",
			produces= MediaType.APPLICATION_JSON_VALUE )
	public List<User> getUsersByTeachingName(@PathVariable("nameTeaching") String nameTeaching){
		User prof =  userService.getProfessorByNameTeaching(nameTeaching);
		List<User> users = userService.getUserEnrolledTeaching(nameTeaching);
		users.add(prof);
		return users;
	}
	
	@GetMapping(
			value = "/userByMail/{mail}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByMail(@PathVariable("mail") String mail) {

		return userService.getUserByMail(mail);
	}
	
	@GetMapping(
			value= "/getById/{id}",
			produces= MediaType.APPLICATION_JSON_VALUE )
	public User getById(@PathVariable("id") int id){
		return userService.getById(id);
	}

	

	@PostMapping(
			value="/save",
			produces= MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public User saveUser(@RequestBody UserModel userModel) {
	
	  
	  User user = new User();
	  user.setName(userModel.getName());
	  user.setSurname(userModel.getSurname());
	  user.setEmail(userModel.getEmail());
	  user.setPassword(userModel.getPassword());
	  Studycourse sc = new Studycourse();
	  sc.setIdStudyCourse(userModel.getStudycourse().getIdStudyCourse());
	  sc.setName(userModel.getStudycourse().getName());
	  Usertype ut = new Usertype();
	  ut.setIdUserType(userModel.getUsertype().getIdUserType());
	  ut.setTypeName(userModel.getUsertype().getTypeName());
	  user.setStudycourse(sc);
	  user.setUsertype(ut);
	  System.out.println(userModel.getFcmToken());
	  if(userModel.getFcmToken() != null)
		  user.setFcmtoken(userModel.getFcmToken());
	  return userService.saveUser(user);
	}
	
	@PostMapping(
			value="/setToken",
			produces= MediaType.APPLICATION_JSON_VALUE,
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public User update(@RequestBody TokenFormModel tfm) {
		System.out.println(tfm.getIdUser());
		System.out.println(tfm.getToken());
		User user = userService.getById(tfm.getIdUser());
		user.setFcmtoken(tfm.getToken());
		return userService.saveUser(user);

	}
	
	
	

}
