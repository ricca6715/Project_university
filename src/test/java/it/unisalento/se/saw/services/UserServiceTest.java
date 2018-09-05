package it.unisalento.se.saw.services;

import javax.annotation.meta.When;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.ElementNotValidException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.UserModel;
import it.unisalento.se.saw.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRep;
	@InjectMocks
	private UserService userService;
	
	@Test
    public void loginUserTest() throws UserNotFoundException {
		
		User user = new User();
		user.setName("riccardo");
		user.setSurname("contino");
		user.setEmail("riccardo@gmail.com");
		user.setPassword("riccardo");
		user.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		
		when(userRep.getUserByMail_Pwd("riccardo@gmail.com", "riccardo")).thenReturn(user);
		
		User uback = userService.getUserByMail_Pwd(user.getEmail(), user.getPassword());
		
		assertEquals(user.getName(), uback.getName());
        assertEquals(user.getSurname(), uback.getSurname());
        assertEquals(user.getStudycourse().getName(), uback.getStudycourse().getName());
	}
	
	@Test
    public void getUserEnrolledTeachingTest() {
		
		User user1 = new User();
		User user2 = new User();
		Teaching t = new Teaching();
		
		user1.setName("riccardo");
		user1.setSurname("contino");
		user1.setEmail("riccardo@gmail.com");
		user1.setPassword("riccardo");
		user1.setUsertype(new Usertype("student", null));
		user1.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		user2.setName("andrea");
		user2.setSurname("della monaca");
		user2.setEmail("andrea@libero.it");
		user2.setPassword("andrea");	
		user2.setUsertype(new Usertype("student", null));
		user2.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		
		when(userRep.getUserEnrolledTeaching(1)).thenReturn(Arrays.asList(user1, user2));
		
		List<User> ulback = userService.getUserEnrolledTeaching(t.getIdTeaching());
		
		assertEquals(user1.getName(), ulback.get(0).getName());
		assertEquals(user1.getSurname(), ulback.get(0).getSurname());
		assertEquals(user1.getEmail(), ulback.get(0).getEmail());
		assertEquals(user1.getPassword(), ulback.get(0).getPassword());
		assertEquals(user2.getName(), ulback.get(1).getName());
		assertEquals(user2.getSurname(), ulback.get(1).getSurname());
		assertEquals(user2.getEmail(), ulback.get(1).getEmail());
		assertEquals(user2.getPassword(), ulback.get(1).getPassword());
	}
	
	@Test
    public void saveUserTest() throws ElementNotValidException {
		
		User user = new User();
		user.setName("riccardo");
		user.setSurname("contino");
		user.setEmail("riccardo@gmail.com");
		user.setPassword("riccardo");
		user.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		when(userRep.save(Mockito.any(User.class))).thenReturn(user);
		
		User uback = userService.saveUser(user);
		
		assertEquals(user.getName(), uback.getName());
        assertEquals(user.getSurname(), uback.getSurname());
        assertEquals(user.getStudycourse().getName(), uback.getStudycourse().getName());
	}
	
	@Test
    public void getProfessorByNameTeachingTest() throws UserNotFoundException {
		
		User user3 = new User();
		user3.setName("luca");
		user3.setSurname("mainetti");
		user3.setEmail("luca@gmail.com");
		user3.setPassword("luca");
		user3.setUsertype(new Usertype("professor", null));
		
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		
		
		when(userRep.getProfessorByidTeaching(1)).thenReturn(user3);
		
		User uback = userService.getProfessorByidTeaching(t.getIdTeaching());
		
		assertEquals(user3.getName(), uback.getName());
        assertEquals(user3.getSurname(), uback.getSurname());
        assertEquals(user3.getEmail(), uback.getEmail());
        assertEquals(user3.getPassword(), uback.getPassword());
	}
	
	@Test
    public void getByIdTest() throws UserNotFoundException {
		
		User user3 = new User();
		user3.setIdUser(1);
		user3.setName("luca");
		user3.setSurname("mainetti");
		user3.setEmail("luca@gmail.com");
		user3.setPassword("luca");
		user3.setUsertype(new Usertype("professor", null));
		
		when(userRep.getOne(1)).thenReturn(user3);
		
		User uback = userService.getById(user3.getIdUser());
		
		assertEquals(user3.getName(), uback.getName());
        assertEquals(user3.getSurname(), uback.getSurname());
        assertEquals(user3.getEmail(), uback.getEmail());
        assertEquals(user3.getPassword(), uback.getPassword());
	}
	
	@Test
    public void getUserByMailTest() throws UserNotFoundException {
		
		User user3 = new User();
		user3.setName("luca");
		user3.setSurname("mainetti");
		user3.setEmail("luca@gmail.com");
		user3.setPassword("luca");
		user3.setUsertype(new Usertype("professor", null));
		
		when(userRep.userByMail("luca@gmail.com")).thenReturn(user3);
		
		User uback = userService.getUserByMail(user3.getEmail());
		
		assertEquals(user3.getName(), uback.getName());
        assertEquals(user3.getSurname(), uback.getSurname());
        assertEquals(user3.getEmail(), uback.getEmail());
        assertEquals(user3.getPassword(), uback.getPassword());
	}
	
	@Test
    public void getAllProfessorsTest() throws UserNotFoundException {
		
		User user1 = new User();
		user1.setName("luca");
		user1.setSurname("mainetti");
		user1.setEmail("luca@gmail.com");
		user1.setPassword("luca");
		user1.setUsertype(new Usertype("professor", null));
		
		User user2 = new User();
		user2.setName("mario");
		user2.setSurname("rossi");
		user2.setEmail("mr@gmail.com");
		user2.setPassword("553399");
		user2.setUsertype(new Usertype("professor", null));
		
		when(userRep.getAllProfessors()).thenReturn(Arrays.asList(user1, user2));
		
		List<User> ulback = userService.getAllProfessors();
		
		assertEquals(user1.getName(), ulback.get(0).getName());
        assertEquals(user1.getSurname(), ulback.get(0).getSurname());
        assertEquals(user1.getEmail(), ulback.get(0).getEmail());
        assertEquals(user1.getPassword(), ulback.get(0).getPassword());
        assertEquals(user2.getName(), ulback.get(1).getName());
        assertEquals(user2.getSurname(), ulback.get(1).getSurname());
        assertEquals(user2.getEmail(), ulback.get(1).getEmail());
        assertEquals(user2.getPassword(), ulback.get(1).getPassword());
	}


}
