	package it.unisalento.se.saw.restapi;
	
	import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.MediaType;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import it.unisalento.se.saw.Iservices.IUserService;
import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
	import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.ElementNotValidException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.RegistrationValidateStrategy;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.TeachingModel;
import it.unisalento.se.saw.models.TokenFormModel;
	import it.unisalento.se.saw.models.UserModel;
	import it.unisalento.se.saw.models.UserTypeModel;
	
	@CrossOrigin
	@RestController //contiene due annotation, Controller e response body
	@RequestMapping("/user")
	public class UserRestController {
	 
	 @Autowired
	 IUserService userService;
	 
	 public UserRestController(IUserService userService) {
		 this.userService = userService;
	 }
	 
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
		  if(utm.getTypeName().equals("student")) {
			   StudyCourseModel scm = new StudyCourseModel();
			   scm.setIdStudyCourse(user.getStudycourse().getIdStudyCourse());
			   scm.setName(user.getStudycourse().getName());
			   scm.setDescription(user.getStudycourse().getDescription());
			   userModel.setStudycourse(scm);
			   userModel.setCourseYear(user.getCourseYear());
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
	 
	 @GetMapping(
		value= "/getAllProfessors",
		produces= MediaType.APPLICATION_JSON_VALUE )
	public List<User> getAllProfessors(){
		return userService.getAllProfessors();
	}

	 @PostMapping(
	   value="/save",
	   produces= MediaType.APPLICATION_JSON_VALUE,
	   consumes= MediaType.APPLICATION_JSON_VALUE)
	 public User saveUser(@RequestBody UserModel userModel) throws ElementNotValidException {
		   
		   User user = new User();
		   if (userModel.getIdUser() != null) {
			user.setIdUser(userModel.getIdUser());
		   } else {
			   userModel.setValidator(new RegistrationValidateStrategy());
			   if (!userModel.isValid()) {
				throw new ElementNotValidException(userModel.getError());
			}
		   }
		   user.setName(userModel.getName());
		   user.setSurname(userModel.getSurname());
		   user.setEmail(userModel.getEmail());
		   user.setPassword(userModel.getPassword());
		   Usertype ut = new Usertype();
		   ut.setIdUserType(userModel.getUsertype().getIdUserType());
		   ut.setTypeName(userModel.getUsertype().getTypeName());
		   user.setUsertype(ut);
		   if (ut.getTypeName().equals("student")) {
			   Studycourse sc = new Studycourse();
			   sc.setIdStudyCourse(userModel.getStudycourse().getIdStudyCourse());
			   sc.setName(userModel.getStudycourse().getName());
			   user.setStudycourse(sc);
			   user.setCourseYear(userModel.getCourseYear());
		   }
		   return userService.saveUser(user);
		 }
	  
	 @PostMapping(
	   value="/setToken",
	   produces= MediaType.APPLICATION_JSON_VALUE,
	   consumes= MediaType.APPLICATION_JSON_VALUE)
	 public User update(@RequestBody TokenFormModel tfm) throws ElementNotValidException {
		  System.out.println(tfm.getIdUser());
		  System.out.println(tfm.getToken());
		  User user = userService.getById(tfm.getIdUser());
		  user.setFcmtoken(tfm.getToken());
		  return userService.saveUser(user);
	 }
	 
	 @PostMapping(
			   value="/subscribetoteaching",
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes= MediaType.APPLICATION_JSON_VALUE)
			 public User subscribetoTeaching(@RequestBody UserModel userModel) throws ElementNotValidException {
				   
				   User user = new User();
				   if (userModel.getIdUser() != null) {
					user.setIdUser(userModel.getIdUser());
				   }
				   user.setName(userModel.getName());
				   user.setSurname(userModel.getSurname());
				   user.setEmail(userModel.getEmail());
				   user.setPassword(userModel.getPassword());
				   Usertype ut = new Usertype();
				   ut.setIdUserType(userModel.getUsertype().getIdUserType());
				   ut.setTypeName(userModel.getUsertype().getTypeName());
				   user.setUsertype(ut);
				   if (ut.getTypeName().equals("student")) {
					   Studycourse sc = new Studycourse();
					   sc.setIdStudyCourse(userModel.getStudycourse().getIdStudyCourse());
					   sc.setName(userModel.getStudycourse().getName());
					   user.setStudycourse(sc);
					   user.setCourseYear(userModel.getCourseYear());
				   }
				   List<TeachingModel> teachings = userModel.getTeachings();
				   HashSet<Teaching> teachingsSet = new HashSet<>();
				   //System.out.println(teachings.size());
				   if (teachings != null) {
					   for (int i = 0; i < teachings.size(); i++) {
						Teaching t = new Teaching();
						t.setIdTeaching(teachings.get(i).getIdTeaching());
						t.setName(teachings.get(i).getName());
						t.setCfu(teachings.get(i).getCfu());				
						teachingsSet.add(t);
					   }   
					   user.setTeachings(teachingsSet);		
				   }
				   return userService.saveUser(user);
				 }
	 
	 
	 
	
	}