package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.TeachingModel;
import it.unisalento.se.saw.models.UserModel;
import it.unisalento.se.saw.models.UserTypeModel;
import it.unisalento.se.saw.repositories.TeachingRepository;

@RunWith(MockitoJUnitRunner.class)
public class TeachingServiceTest {
	
	@Mock
	private TeachingRepository teachingRepositoryMock;
	@InjectMocks
	private TeachingService teachingService;
	
	@Test
	public void getTeachingByNameTest() throws Exception {
		
		Teaching t = new Teaching();
		t.setName("Software Engineering");
		t.setCfu(9);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t.setUser(professor);
		
		when(teachingRepositoryMock.getTeachingByName(Mockito.anyString())).thenReturn(t);
		
		Teaching tBack = teachingService.getTeachingByName("Software Engineering");
		assertEquals(tBack.getName(), t.getName());
		assertEquals(tBack.getCfu(), t.getCfu());
		assertEquals(tBack.getUser().getName(), t.getUser().getName());
		assertEquals(tBack.getUser().getSurname(), t.getUser().getSurname());

	}
	
	@Test
	public void getAllTest() throws Exception {
		
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t1.setUser(professor);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		User professor2 = new User();
		professor2.setName("pierluigi");
		professor2.setSurname("mazzeo");
		professor2.setEmail("pierluigi@gmail.com");
		professor2.setPassword("pierluigi");
		professor2.setUsertype(new Usertype("professor", null));
		t2.setUser(professor);
		
		
		when(teachingRepositoryMock.findAll()).thenReturn(Arrays.asList(t1, t2));
		
		List<Teaching> teachingsBack = teachingService.getAll();
		assertEquals(teachingsBack.get(0).getName(), t1.getName());
		assertEquals(teachingsBack.get(0).getCfu(), t1.getCfu());
		assertEquals(teachingsBack.get(0).getUser().getName(), t1.getUser().getName());
		assertEquals(teachingsBack.get(0).getUser().getSurname(), t1.getUser().getSurname());
		assertEquals(teachingsBack.get(1).getName(), t2.getName());
		assertEquals(teachingsBack.get(1).getCfu(), t2.getCfu());
		assertEquals(teachingsBack.get(1).getUser().getName(), t2.getUser().getName());
		assertEquals(teachingsBack.get(1).getUser().getSurname(), t2.getUser().getSurname());
		
	}
	
	@Test
	public void getTeachingByStudyCourseTest() throws Exception {
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t1.setUser(professor);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		User professor2 = new User();
		professor2.setName("pierluigi");
		professor2.setSurname("mazzeo");
		professor2.setEmail("pierluigi@gmail.com");
		professor2.setPassword("pierluigi");
		professor2.setUsertype(new Usertype("professor", null));
		t2.setUser(professor);
		
		
		when(teachingRepositoryMock.getTeachingByStudyCourse(Mockito.anyInt())).thenReturn(Arrays.asList(t1, t2));
		
		List<Teaching> teachingsBack = teachingService.getTeachingByStudyCourse(1);
		assertEquals(teachingsBack.get(0).getName(), t1.getName());
		assertEquals(teachingsBack.get(0).getCfu(), t1.getCfu());
		assertEquals(teachingsBack.get(0).getUser().getName(), t1.getUser().getName());
		assertEquals(teachingsBack.get(0).getUser().getSurname(), t1.getUser().getSurname());
		assertEquals(teachingsBack.get(1).getName(), t2.getName());
		assertEquals(teachingsBack.get(1).getCfu(), t2.getCfu());
		assertEquals(teachingsBack.get(1).getUser().getName(), t2.getUser().getName());
		assertEquals(teachingsBack.get(1).getUser().getSurname(), t2.getUser().getSurname());
	}
	
	@Test
	public void getTeachingByIdProfessorTest() throws Exception {
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t1.setUser(professor);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		User professor2 = new User();
		professor2.setName("pierluigi");
		professor2.setSurname("mazzeo");
		professor2.setEmail("pierluigi@gmail.com");
		professor2.setPassword("pierluigi");
		professor2.setUsertype(new Usertype("professor", null));
		t2.setUser(professor);
		
		
		when(teachingRepositoryMock.getTeachingsByIdProfessor(Mockito.anyInt())).thenReturn(Arrays.asList(t1, t2));
		
		List<Teaching> teachingsBack = teachingService.getTeachingsByIdProfessor(1);
		assertEquals(teachingsBack.get(0).getName(), t1.getName());
		assertEquals(teachingsBack.get(0).getCfu(), t1.getCfu());
		assertEquals(teachingsBack.get(0).getUser().getName(), t1.getUser().getName());
		assertEquals(teachingsBack.get(0).getUser().getSurname(), t1.getUser().getSurname());
		assertEquals(teachingsBack.get(1).getName(), t2.getName());
		assertEquals(teachingsBack.get(1).getCfu(), t2.getCfu());
		assertEquals(teachingsBack.get(1).getUser().getName(), t2.getUser().getName());
		assertEquals(teachingsBack.get(1).getUser().getSurname(), t2.getUser().getSurname());
	}
	
	@Test
	public void getTeachingByIdStudentTest() throws Exception {
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t1.setUser(professor);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		User professor2 = new User();
		professor2.setName("pierluigi");
		professor2.setSurname("mazzeo");
		professor2.setEmail("pierluigi@gmail.com");
		professor2.setPassword("pierluigi");
		professor2.setUsertype(new Usertype("professor", null));
		t2.setUser(professor);
		
		
		when(teachingRepositoryMock.getTeachingsByIdStudent(Mockito.anyInt())).thenReturn(Arrays.asList(t1, t2));
		
		List<Teaching> teachingsBack = teachingService.getTeachingsByIdStudent(1);
		assertEquals(teachingsBack.get(0).getName(), t1.getName());
		assertEquals(teachingsBack.get(0).getCfu(), t1.getCfu());
		assertEquals(teachingsBack.get(0).getUser().getName(), t1.getUser().getName());
		assertEquals(teachingsBack.get(0).getUser().getSurname(), t1.getUser().getSurname());
		assertEquals(teachingsBack.get(1).getName(), t2.getName());
		assertEquals(teachingsBack.get(1).getCfu(), t2.getCfu());
		assertEquals(teachingsBack.get(1).getUser().getName(), t2.getUser().getName());
		assertEquals(teachingsBack.get(1).getUser().getSurname(), t2.getUser().getSurname());
	}
	
	@Test
	public void saveTest() throws Exception {
		
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(1);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t.setUser(professor);
		Studycourse sc = new Studycourse();
		sc.setIdStudyCourse(1);
		sc.setName("prova");
		sc.setDescription("test");
		Set<Studycourse> sclist = new HashSet<>();
		sclist.add(sc);
		
		when(teachingRepositoryMock.save(Mockito.any(Teaching.class))).thenReturn(t);
		Teaching tBack = teachingService.save(t);
		assertEquals(tBack.getName(), t.getName());
		assertEquals(tBack.getCfu(), t.getCfu());
		assertEquals(tBack.getUser().getName(), t.getUser().getName());
		assertEquals(tBack.getUser().getSurname(), t.getUser().getSurname());
	}
	
	@Test
	public void getTeachingByIdTest() throws Exception {
		
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		User professor = new User();
		professor.setName("luca");
		professor.setSurname("mainetti");
		professor.setEmail("luca@gmail.com");
		professor.setPassword("luca");
		professor.setUsertype(new Usertype("professor", null));
		t.setUser(professor);
		
		when(teachingRepositoryMock.getOne(1)).thenReturn(t);
		
		Teaching tBack = teachingService.getTeachingById(t.getIdTeaching());
		assertEquals(tBack.getName(), t.getName());
		assertEquals(tBack.getCfu(), t.getCfu());
		assertEquals(tBack.getUser().getName(), t.getUser().getName());
		assertEquals(tBack.getUser().getSurname(), t.getUser().getSurname());

	}


}
