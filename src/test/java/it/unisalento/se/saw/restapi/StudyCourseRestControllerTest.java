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
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import it.unisalento.se.saw.Iservices.IReportStatusService;
import it.unisalento.se.saw.Iservices.IStudyCourseService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.StudycourseNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class StudyCourseRestControllerTest {
	
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private IStudyCourseService studyCourseServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new StudyCourseRestController(studyCourseServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	
	@Test
	public void getStudycourseByNameTest() throws Exception {
		
		Studycourse sc = new Studycourse();
		
		sc.setName("Software Engineering");
		sc.setDescription("Software engineering teaching");
		sc.setIdStudyCourse(1);
		
		
		when(studyCourseServiceMock.getStudycourseByName("Software Engineering")).thenReturn(sc);
		
		mockMvc.perform(get("/studycourse/getStudycourseByName/{name}", "Software Engineering"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.name", is("Software Engineering")))
			.andExpect(jsonPath("$.description", is("Software engineering teaching")))
			.andExpect(jsonPath("$.idStudyCourse", is(1)));
			
		
		verify(studyCourseServiceMock, times(1)).getStudycourseByName("Software Engineering");
		verifyNoMoreInteractions(studyCourseServiceMock);
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
		
		
		when(studyCourseServiceMock.getAll()).thenReturn(Arrays.asList(sc1, sc2));
		
		mockMvc.perform(get("/studycourse/getAll"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].name", is("Software Engineering")))
			.andExpect(jsonPath("$[0].description", is("Software engineering teaching")))
			.andExpect(jsonPath("$[0].idStudyCourse", is(1)))
			.andExpect(jsonPath("$[1].name", is("Database")))
			.andExpect(jsonPath("$[1].description", is("Database teaching")))
			.andExpect(jsonPath("$[1].idStudyCourse", is(2)));
			
		
		verify(studyCourseServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(studyCourseServiceMock);
	}
	
	@Test
	public void getStudycourseByInvalidNameTest() throws Exception {
		Studycourse sc = new Studycourse();
		
		sc.setName("Software Engineering");
		sc.setDescription("Software engineering teaching");
		sc.setIdStudyCourse(1);
		
		
		
		when(studyCourseServiceMock.getStudycourseByName(Mockito.anyString())).thenThrow(new StudycourseNotFoundException());
		
		mockMvc.perform(get("/studycourse/getStudycourseByName/{name}", "Software Engineering"))
		.andExpect(status().isNotFound());
	
		verify(studyCourseServiceMock, times(1)).getStudycourseByName(sc.getName());
		verifyNoMoreInteractions(studyCourseServiceMock);
		
	}
	
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}



}
