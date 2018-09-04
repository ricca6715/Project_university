package it.unisalento.se.saw.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.repositories.LectureSatisfactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class LectureSatisfactionServiceTest {
	
	 @Mock
	 private LectureSatisfactionRepository lectureSatRepositoryMock;
	 @InjectMocks
	 private LectureSatisfactionService lectureSatService;
	 
	 @Test
	 public void getAverageRatingByIdLectureTest() throws Exception {
		 
		Double rating = 3.33;
			
		when(lectureSatRepositoryMock.getAverageRatingByIdLecture(Mockito.anyInt())).thenReturn(rating);
		
		Double ratingBack = lectureSatService.getAverageRatingByIdLecture(1);
		
		assertEquals(ratingBack, rating);
	 }
	 
	 @Test
	 public void getLectureSatisfactionsByIdLectureTest() throws Exception {
		 Lecture l = new Lecture();
			l.setIdLecture(1);
			Lecturesatisfaction l1 = new Lecturesatisfaction();
			l1.setIdlectureSatisfaction(1);
			l1.setLevel(2);
			l1.setLecture(l);
			l1.setNote("prova");
			Classroom cl1 = new Classroom();
			cl1.setDescription("prova");
			cl1.setName("y1");
			l.setClassroom(cl1);
			Teaching t = new Teaching();
			t.setName("Software Engineering");
			t.setCfu(9);
			User professor = new User();
			professor.setName("luca");
			professor.setSurname("mainetti");
			professor.setEmail("luca@gmail.com");
			professor.setPassword("luca");
			Usertype ut1 = new Usertype();
			ut1.setIdUserType(2);
			ut1.setTypeName("professor");
			professor.setUsertype(ut1);
			t.setUser(professor);
			l.setTeaching(t);
			l1.setLecture(l);
			User user = new User();
			user.setName("riccardo");
			user.setSurname("contino");
			user.setEmail("riccardo@gmail.com");
			user.setPassword("riccardo");
			Usertype ut = new Usertype();
			ut.setIdUserType(1);
			ut.setTypeName("student");
			user.setUsertype(ut);
			Studycourse sc = new Studycourse();
			sc.setName("Software Engineering");
			sc.setDescription("Software engineering teaching");
			sc.setIdStudyCourse(1);
			user.setStudycourse(sc);
			l1.setUser(user);
			Lecturesatisfaction l2 = new Lecturesatisfaction();
			l2.setIdlectureSatisfaction(2);
			l2.setLevel(5);
			l2.setLecture(l);
			l2.setNote("prova");
			l2.setUser(user);
			
			when(lectureSatRepositoryMock.getLectureSatisfactionsByIdLecture(Mockito.anyInt())).thenReturn(Arrays.asList(l1, l2));
	
			List<Lecturesatisfaction> listBack = lectureSatService.getLectureSatisfactionsByIdLecture(1);
			
			assertEquals(listBack.get(0).getLevel().toString(), "2");
			assertEquals(listBack.get(0).getLecture().getIdLecture().toString(), "1");
			assertEquals(listBack.get(1).getLevel().toString(), "5");

	 }
	 
	 @Test
	 public void getLectureSatisfactionByIdUserAndIdLectureTest() throws Exception {
		 Lecture l = new Lecture();
			l.setIdLecture(1);
			Lecturesatisfaction l1 = new Lecturesatisfaction();
			l1.setIdlectureSatisfaction(1);
			l1.setLevel(2);
			l1.setNote("prova");
			Classroom cl1 = new Classroom();
			cl1.setDescription("prova");
			cl1.setName("y1");
			l.setClassroom(cl1);
			Teaching t = new Teaching();
			t.setName("Software Engineering");
			t.setCfu(9);
			User professor = new User();
			professor.setName("luca");
			professor.setSurname("mainetti");
			professor.setEmail("luca@gmail.com");
			professor.setPassword("luca");
			Usertype ut1 = new Usertype();
			ut1.setIdUserType(2);
			ut1.setTypeName("professor");
			professor.setUsertype(ut1);
			t.setUser(professor);
			l.setTeaching(t);
			l1.setLecture(l);
			User user = new User();
			user.setName("riccardo");
			user.setSurname("contino");
			user.setEmail("riccardo@gmail.com");
			user.setPassword("riccardo");
			Usertype ut = new Usertype();
			ut.setIdUserType(1);
			ut.setTypeName("student");
			user.setUsertype(ut);
			Studycourse sc = new Studycourse();
			sc.setName("Software Engineering");
			sc.setDescription("Software engineering teaching");
			sc.setIdStudyCourse(1);
			user.setStudycourse(sc);
			l1.setUser(user);
			
			when(lectureSatRepositoryMock.getLectureSatisfactionByIdUserAndIdLecture(Mockito.anyInt(), Mockito.anyInt())).thenReturn(l1);
			Lecturesatisfaction lback = lectureSatService.getLectureSatisfactionByIdUserAndIdLecture(1, 1);
			
			assertEquals(lback.getLevel().toString(), "2");
			assertEquals(lback.getLecture().getIdLecture().toString(), "1");
			assertEquals(lback.getIdlectureSatisfaction().toString(), "1");
			assertEquals(lback.getNote(), l1.getNote());
			
	 }

	 @Test
	 public void saveTest() throws Exception {
		 Lecture l = new Lecture();
			l.setIdLecture(1);
			Lecturesatisfaction l1 = new Lecturesatisfaction();
			l1.setIdlectureSatisfaction(1);
			l1.setLevel(2);
			l1.setLecture(l);
			User u = new User();
			u.setIdUser(1);
			l1.setUser(u);
			l1.setNote("prova");
			
			when(lectureSatRepositoryMock.save(Mockito.any(Lecturesatisfaction.class))).thenReturn(l1);
			
			Lecturesatisfaction lback = lectureSatService.saveSatisfaction(l1);
			
			assertEquals(lback.getLevel().toString(), "2");
			assertEquals(lback.getNote(), l1.getNote());
			assertEquals(lback.getLecture().getIdLecture().toString(), "1");
			
	 }
}
