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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.ITeachingService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.StudyCourseModel;
import it.unisalento.se.saw.models.TeachingModel;
import it.unisalento.se.saw.models.UserModel;
import it.unisalento.se.saw.models.UserTypeModel;

@RunWith(MockitoJUnitRunner.class)
public class TeachingRestControllerTest {
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private ITeachingService teachingServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new TeachingRestController(teachingServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
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
		
		when(teachingServiceMock.getTeachingByName("Software Engineering")).thenReturn(t);
		
		mockMvc.perform(get("/teaching/getTeachingByName/{name}", "Software Engineering"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.name", is("Software Engineering")))
		.andExpect(jsonPath("$.cfu", is(9)))
		.andExpect(jsonPath("$.user.name", is("luca")))
		.andExpect(jsonPath("$.user.surname", is("mainetti")));
	
		verify(teachingServiceMock, times(1)).getTeachingByName("Software Engineering");
		verifyNoMoreInteractions(teachingServiceMock);
	}
	
	@Test
	public void getTeachingByNameErrorTest() throws Exception {
		
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
		
		when(teachingServiceMock.getTeachingByName(Mockito.anyString()))
		.thenThrow(new TeachingNotFoundException());
		
		mockMvc.perform(get("/teaching/getTeachingByName/{name}", "Software Engineering"))
		.andExpect(status().isNotFound());
	
		verify(teachingServiceMock, times(1)).getTeachingByName("Software Engineering");
		verifyNoMoreInteractions(teachingServiceMock);
	}
	
	@Test
	public void getAllTest() throws Exception {
		
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		
		
		when(teachingServiceMock.getAll()).thenReturn(Arrays.asList(t1, t2));
		
		mockMvc.perform(get("/teaching/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].name", is("Software Engineering")))
		.andExpect(jsonPath("$[0].cfu", is(9)))
		.andExpect(jsonPath("$[1].name", is("Robotics")))
		.andExpect(jsonPath("$[1].cfu", is(12)));
	
		verify(teachingServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(teachingServiceMock);
	}
	
	@Test
	public void getTeachingByStudyCourseTest() throws Exception {
		
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		
		
		when(teachingServiceMock.getTeachingByStudyCourse(1)).thenReturn(Arrays.asList(t1, t2));
		
		mockMvc.perform(get("/teaching/getTeachingByStudyCourse/{id}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].name", is("Software Engineering")))
		.andExpect(jsonPath("$[0].cfu", is(9)))
		.andExpect(jsonPath("$[1].name", is("Robotics")))
		.andExpect(jsonPath("$[1].cfu", is(12)));
	
		verify(teachingServiceMock, times(1)).getTeachingByStudyCourse(1);
		verifyNoMoreInteractions(teachingServiceMock);
	}
	
	@Test
	public void getTeachingByIdProfessorTest() throws Exception {
		
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		
		
		when(teachingServiceMock.getTeachingsByIdProfessor(1)).thenReturn(Arrays.asList(t1, t2));
		
		mockMvc.perform(get("/teaching/getTeachingByIdProfessor/{id}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].name", is("Software Engineering")))
		.andExpect(jsonPath("$[0].cfu", is(9)))
		.andExpect(jsonPath("$[1].name", is("Robotics")))
		.andExpect(jsonPath("$[1].cfu", is(12)));
	
		verify(teachingServiceMock, times(1)).getTeachingsByIdProfessor(1);
		verifyNoMoreInteractions(teachingServiceMock);
	}
	
	@Test
	public void getTeachingByIdStudentTest() throws Exception {
		
		Teaching t1 = new Teaching();
		t1.setName("Software Engineering");
		t1.setCfu(9);
		
		Teaching t2 = new Teaching();
		t2.setName("Robotics");
		t2.setCfu(12);
		
		
		when(teachingServiceMock.getTeachingsByIdStudent(1)).thenReturn(Arrays.asList(t1, t2));
		
		mockMvc.perform(get("/teaching/getTeachingByIdStudent/{id}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].name", is("Software Engineering")))
		.andExpect(jsonPath("$[0].cfu", is(9)))
		.andExpect(jsonPath("$[1].name", is("Robotics")))
		.andExpect(jsonPath("$[1].cfu", is(12)));
	
		verify(teachingServiceMock, times(1)).getTeachingsByIdStudent(1);
		verifyNoMoreInteractions(teachingServiceMock);
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
		
		TeachingModel t1 = new TeachingModel();
		t1.setIdTeaching(1);
		t1.setName("Software Engineering");
		t1.setCfu(9);
		t1.setCourseYear(1);
		UserModel professor1 = new UserModel();
		professor1.setName("luca");
		professor1.setSurname("mainetti");
		professor1.setEmail("luca@gmail.com");
		professor1.setPassword("luca");
		UserTypeModel utm2 = new UserTypeModel();
		utm2.setTypeName("professor");
		professor1.setUsertype(utm2);
		t1.setUser(professor1);
		StudyCourseModel scm = new StudyCourseModel();
		scm.setIdStudyCourse(1);
		scm.setName("prova");
		scm.setDescription("test");
		List<StudyCourseModel> sclist1 = new ArrayList<>();
		sclist1.add(scm);
		t1.setStudycourses(sclist1);
		
		when(teachingServiceMock.save(Mockito.any(Teaching.class))).thenReturn(t);
		
		mockMvc.perform(post("/teaching/save")
				.contentType(APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(t1)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.name", is("Software Engineering")))
		.andExpect(jsonPath("$.cfu", is(9)));
	
		ArgumentCaptor<Teaching> uCaptor = ArgumentCaptor.forClass(Teaching.class);
		verify(teachingServiceMock, times(1)).save(uCaptor.capture());
		verifyNoMoreInteractions(teachingServiceMock);
	}
	
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
