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
import java.util.Collections;
import java.util.Date;
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
import com.mysql.fabric.xmlrpc.base.Array;

import it.unisalento.se.saw.Iservices.IClassroomService;
import it.unisalento.se.saw.Iservices.ILectureService;
import it.unisalento.se.saw.Iservices.ITeachingService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.exceptions.LectureNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class LectureRestControllerTest {
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private ILectureService lectureServiceMock;
	@Mock
	private ITeachingService teachingServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new LectureRestController(lectureServiceMock, teachingServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	
	@Test
	public void getLecturesByDateTest() throws Exception {

		
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setDescription("test lecture");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		
		when(lectureServiceMock.getLecturesByDate(d)).thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecture/getLecturesByDate/{date}", "2012-12-21"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].date", is("2012-12-21")))
			.andExpect(jsonPath("$[0].idLecture", is(1)))
			.andExpect(jsonPath("$[0].starttime", is("15-15")))
			.andExpect(jsonPath("$[0].description", is("it was a good lesson")))
			.andExpect(jsonPath("$[0].endtime", is("18-15")))
			.andExpect(jsonPath("$[0].teaching.name", is("Database")))
			.andExpect(jsonPath("$[0].classroom.name", is("y1")))
			.andExpect(jsonPath("$[1].date", is("2012-12-21")))
			.andExpect(jsonPath("$[1].idLecture", is(2)))
			.andExpect(jsonPath("$[1].starttime", is("15-15")))
			.andExpect(jsonPath("$[1].description", is("test lecture")))
			.andExpect(jsonPath("$[1].endtime", is("18-15")))
			.andExpect(jsonPath("$[1].teaching.name", is("Computer Vision")))
			.andExpect(jsonPath("$[1].classroom.name", is("y2")));
			
		
		verify(lectureServiceMock, times(1)).getLecturesByDate(d);
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	
	@Test
	public void getAllTest() throws Exception {

		
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setDescription("test lecture");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		
		when(lectureServiceMock.getAll()).thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecture/getAll", "2012-12-21"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].date", is("2012-12-21")))
			.andExpect(jsonPath("$[0].idLecture", is(1)))
			.andExpect(jsonPath("$[0].starttime", is("15-15")))
			.andExpect(jsonPath("$[0].description", is("it was a good lesson")))
			.andExpect(jsonPath("$[0].endtime", is("18-15")))
			.andExpect(jsonPath("$[0].teaching.name", is("Database")))
			.andExpect(jsonPath("$[0].classroom.name", is("y1")))
			.andExpect(jsonPath("$[1].date", is("2012-12-21")))
			.andExpect(jsonPath("$[1].idLecture", is(2)))
			.andExpect(jsonPath("$[1].starttime", is("15-15")))
			.andExpect(jsonPath("$[1].description", is("test lecture")))
			.andExpect(jsonPath("$[1].endtime", is("18-15")))
			.andExpect(jsonPath("$[1].teaching.name", is("Computer Vision")))
			.andExpect(jsonPath("$[1].classroom.name", is("y2")));
			
		
		verify(lectureServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	@Test
	public void getLectureByIdTest() throws Exception {
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		when(lectureServiceMock.getLectureById(1)).thenReturn(l1);
		
		mockMvc.perform(get("/lecture/getLectureById/{id}",1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.date", is("2012-12-21")))
			.andExpect(jsonPath("$.idLecture", is(1)))
			.andExpect(jsonPath("$.starttime", is("15-15")))
			.andExpect(jsonPath("$.description", is("it was a good lesson")))
			.andExpect(jsonPath("$.endtime", is("18-15")))
			.andExpect(jsonPath("$.teaching.name", is("Database")))
			.andExpect(jsonPath("$.classroom.name", is("y1")));
			
		
		verify(lectureServiceMock, times(1)).getLectureById(1);
		verifyNoMoreInteractions(lectureServiceMock);
		
	}
	
	@Test
	public void getLectureByIdErrorTest() throws Exception {
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		when(lectureServiceMock.getLectureById(1)).thenThrow(new LectureNotFoundException());
		
		mockMvc.perform(get("/lecture/getLectureById/{id}",1))
			.andExpect(status().isNotFound());
		
		verify(lectureServiceMock, times(1)).getLectureById(1);
		verifyNoMoreInteractions(lectureServiceMock);
		
	}
	
	
	@Test
	public void getLecturesByIdClassroomTest() throws Exception {
		
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setDescription("test lecture");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		
		when(lectureServiceMock.getLecturesByClassroom(1)).thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecture/getLectureByClassroom/{idClassroom}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].date", is("2012-12-21")))
			.andExpect(jsonPath("$[0].idLecture", is(1)))
			.andExpect(jsonPath("$[0].starttime", is("15-15")))
			.andExpect(jsonPath("$[0].description", is("it was a good lesson")))
			.andExpect(jsonPath("$[0].endtime", is("18-15")))
			.andExpect(jsonPath("$[0].teaching.name", is("Database")))
			.andExpect(jsonPath("$[0].classroom.name", is("y1")))
			.andExpect(jsonPath("$[1].date", is("2012-12-21")))
			.andExpect(jsonPath("$[1].idLecture", is(2)))
			.andExpect(jsonPath("$[1].starttime", is("15-15")))
			.andExpect(jsonPath("$[1].description", is("test lecture")))
			.andExpect(jsonPath("$[1].endtime", is("18-15")))
			.andExpect(jsonPath("$[1].teaching.name", is("Computer Vision")))
			.andExpect(jsonPath("$[1].classroom.name", is("y2")));
			
		
		verify(lectureServiceMock, times(1)).getLecturesByClassroom(1);
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	
	@Test
	public void getLecturesByIdTeachingTest() throws Exception {
		
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setDescription("test lecture");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		
		when(lectureServiceMock.getLecturesByIdTeaching(1)).thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecture/getLecturesByIdTeaching/{idTeaching}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].date", is("2012-12-21")))
			.andExpect(jsonPath("$[0].idLecture", is(1)))
			.andExpect(jsonPath("$[0].starttime", is("15-15")))
			.andExpect(jsonPath("$[0].description", is("it was a good lesson")))
			.andExpect(jsonPath("$[0].endtime", is("18-15")))
			.andExpect(jsonPath("$[0].teaching.name", is("Database")))
			.andExpect(jsonPath("$[0].classroom.name", is("y1")))
			.andExpect(jsonPath("$[1].date", is("2012-12-21")))
			.andExpect(jsonPath("$[1].idLecture", is(2)))
			.andExpect(jsonPath("$[1].starttime", is("15-15")))
			.andExpect(jsonPath("$[1].description", is("test lecture")))
			.andExpect(jsonPath("$[1].endtime", is("18-15")))
			.andExpect(jsonPath("$[1].teaching.name", is("Computer Vision")))
			.andExpect(jsonPath("$[1].classroom.name", is("y2")));
			
		
		verify(lectureServiceMock, times(1)).getLecturesByIdTeaching(1);
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	
	@Test
	public void getDailyLecturesByIdProfAndDateTest() throws Exception {
		
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(new Teaching(null, "Database", 9, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setDescription("test lecture");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));	
		
		when(lectureServiceMock.getDailyLectureByIdProfAndDate(1,d)).thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecture/getDailyLectureByIdProfAndDate/{idUser}/{date}", 1, "2012-12-21"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].date", is("2012-12-21")))
			.andExpect(jsonPath("$[0].idLecture", is(1)))
			.andExpect(jsonPath("$[0].starttime", is("15-15")))
			.andExpect(jsonPath("$[0].description", is("it was a good lesson")))
			.andExpect(jsonPath("$[0].endtime", is("18-15")))
			.andExpect(jsonPath("$[0].teaching.name", is("Database")))
			.andExpect(jsonPath("$[0].classroom.name", is("y1")))
			.andExpect(jsonPath("$[1].date", is("2012-12-21")))
			.andExpect(jsonPath("$[1].idLecture", is(2)))
			.andExpect(jsonPath("$[1].starttime", is("15-15")))
			.andExpect(jsonPath("$[1].description", is("test lecture")))
			.andExpect(jsonPath("$[1].endtime", is("18-15")))
			.andExpect(jsonPath("$[1].teaching.name", is("Computer Vision")))
			.andExpect(jsonPath("$[1].classroom.name", is("y2")));
			
		verify(lectureServiceMock, times(1)).getDailyLectureByIdProfAndDate(1, d);
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	@Test
	public void getDailyLecturesByIdStudentAndDateTest() throws Exception {
		
		Teaching t1 = new Teaching();
		t1.setIdTeaching(1);
		t1.setName("Software Engineering");
		t1.setCfu(9);
		
		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		l1.setTeaching(t1);
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setDescription("test lecture");
		l2.setTeaching(t1);
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		when(teachingServiceMock.getTeachingsByIdStudent(1)).thenReturn(Arrays.asList(t1));
		when(lectureServiceMock.getDailyLectureByIdTeachingAndDate(1, d)).thenReturn(Arrays.asList(l1, l2));
		
		mockMvc.perform(get("/lecture/getDailyLectureByIdStudent/{idUser}/{date}", 1, "2012-12-21"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].date", is("2012-12-21")))
			.andExpect(jsonPath("$[0].idLecture", is(1)))
			.andExpect(jsonPath("$[0].starttime", is("15-15")))
			.andExpect(jsonPath("$[0].description", is("it was a good lesson")))
			.andExpect(jsonPath("$[0].endtime", is("18-15")))

			.andExpect(jsonPath("$[0].teaching.name", is("Software Engineering")))
			.andExpect(jsonPath("$[0].classroom.name", is("y1")))
			.andExpect(jsonPath("$[1].date", is("2012-12-21")))
			.andExpect(jsonPath("$[1].idLecture", is(2)))
			.andExpect(jsonPath("$[1].starttime", is("15-15")))
			.andExpect(jsonPath("$[1].description", is("test lecture")))
			.andExpect(jsonPath("$[1].endtime", is("18-15")))
			.andExpect(jsonPath("$[1].teaching.name", is("Software Engineering")))
			.andExpect(jsonPath("$[1].classroom.name", is("y2")));
			
		
		verify(teachingServiceMock, times(1)).getTeachingsByIdStudent(1);
		verify(lectureServiceMock, times(1)).getDailyLectureByIdTeachingAndDate(t1.getIdTeaching(), d);
		verifyNoMoreInteractions(teachingServiceMock);
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	@Test
	public void saveLessonTest() throws Exception {

		Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setDescription("it was a good lesson");
		Teaching t = new Teaching(null, "Database", 9, null, null, null, null);
		t.setIdTeaching(1);
		l1.setTeaching(t);
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		when(lectureServiceMock.getLecturesByIdTeaching(1)).thenReturn(Collections.emptyList());
		when(lectureServiceMock.save(Mockito.any(Lecture.class))).thenReturn(l1);
		
		mockMvc.perform(post("/lecture/save")
                .contentType(APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(l1)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.date", is("2012-12-21")))
			.andExpect(jsonPath("$.idLecture", is(1)))
			.andExpect(jsonPath("$.starttime", is("15-15")))
			.andExpect(jsonPath("$.description", is("it was a good lesson")))
			.andExpect(jsonPath("$.endtime", is("18-15")))
			.andExpect(jsonPath("$.teaching.name", is("Database")))
			.andExpect(jsonPath("$.classroom.name", is("y1")));
			
		ArgumentCaptor<Lecture> uCaptor = ArgumentCaptor.forClass(Lecture.class);
		verify(lectureServiceMock, times(1)).getLecturesByIdTeaching(1);
		verify(lectureServiceMock, times(1)).save(uCaptor.capture());
		verifyNoMoreInteractions(lectureServiceMock);
	}
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


}
