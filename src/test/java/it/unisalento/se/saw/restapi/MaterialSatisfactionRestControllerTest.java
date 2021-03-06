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

import it.unisalento.se.saw.Iservices.ILectureSatisfactionService;
import it.unisalento.se.saw.Iservices.IMaterialSatisfactionService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Report;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.MaterialSatisfactionNotFound;

@RunWith(MockitoJUnitRunner.class)
public class MaterialSatisfactionRestControllerTest {
	
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private IMaterialSatisfactionService materialSatisfactionServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new MaterialSatisfactionRestController(materialSatisfactionServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	@Test
	public void getAverageRatingByIdMaterialTest() throws Exception {
	
		Double rating = 3.33;
		
		when(materialSatisfactionServiceMock.getAverageRatingByIdMaterial(1))
		.thenReturn(rating);
		
		mockMvc.perform(get("/materialsatisfaction/getAverageRatingByIdMaterial/{idMaterial}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", is(3.33)));
	
		verify(materialSatisfactionServiceMock, times(1)).getAverageRatingByIdMaterial(1);
		verifyNoMoreInteractions(materialSatisfactionServiceMock);
	}
	
	@Test
	public void getMaterialSatisfactionByIdMaterialTest() throws Exception {
	
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		tm.setLink("test");
		tm.setName("prova");
		tm.setType("link");
		Lecture l = new Lecture();
		l.setIdLecture(1);
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
		tm.setLecture(l);
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
		tm.setUser(user);
		Materialsatisfaction m1 = new Materialsatisfaction();
		m1.setIdMaterialSatisfaction(1);
		m1.setLevel(2);
		m1.setTeachingmaterial(tm);
		m1.setNote("prova");
		Materialsatisfaction m2 = new Materialsatisfaction();
		m2.setIdMaterialSatisfaction(2);
		m2.setLevel(5);
		m2.setTeachingmaterial(tm);
		m2.setNote("prova");
		
		when(materialSatisfactionServiceMock.getMaterialSatisfactionByIdMaterial(1))
		.thenReturn(Arrays.asList(m1, m2));
		
		mockMvc.perform(get("/materialsatisfaction/getMaterialSatisfactionByIdMaterial/{idMaterial}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].level", is(2)))
		.andExpect(jsonPath("$[0].note", is("prova")))
		.andExpect(jsonPath("$[1].level", is(5)))
		.andExpect(jsonPath("$[1].note", is("prova")));
	
		verify(materialSatisfactionServiceMock, times(1)).getMaterialSatisfactionByIdMaterial(1);
		verifyNoMoreInteractions(materialSatisfactionServiceMock);
	}
	
	@Test
	public void getMaterialSatisfactionByIdUserAndIdMaterialTest() throws Exception {
	
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		tm.setLink("test");
		tm.setName("prova");
		tm.setType("link");
		Lecture l = new Lecture();
		l.setIdLecture(1);
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
		tm.setLecture(l);
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
		tm.setUser(user);
		Materialsatisfaction m1 = new Materialsatisfaction();
		m1.setIdMaterialSatisfaction(1);
		m1.setLevel(2);
		m1.setTeachingmaterial(tm);
		m1.setNote("prova");
		
		
		when(materialSatisfactionServiceMock.getMaterialSatisfactionByIdUserAndIdMaterial(1, 1))
		.thenReturn(m1);
		
		mockMvc.perform(get("/materialsatisfaction/getMaterialSatisfactionByIdUserAndIdMaterial/{idUser}/{idMaterial}", 1, 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.level", is(2)))
		.andExpect(jsonPath("$.note", is("prova")));
		
		verify(materialSatisfactionServiceMock, times(1)).getMaterialSatisfactionByIdUserAndIdMaterial(1, 1);
		verifyNoMoreInteractions(materialSatisfactionServiceMock);
	}
	
	@Test
	public void getMaterialSatisfactionByIdUserAndIdMaterialErrorTest() throws Exception {
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		Materialsatisfaction m1 = new Materialsatisfaction();
		m1.setLevel(2);
		m1.setTeachingmaterial(tm);
		m1.setNote("prova");
		
		when(materialSatisfactionServiceMock.getMaterialSatisfactionByIdUserAndIdMaterial(1, 1))
		.thenThrow(new MaterialSatisfactionNotFound());
		
		mockMvc.perform(get("/materialsatisfaction/getMaterialSatisfactionByIdUserAndIdMaterial/{idUser}/{idMaterial}", 1, 1))
		.andExpect(status().isNotFound());
		
		verify(materialSatisfactionServiceMock, times(1)).getMaterialSatisfactionByIdUserAndIdMaterial(1, 1);
		verifyNoMoreInteractions(materialSatisfactionServiceMock);
	}
	
	@Test
	public void saveTest() throws  Exception {
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		Materialsatisfaction m1 = new Materialsatisfaction();
		m1.setLevel(2);
		m1.setTeachingmaterial(tm);
		User u = new User();
		u.setIdUser(1);
		m1.setUser(u);
		m1.setNote("prova");
		
		when(materialSatisfactionServiceMock.saveSatisfaction(Mockito.any(Materialsatisfaction.class)))
		.thenReturn(m1);
		
		mockMvc.perform(
                post("/materialsatisfaction/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(m1)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.level", is(2)))
		.andExpect(jsonPath("$.note", is("prova")));
		
		ArgumentCaptor<Materialsatisfaction> uCaptor = ArgumentCaptor.forClass(Materialsatisfaction.class);
		verify(materialSatisfactionServiceMock, times(1)).saveSatisfaction(uCaptor.capture());
		verifyNoMoreInteractions(materialSatisfactionServiceMock);
	}
	
	@Test
	public void updateTest() throws  Exception {
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		Materialsatisfaction m1 = new Materialsatisfaction();
		m1.setLevel(2);
		m1.setTeachingmaterial(tm);
		User u = new User();
		u.setIdUser(1);
		m1.setUser(u);
		m1.setIdMaterialSatisfaction(1);
		m1.setNote("prova");
		
		when(materialSatisfactionServiceMock.saveSatisfaction(Mockito.any(Materialsatisfaction.class)))
		.thenReturn(m1);
		
		mockMvc.perform(
                post("/materialsatisfaction/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(m1)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.idMaterialSatisfaction", is(1)))
		.andExpect(jsonPath("$.level", is(2)))
		.andExpect(jsonPath("$.note", is("prova")));
		
		ArgumentCaptor<Materialsatisfaction> uCaptor = ArgumentCaptor.forClass(Materialsatisfaction.class);
		verify(materialSatisfactionServiceMock, times(1)).saveSatisfaction(uCaptor.capture());
		verifyNoMoreInteractions(materialSatisfactionServiceMock);
	}
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


}
