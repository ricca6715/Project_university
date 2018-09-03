package it.unisalento.se.saw.restapi;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.ServletContextLiveBeansView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IExamService;
import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;

@RunWith(MockitoJUnitRunner.class)
public class ExamRestControllerTest {
	
private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	
	@Mock
	IExamService examServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new ExamRestController(examServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	
	@Test
	public void getAllTest() throws Exception {
		
		Exam e = new Exam();
		Exam e2 = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		e.setDate(d);
		e.setHour("11.30");
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(2);
		e.setTeaching(t);
		Classroom c = new Classroom();
		c.setIdClassroom(1);
		c.setName("y1");
		e.setClassroom(c);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date d2 = sdf2.parse("2013-12-21");
		e2.setDate(d2);
		e2.setHour("12.30");
		Teaching t2 = new Teaching();
		t2.setIdTeaching(2);
		t2.setName("Software Engineering 2");
		t2.setCfu(12);
		t2.setCourseYear(3);
		e2.setTeaching(t2);
		Classroom c2 = new Classroom();
		c2.setIdClassroom(2);
		c2.setName("y2");
		e2.setClassroom(c2);
		
		when(examServiceMock.getAll()).thenReturn(Arrays.asList(e,e2));
		
		mockMvc.perform(get("/exam/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].date", is("2012-12-21")))
		.andExpect(jsonPath("$[1].date", is("2013-12-21")))
		.andExpect(jsonPath("$[0].teaching.name", is("Software Engineering")))
		.andExpect(jsonPath("$[1].teaching.name", is("Software Engineering 2")))
		.andExpect(jsonPath("$[0].hour", is("11.30")))
		.andExpect(jsonPath("$[1].hour", is("12.30")));

		
		
		verify(examServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(examServiceMock);
	}
	
	
	@Test
	public void saveTest() throws Exception {
		Exam e = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		e.setDate(d);
		e.setHour("11.30");
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(2);
		e.setTeaching(t);
		Classroom c = new Classroom();
		c.setIdClassroom(1);
		c.setName("y1");
		e.setClassroom(c);
		
		when(examServiceMock.save(Mockito.any(Exam.class))).thenReturn(e);
		
		 mockMvc.perform(
	                post("/exam/save")
	                .contentType(APPLICATION_JSON_UTF8)
	                .content(new ObjectMapper().writeValueAsString(e)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.date", is("2012-12-21")))
		.andExpect(jsonPath("$.teaching.name", is("Software Engineering")))
		.andExpect(jsonPath("$.hour", is("11.30")));
		
		ArgumentCaptor<Exam> uCaptor = ArgumentCaptor.forClass(Exam.class);
		
		verify(examServiceMock, times(1)).save(uCaptor.capture());
		verifyNoMoreInteractions(examServiceMock);
		
		
	}
	
	@Test
	public void updateTest() throws Exception {
		Exam e = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		e.setDate(d);
		e.setHour("11.30");
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(2);
		e.setTeaching(t);
		Classroom c = new Classroom();
		c.setIdClassroom(1);
		c.setName("y1");
		e.setClassroom(c);
		e.setIdExam(1);
		
		when(examServiceMock.save(Mockito.any(Exam.class))).thenReturn(e);
		
		 mockMvc.perform(
	                post("/exam/save")
	                .contentType(APPLICATION_JSON_UTF8)
	                .content(new ObjectMapper().writeValueAsString(e)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.date", is("2012-12-21")))
		.andExpect(jsonPath("$.teaching.name", is("Software Engineering")))
		.andExpect(jsonPath("$.hour", is("11.30")));
		
		ArgumentCaptor<Exam> uCaptor = ArgumentCaptor.forClass(Exam.class);
		
		verify(examServiceMock, times(1)).save(uCaptor.capture());
		verifyNoMoreInteractions(examServiceMock);
		
		
	}
	
	@Test
	public void getExamsByIdTeachingTest() throws Exception {
		Exam e = new Exam();
		Exam e2 = new Exam();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		e.setDate(d);
		e.setHour("11.30");
		Teaching t = new Teaching();
		t.setIdTeaching(1);
		t.setName("Software Engineering");
		t.setCfu(9);
		t.setCourseYear(2);
		e.setTeaching(t);
		Classroom c = new Classroom();
		c.setIdClassroom(1);
		c.setName("y1");
		e.setClassroom(c);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date d2 = sdf2.parse("2013-12-21");
		e2.setDate(d2);
		e2.setHour("12.30");
		Teaching t2 = new Teaching();
		t2.setIdTeaching(2);
		t2.setName("Software Engineering 2");
		t2.setCfu(12);
		t2.setCourseYear(3);
		e2.setTeaching(t2);
		Classroom c2 = new Classroom();
		c2.setIdClassroom(2);
		c2.setName("y2");
		e2.setClassroom(c2);
		
		when(examServiceMock.getExamsByIdTeaching(Mockito.anyInt())).thenReturn(Arrays.asList(e,e2));
		
		mockMvc.perform(get("/exam/getExamsByIdTeaching/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].date", is("2012-12-21")))
		.andExpect(jsonPath("$[1].date", is("2013-12-21")))
		.andExpect(jsonPath("$[0].teaching.name", is("Software Engineering")))
		.andExpect(jsonPath("$[1].teaching.name", is("Software Engineering 2")))
		.andExpect(jsonPath("$[0].hour", is("11.30")))
		.andExpect(jsonPath("$[1].hour", is("12.30")));
		
		
		
	
		
		verify(examServiceMock, times(1)).getExamsByIdTeaching(1);
		verifyNoMoreInteractions(examServiceMock);
		
		
		
		
	}
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
