package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.repositories.ReportRepository;
import it.unisalento.se.saw.repositories.StudyCourseRepository;

@RunWith(MockitoJUnitRunner.class)
public class StudyCourseServiceTest {
	
	@Mock
	private StudyCourseRepository studyCourseRepositoryMock;
	@InjectMocks
	private StudyCourseService studyCourseService;
	
	@Test
	public void getStudycourseByNameTest() throws Exception {
		
		Studycourse sc = new Studycourse();
		
		sc.setName("Software Engineering");
		sc.setDescription("Software engineering teaching");
		sc.setIdStudyCourse(1);
		
		when(studyCourseRepositoryMock.getStudycourseByName(Mockito.anyString())).thenReturn(sc);
		
		Studycourse scBack = studyCourseService.getStudycourseByName("Software Engineering");
		
		assertEquals(scBack.getName(), sc.getName());
		assertEquals(scBack.getDescription(), sc.getDescription());
		assertEquals(scBack.getIdStudyCourse().toString(), sc.getIdStudyCourse().toString());
		
	}
	
	@Test
	public void getAllTest() throws Exception {
		
		Studycourse sc1 = new Studycourse();
		Studycourse sc2= new Studycourse();

		
		sc1.setName("Software Engineering");
		sc1.setDescription("Software engineering teaching");
		sc1.setIdStudyCourse(1);
		
		
		sc2.setName("Database");
		sc2.setDescription("Database teaching");
		sc2.setIdStudyCourse(2);
		
		when(studyCourseRepositoryMock.findAll()).thenReturn(Arrays.asList(sc1, sc2));
	
		List<Studycourse> studycoursesBack = studyCourseService.getAll();
		assertEquals(studycoursesBack.get(0).getName(), sc1.getName());
		assertEquals(studycoursesBack.get(0).getDescription(), sc1.getDescription());
		assertEquals(studycoursesBack.get(0).getIdStudyCourse().toString(), sc1.getIdStudyCourse().toString());
		assertEquals(studycoursesBack.get(1).getName(), sc2.getName());
		assertEquals(studycoursesBack.get(1).getDescription(), sc2.getDescription());
		assertEquals(studycoursesBack.get(1).getIdStudyCourse().toString(), sc2.getIdStudyCourse().toString());

	}
	
	@Test
	public void getStudycoursesByIdTeachingTest() throws Exception {
		Studycourse sc1 = new Studycourse();
		Studycourse sc2= new Studycourse();

		
		sc1.setName("Software Engineering");
		sc1.setDescription("Software engineering teaching");
		sc1.setIdStudyCourse(1);
		
		
		sc2.setName("Database");
		sc2.setDescription("Database teaching");
		sc2.setIdStudyCourse(2);
		
		
		when(studyCourseRepositoryMock.getStudycourseByIdTeaching(Mockito.anyInt())).thenReturn(Arrays.asList(sc1, sc2));
	
		List<Studycourse> studycoursesBack = studyCourseService.getStudycourseByIdTeaching(1);
		assertEquals(studycoursesBack.get(0).getName(), sc1.getName());
		assertEquals(studycoursesBack.get(0).getDescription(), sc1.getDescription());
		assertEquals(studycoursesBack.get(0).getIdStudyCourse().toString(), sc1.getIdStudyCourse().toString());
		assertEquals(studycoursesBack.get(1).getName(), sc2.getName());
		assertEquals(studycoursesBack.get(1).getDescription(), sc2.getDescription());
		assertEquals(studycoursesBack.get(1).getIdStudyCourse().toString(), sc2.getIdStudyCourse().toString());
	}
	
	@Test
	public void saveTest() throws Exception {
		
Studycourse sc = new Studycourse();
		
		sc.setName("Software Engineering");
		sc.setDescription("Software engineering teaching");
		sc.setIdStudyCourse(1);
		
		when(studyCourseRepositoryMock.save(Mockito.any(Studycourse.class))).thenReturn(sc);
		
		Studycourse scBack = studyCourseService.save(sc);
		
		assertEquals(scBack.getName(), sc.getName());
		assertEquals(scBack.getDescription(), sc.getDescription());
		assertEquals(scBack.getIdStudyCourse().toString(), sc.getIdStudyCourse().toString());
		
	}
}
