package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.repositories.CalendarRepository;

@RunWith(MockitoJUnitRunner.class)
public class CalendarServiceTest {

	@Mock
	CalendarRepository calRepositoryMock;
	
	@InjectMocks
	CalendarService calService;
	
	@Test
	public void getAllTest() throws Exception {
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setStudycourses(scSet);
		
		Calendar c2 = new Calendar();
		Set<Studycourse> scSet2 = new HashSet<>();
		scSet2.add(new Studycourse("Software Engineering","test",null,null,null));
		c2.setAcademicYear("2019-2020");
		c2.setStudycourses(scSet);
		
		when(calRepositoryMock.findAll()).thenReturn(Arrays.asList(c, c2));
		
		List<Calendar> calendars = calService.getAll();
		
		assertEquals(calendars.get(0).getAcademicYear(), "2018-2019");
		assertEquals(calendars.get(1).getAcademicYear(), "2019-2020");
		
		
		
	}
	
	@Test
	public void getCalendarsByIdStudycourseTest() throws Exception {
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setStudycourses(scSet);
		
		Calendar c2 = new Calendar();
		Set<Studycourse> scSet2 = new HashSet<>();
		scSet2.add(new Studycourse("Software Engineering","test",null,null,null));
		c2.setAcademicYear("2019-2020");
		c2.setStudycourses(scSet);
		
		when(calRepositoryMock.getCalendarsByIdStudycourse(Mockito.anyInt())).thenReturn(Arrays.asList(c, c2));
		
		List<Calendar> calendars = calService.getCalendarsByIdStudycourse(1);
		
		assertEquals(calendars.get(0).getAcademicYear(), "2018-2019");
		assertEquals(calendars.get(1).getAcademicYear(), "2019-2020");
		
		
		
	}
	
	@Test
	public void saveTest() throws Exception {
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setStudycourses(scSet);
		
		when(calRepositoryMock.save(Mockito.any(Calendar.class))).thenReturn(c);
		
		Calendar cback = calService.save(c);
		assertEquals(cback.getAcademicYear(), "2018-2019");
		
		
	}
	
}
