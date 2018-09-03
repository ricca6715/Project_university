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
import java.util.ArrayList;
import java.util.Arrays;
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

import it.unisalento.se.saw.Iservices.ICalendarService;
import it.unisalento.se.saw.Iservices.IClassroomService;
import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.models.CalendarModel;

@RunWith(MockitoJUnitRunner.class)
public class CalendarRestControllerTest {
	
private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	
	@Mock
	ICalendarService calServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new CalendarRestController(calServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	
	
	@Test
	public void getAllTest() throws Exception {
		
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setStudycourses(scSet);
		
		when(calServiceMock.getAll()).thenReturn(Arrays.asList(c));
		
		mockMvc.perform(get("/calendar/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].academicYear", is("2018-2019")));
		
		verify(calServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(calServiceMock);
	}
	
	@Test
	public void saveTest() throws Exception {
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setIdCalendar(1);
		c.setStudycourses(scSet);
		
		when(calServiceMock.save(Mockito.any(Calendar.class))).thenReturn(c);
		
		 mockMvc.perform(
	                post("/calendar/save")
	                .contentType(APPLICATION_JSON_UTF8)
	                .content(new ObjectMapper().writeValueAsString(c)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.academicYear", is("2018-2019")))
		.andExpect(jsonPath("$.idCalendar", is(1)));
		
		ArgumentCaptor<Calendar> uCaptor = ArgumentCaptor.forClass(Calendar.class);
		
		verify(calServiceMock, times(1)).save(uCaptor.capture());
		verifyNoMoreInteractions(calServiceMock);
		
		
	}
	
	@Test
	public void updateTest() throws Exception {
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setIdCalendar(1);
		c.setStudycourses(scSet);
		c.setIdCalendar(1);
		
		when(calServiceMock.save(Mockito.any(Calendar.class))).thenReturn(c);
		
		 mockMvc.perform(
	                post("/calendar/save")
	                .contentType(APPLICATION_JSON_UTF8)
	                .content(new ObjectMapper().writeValueAsString(c)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.academicYear", is("2018-2019")))
		.andExpect(jsonPath("$.idCalendar", is(1)));
		
		ArgumentCaptor<Calendar> uCaptor = ArgumentCaptor.forClass(Calendar.class);
		
		verify(calServiceMock, times(1)).save(uCaptor.capture());
		verifyNoMoreInteractions(calServiceMock);
		
		
	}
	
	
	@Test
	public void getCalendarsByIdStudycourseTest() throws Exception {
		
		Calendar c = new Calendar();
		Set<Studycourse> scSet = new HashSet<>();
		scSet.add(new Studycourse("Software Engineering","test",null,null,null));
		c.setAcademicYear("2018-2019");
		c.setStudycourses(scSet);
		Calendar c2 = new Calendar();
		c2.setAcademicYear("2017-2018");
		c2.setStudycourses(scSet);
		
		when(calServiceMock.getCalendarsByIdStudycourse(Mockito.anyInt())).thenReturn(Arrays.asList(c,c2));
		
		mockMvc.perform(get("/calendar/getCalendarsByIdStudycourse/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].academicYear", is("2018-2019")))
		.andExpect(jsonPath("$[1].academicYear", is("2017-2018")));
		
		verify(calServiceMock, times(1)).getCalendarsByIdStudycourse(1);
		verifyNoMoreInteractions(calServiceMock);
	}
	
	
	
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
