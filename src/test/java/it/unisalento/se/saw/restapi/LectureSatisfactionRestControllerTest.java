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
import it.unisalento.se.saw.Iservices.ILectureService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.LectureSatisfactionNotFound;
import it.unisalento.se.saw.exceptions.MaterialSatisfactionNotFound;

@RunWith(MockitoJUnitRunner.class)
public class LectureSatisfactionRestControllerTest {
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private ILectureSatisfactionService lectureSatServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new LectureSatisfactionRestController(lectureSatServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	@Test
	public void getAverageRatingByIdLectureTest() throws Exception {
	
		Double rating = 3.33;
		
		when(lectureSatServiceMock.getAverageRatingByIdLecture(1))
		.thenReturn(rating);
		
		mockMvc.perform(get("/lecturesatisfaction/getAverageRatingByIdLecture/{idLecture}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", is(3.33)));
	
		verify(lectureSatServiceMock, times(1)).getAverageRatingByIdLecture(1);
		verifyNoMoreInteractions(lectureSatServiceMock);
	}
	
	@Test
	public void getLectureSatisfactionByIdLectureTest() throws Exception {
	
		Lecture l = new Lecture();
		l.setIdLecture(1);
		Lecturesatisfaction l1 = new Lecturesatisfaction();
		l1.setIdlectureSatisfaction(1);
		l1.setLevel(2);
		l1.setLecture(l);
		Lecturesatisfaction l2 = new Lecturesatisfaction();
		l2.setIdlectureSatisfaction(2);
		l2.setLevel(5);
		l2.setLecture(l);
		
		when(lectureSatServiceMock.getLectureSatisfactionsByIdLecture(1))
		.thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecturesatisfaction/getLectureSatisfactionsByIdLecture/{idLecture}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].level", is(2)))
		.andExpect(jsonPath("$[1].level", is(5)));
	
		verify(lectureSatServiceMock, times(1)).getLectureSatisfactionsByIdLecture(1);
		verifyNoMoreInteractions(lectureSatServiceMock);
	}
	
	@Test
	public void getLectureSatisfactionByIdUserAndIdLectureTest() throws Exception {
	
		Lecture l = new Lecture();
		l.setIdLecture(1);
		Lecturesatisfaction l1 = new Lecturesatisfaction();
		l1.setIdlectureSatisfaction(1);
		l1.setLevel(2);
		l1.setLecture(l);
		
		when(lectureSatServiceMock.getLectureSatisfactionByIdUserAndIdLecture(1, 1)).thenReturn(l1);
		
		mockMvc.perform(get("/lecturesatisfaction/getLectureSatisfactionByIdUserAndIdLecture/{idUser}/{idLecture}", 1, 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.level", is(2)));
		
		verify(lectureSatServiceMock, times(1)).getLectureSatisfactionByIdUserAndIdLecture(1, 1);
		verifyNoMoreInteractions(lectureSatServiceMock);
	}
	
	@Test
	public void getMaterialSatisfactionByIdUserAndIdMaterialErrorTest() throws Exception {
		Lecture l = new Lecture();
		l.setIdLecture(1);
		Lecturesatisfaction l1 = new Lecturesatisfaction();
		l1.setIdlectureSatisfaction(1);
		l1.setLevel(2);
		l1.setLecture(l);
		
		when(lectureSatServiceMock.getLectureSatisfactionByIdUserAndIdLecture(Mockito.anyInt(), Mockito.anyInt()))
		.thenThrow(new LectureSatisfactionNotFound());
		
		mockMvc.perform(get("/lecturesatisfaction/getLectureSatisfactionByIdUserAndIdLecture/{idUser}/{idLecture}", 1, 1))
		.andExpect(status().isNotFound());
		
		verify(lectureSatServiceMock, times(1)).getLectureSatisfactionByIdUserAndIdLecture(1, 1);
		verifyNoMoreInteractions(lectureSatServiceMock);
	}
	
	@Test
	public void saveTest() throws  Exception {
		Lecture l = new Lecture();
		l.setIdLecture(1);
		Lecturesatisfaction l1 = new Lecturesatisfaction();
		l1.setIdlectureSatisfaction(1);
		l1.setLevel(2);
		l1.setLecture(l);
		User u = new User();
		u.setIdUser(1);
		l1.setUser(u);
		
		when(lectureSatServiceMock.saveSatisfaction(Mockito.any(Lecturesatisfaction.class)))
		.thenReturn(l1);
		
		mockMvc.perform(
                post("/lecturesatisfaction/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(l1)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.level", is(2)));
		
		ArgumentCaptor<Lecturesatisfaction> uCaptor = ArgumentCaptor.forClass(Lecturesatisfaction.class);
		verify(lectureSatServiceMock, times(1)).saveSatisfaction(uCaptor.capture());
		verifyNoMoreInteractions(lectureSatServiceMock);
	}
	
	@Test
	public void updateTest() throws  Exception {
		Lecture l = new Lecture();
		l.setIdLecture(1);
		Lecturesatisfaction l1 = new Lecturesatisfaction();
		l1.setIdlectureSatisfaction(1);
		l1.setLevel(2);
		l1.setLecture(l);
		User u = new User();
		u.setIdUser(1);
		l1.setUser(u);
		l1.setIdlectureSatisfaction(1);
		
		when(lectureSatServiceMock.saveSatisfaction(Mockito.any(Lecturesatisfaction.class)))
		.thenReturn(l1);
		
		mockMvc.perform(
                post("/lecturesatisfaction/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(l1)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.level", is(2)));
		
		ArgumentCaptor<Lecturesatisfaction> uCaptor = ArgumentCaptor.forClass(Lecturesatisfaction.class);
		verify(lectureSatServiceMock, times(1)).saveSatisfaction(uCaptor.capture());
		verifyNoMoreInteractions(lectureSatServiceMock);
	}
	
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


}
