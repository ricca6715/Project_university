package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.repositories.ExamRepository;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {
	
	@Mock
	private ExamRepository examrep;
	@InjectMocks
	private ExamService examService;
	
	@Test
    public void getAllTest() throws ParseException {
		Exam ex1 = new Exam();
		Exam ex2 = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		Classroom cls = new Classroom();
		cls.setName("y1");
		cls.setDescription("test");
		cls.setLatitude((double) 110);
		cls.setLongitude((double) 115);
		cls.setIdClassroom(1);
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(1);
		User prof = new User();
		prof.setName("Mario");
		prof.setSurname("Rossi");
		prof.setEmail("mario.rossi@gmail.com");
		prof.setPassword("mario1971");
		prof.setUsertype(new Usertype("professor", null));
		t.setUser(prof);
		
		ex1.setIdExam(1);
		ex1.setDate(d);
		ex1.setHour("10.30");
		ex1.setClassroom(cls);
		ex1.setTeaching(t);
		ex2.setIdExam(2);
		ex2.setDate(d);
		ex2.setHour("15.30");
		ex2.setClassroom(cls);
		ex2.setTeaching(t);
		
		when(examrep.findAll()).thenReturn(Arrays.asList(ex1, ex2));
		
		List<Exam> exlback = examService.getAll();
		
		assertEquals(ex1.getIdExam(), exlback.get(0).getIdExam());
		assertEquals(ex1.getHour(), exlback.get(0).getHour());
		assertEquals(ex1.getDate(), exlback.get(0).getDate());
		assertEquals(ex1.getClassroom().getName(), exlback.get(0).getClassroom().getName());
		assertEquals(ex1.getTeaching().getName(), exlback.get(0).getTeaching().getName());
		assertEquals(ex2.getIdExam(), exlback.get(1).getIdExam());
		assertEquals(ex2.getHour(), exlback.get(1).getHour());
		assertEquals(ex2.getDate(), exlback.get(1).getDate());
		assertEquals(ex2.getClassroom().getName(), exlback.get(1).getClassroom().getName());
		assertEquals(ex2.getTeaching().getName(), exlback.get(1).getTeaching().getName());
	}
	
	@Test
    public void getExamsByIdTeachingTest() throws ParseException {
		Exam ex1 = new Exam();
		Exam ex2 = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		Classroom cls = new Classroom();
		cls.setName("y1");
		cls.setDescription("test");
		cls.setLatitude((double) 110);
		cls.setLongitude((double) 115);
		cls.setIdClassroom(1);
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(1);
		User prof = new User();
		prof.setName("Mario");
		prof.setSurname("Rossi");
		prof.setEmail("mario.rossi@gmail.com");
		prof.setPassword("mario1971");
		prof.setUsertype(new Usertype("professor", null));
		t.setUser(prof);
		
		ex1.setIdExam(1);
		ex1.setDate(d);
		ex1.setHour("10.30");
		ex1.setClassroom(cls);
		ex1.setTeaching(t);
		ex2.setIdExam(2);
		ex2.setDate(d);
		ex2.setHour("15.30");
		ex2.setClassroom(cls);
		ex2.setTeaching(t);
		
		when(examrep.getExamsByIdTeaching(1)).thenReturn(Arrays.asList(ex1, ex2));
		
		List<Exam> exlback = examService.getExamsByIdTeaching(1);
		
		assertEquals(ex1.getIdExam(), exlback.get(0).getIdExam());
		assertEquals(ex1.getHour(), exlback.get(0).getHour());
		assertEquals(ex1.getDate(), exlback.get(0).getDate());
		assertEquals(ex1.getClassroom().getName(), exlback.get(0).getClassroom().getName());
		assertEquals(ex1.getTeaching().getIdTeaching(), exlback.get(0).getTeaching().getIdTeaching());
		assertEquals(ex2.getIdExam(), exlback.get(1).getIdExam());
		assertEquals(ex2.getHour(), exlback.get(1).getHour());
		assertEquals(ex2.getDate(), exlback.get(1).getDate());
		assertEquals(ex2.getClassroom().getName(), exlback.get(1).getClassroom().getName());
		assertEquals(ex2.getTeaching().getIdTeaching(), exlback.get(1).getTeaching().getIdTeaching());
	}
	
	@Test
    public void saveExamTest() throws ParseException {
		Exam ex1 = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		Classroom cls = new Classroom();
		cls.setName("y1");
		cls.setDescription("test");
		cls.setLatitude((double) 110);
		cls.setLongitude((double) 115);
		cls.setIdClassroom(1);
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(1);
		User prof = new User();
		prof.setName("Mario");
		prof.setSurname("Rossi");
		prof.setEmail("mario.rossi@gmail.com");
		prof.setPassword("mario1971");
		prof.setUsertype(new Usertype("professor", null));
		t.setUser(prof);
		
		ex1.setIdExam(1);
		ex1.setDate(d);
		ex1.setHour("10.30");
		ex1.setClassroom(cls);
		ex1.setTeaching(t);
		
		when(examrep.save(Mockito.any(Exam.class))).thenReturn(ex1);
		
		Exam exback = examService.save(ex1);
		
		assertEquals(ex1.getIdExam(), exback.getIdExam());
		assertEquals(ex1.getHour(), exback.getHour());
		assertEquals(ex1.getDate(), exback.getDate());
		assertEquals(ex1.getClassroom().getName(), exback.getClassroom().getName());
		assertEquals(ex1.getTeaching().getIdTeaching(), exback.getTeaching().getIdTeaching());
		}

}
