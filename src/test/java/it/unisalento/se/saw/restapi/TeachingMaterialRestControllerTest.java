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

import java.io.File;
import java.io.PrintWriter;
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
import it.unisalento.se.saw.Iservices.ITeachingMaterialService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class TeachingMaterialRestControllerTest {
	
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private ITeachingMaterialService teachingMaterialServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new TeachingMaterialRestController(teachingMaterialServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	@Test
	public void getTeachingMaterialByIdLectureTest() throws Exception {
		Teachingmaterial tm1 = new Teachingmaterial();
		tm1.setIdTeachingMaterial(1);
		tm1.setLink("C:\\Users\\ricca\\Desktop\\ciao.txt");
		tm1.setName("testMaterial");
		tm1.setLecture(new Lecture(new Classroom("y1", "y1classroom", null, null, null, null, null), new Teaching(null, "Database", null, null, null, null, null), null, null, null, null, null, null));
		tm1.setType("link");
		Teachingmaterial tm2 = new Teachingmaterial();
		tm2.setIdTeachingMaterial(2);
		tm2.setLink("C:\\Users\\ricca\\Desktop\\ciao2.txt");
		tm2.setName("testMaterial2");
		tm2.setLecture(new Lecture(new Classroom("y1", "y1classroom", null, null, null, null, null), new Teaching(null, "Database", null, null, null, null, null), null, null, null, null, null, null));
		tm2.setType("link");
		
		when(teachingMaterialServiceMock.getTeachingMaterialByIdLecture(1))
		.thenReturn(Arrays.asList(tm1, tm2));
		
		mockMvc.perform(get("/teachingmaterial/getTeachingMaterialByIdLecture/{idLecture}",1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].name", is("testMaterial")))
			.andExpect(jsonPath("$[0].type", is("link")))
			.andExpect(jsonPath("$[0].link", is("C:\\Users\\ricca\\Desktop\\ciao.txt")))
			.andExpect(jsonPath("$[1].name", is("testMaterial2")))
			.andExpect(jsonPath("$[1].type", is("link")))
			.andExpect(jsonPath("$[1].link", is("C:\\Users\\ricca\\Desktop\\ciao2.txt")));
			
		verify(teachingMaterialServiceMock, times(1)).getTeachingMaterialByIdLecture(1);
		verifyNoMoreInteractions(teachingMaterialServiceMock);
	}
	
	@Test
	public void removeMaterialByIdTest() throws Exception {
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		tm.setLink("C:\\Users\\ricca\\Desktop\\ciao.txt");
		tm.setName("testMaterial");
		tm.setLecture(new Lecture(new Classroom("y1", "y1classroom", null, null, null, null, null), new Teaching(null, "Database", null, null, null, null, null), null, null, null, null, null, null));
		tm.setType("link");
		boolean deleted = true;
		when(teachingMaterialServiceMock.getTeachingMaterialById(1)).thenReturn(tm);
		when(teachingMaterialServiceMock.removeMaterial(1)).thenReturn(deleted);
		
		mockMvc.perform(get("/teachingmaterial/delete/{idTeachingmaterial}",1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", is(deleted)));
			
		verify(teachingMaterialServiceMock, times(1)).getTeachingMaterialById(1);
		verify(teachingMaterialServiceMock, times(1)).removeMaterial(1);
		verifyNoMoreInteractions(teachingMaterialServiceMock);	
	}
	
	@Test
	public void removeFileMaterialByIdTest() throws Exception {
		PrintWriter writer = new PrintWriter("testfile.txt", "UTF-8");
		writer.println("provaprovaprova");
		writer.close();
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		tm.setLink("testfile.txt");
		tm.setName("testMaterial");
		tm.setLecture(new Lecture(new Classroom("y1", "y1classroom", null, null, null, null, null), new Teaching(null, "Database", null, null, null, null, null), null, null, null, null, null, null));
		tm.setType("txt");
		boolean deleted = true;
		when(teachingMaterialServiceMock.getTeachingMaterialById(1)).thenReturn(tm);
		when(teachingMaterialServiceMock.removeMaterial(1)).thenReturn(deleted);
		
		mockMvc.perform(get("/teachingmaterial/delete/{idTeachingmaterial}",1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", is(deleted)));
			
		verify(teachingMaterialServiceMock, times(1)).getTeachingMaterialById(1);
		verify(teachingMaterialServiceMock, times(1)).removeMaterial(1);
		verifyNoMoreInteractions(teachingMaterialServiceMock);	
	}
	
	@Test
	public void removeFileMaterialByIdErrorTest() throws Exception {
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		tm.setLink("testfile.txt");
		tm.setName("testMaterial");
		tm.setLecture(new Lecture(new Classroom("y1", "y1classroom", null, null, null, null, null), new Teaching(null, "Database", null, null, null, null, null), null, null, null, null, null, null));
		tm.setType("txt");
		boolean deleted = false;
		when(teachingMaterialServiceMock.getTeachingMaterialById(1)).thenReturn(tm);
		
		mockMvc.perform(get("/teachingmaterial/delete/{idTeachingmaterial}",1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", is(deleted)));
			
		verify(teachingMaterialServiceMock, times(1)).getTeachingMaterialById(1);
		verifyNoMoreInteractions(teachingMaterialServiceMock);	
	}
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}



}
