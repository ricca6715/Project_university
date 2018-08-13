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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.ServletContextLiveBeansView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IMaterialSatisfactionService;
import it.unisalento.se.saw.Iservices.IReportService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Report;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.ReportNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ReportRestControllerTest {
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	
	@Mock
	private IReportService reportServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new ReportRestController(reportServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	@Test
	public void getReportsByIdProfessorTest() throws Exception {
		
		Report r1 = new Report();
		r1.setProblemDescription("Aula piccola");
		Classroom cl1 = new Classroom();
		cl1.setIdClassroom(1);
		cl1.setName("i1");
		r1.setClassroom(cl1);
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		r1.setReportstatus(rs1);
		
		Report r2 = new Report();
		r2.setProblemDescription("Proiettore rotto");
		Classroom cl2 = new Classroom();
		cl2.setIdClassroom(2);
		cl2.setName("i5");
		r2.setClassroom(cl2);
		r2.setReportstatus(rs1);
		
		when(reportServiceMock.getReportsByIdProfessor(1)).thenReturn(Arrays.asList(r1, r2));
		
		mockMvc.perform(get("/report/getReportsByIdProfessor/{idProfessor}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].problemDescription", is("Aula piccola")))
		.andExpect(jsonPath("$[0].classroom.name", is("i1")))
		.andExpect(jsonPath("$[0].reportstatus.name", is("In progress")))
		.andExpect(jsonPath("$[1].problemDescription", is("Proiettore rotto")))
		.andExpect(jsonPath("$[1].classroom.name", is("i5")))
		.andExpect(jsonPath("$[1].reportstatus.name", is("In progress")));
	
		verify(reportServiceMock, times(1)).getReportsByIdProfessor(1);
		verifyNoMoreInteractions(reportServiceMock);
	}
	
	@Test
	public void getAllTest() throws Exception {
		
		Report r1 = new Report();
		r1.setProblemDescription("Aula piccola");
		Classroom cl1 = new Classroom();
		cl1.setIdClassroom(1);
		cl1.setName("i1");
		r1.setClassroom(cl1);
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		r1.setReportstatus(rs1);
		
		Report r2 = new Report();
		r2.setProblemDescription("Proiettore rotto");
		Classroom cl2 = new Classroom();
		cl2.setIdClassroom(2);
		cl2.setName("i5");
		r2.setClassroom(cl2);
		r2.setReportstatus(rs1);
		
		when(reportServiceMock.getAll()).thenReturn(Arrays.asList(r1, r2));
		
		mockMvc.perform(get("/report/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].problemDescription", is("Aula piccola")))
		.andExpect(jsonPath("$[0].classroom.name", is("i1")))
		.andExpect(jsonPath("$[0].reportstatus.name", is("In progress")))
		.andExpect(jsonPath("$[1].problemDescription", is("Proiettore rotto")))
		.andExpect(jsonPath("$[1].classroom.name", is("i5")))
		.andExpect(jsonPath("$[1].reportstatus.name", is("In progress")));
	
		verify(reportServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(reportServiceMock);
	}
	
	@Test
	public void getReportByIdTest() throws Exception {
		
		Report r1 = new Report();
		r1.setProblemDescription("Aula piccola");
		Classroom cl1 = new Classroom();
		cl1.setIdClassroom(1);
		cl1.setName("i1");
		r1.setClassroom(cl1);
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		r1.setReportstatus(rs1);
		
		when(reportServiceMock.getById(1)).thenReturn(r1);
		
		mockMvc.perform(get("/report/getReportById/{idReport}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.problemDescription", is("Aula piccola")))
		.andExpect(jsonPath("$.classroom.name", is("i1")))
		.andExpect(jsonPath("$.reportstatus.name", is("In progress")));
		
		verify(reportServiceMock, times(1)).getById(1);
		verifyNoMoreInteractions(reportServiceMock);
	}
	
	@Test
	public void getReportByIdErrorTest() throws Exception {
		
		Report r1 = new Report();
		r1.setProblemDescription("Aula piccola");
		Classroom cl1 = new Classroom();
		cl1.setIdClassroom(1);
		cl1.setName("i1");
		r1.setClassroom(cl1);
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		r1.setReportstatus(rs1);
		
		when(reportServiceMock.getById(1))
		.thenThrow(new ReportNotFoundException());
		
		mockMvc.perform(get("/report/getReportById/{idReport}", 1))
		.andExpect(status().isNotFound());
		
		verify(reportServiceMock, times(1)).getById(1);
		verifyNoMoreInteractions(reportServiceMock);
	}
	
	@Test
	public void getReportsByIdClassroomTest() throws Exception {
		
		Report r1 = new Report();
		r1.setProblemDescription("Aula piccola");
		Classroom cl1 = new Classroom();
		cl1.setIdClassroom(1);
		cl1.setName("i1");
		r1.setClassroom(cl1);
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		r1.setReportstatus(rs1);
		
		Report r2 = new Report();
		r2.setProblemDescription("Proiettore rotto");
		r2.setClassroom(cl1);
		r2.setReportstatus(rs1);
		
		when(reportServiceMock.getReportsByIdClassroom(1)).thenReturn(Arrays.asList(r1, r2));
		
		mockMvc.perform(get("/report/getReportsByIdClassroom/{idClassroom}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].problemDescription", is("Aula piccola")))
		.andExpect(jsonPath("$[0].classroom.name", is("i1")))
		.andExpect(jsonPath("$[0].reportstatus.name", is("In progress")))
		.andExpect(jsonPath("$[1].problemDescription", is("Proiettore rotto")))
		.andExpect(jsonPath("$[1].classroom.name", is("i1")))
		.andExpect(jsonPath("$[1].reportstatus.name", is("In progress")));
		
		verify(reportServiceMock, times(1)).getReportsByIdClassroom(1);
		verifyNoMoreInteractions(reportServiceMock);
	}
	

	@Test
	public void saveReportTest() throws Exception {
		
		Report r1 = new Report();
		r1.setProblemDescription("Aula piccola");
		Classroom cl1 = new Classroom();
		cl1.setIdClassroom(1);
		cl1.setName("i1");
		r1.setClassroom(cl1);
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		r1.setReportstatus(rs1);
		User professor = new User();
		professor.setIdUser(1);
		r1.setUserByProfessorIdProfessor(professor);
		
		when(reportServiceMock.saveReport(Mockito.any(Report.class))).thenReturn(r1);
		
		mockMvc.perform(post("/report/save")
				.contentType(APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(r1)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.problemDescription", is("Aula piccola")))
		.andExpect(jsonPath("$.classroom.name", is("i1")))
		.andExpect(jsonPath("$.reportstatus.name", is("In progress")))
		.andExpect(jsonPath("$.userByProfessorIdProfessor.idUser", is(1)));
		
		ArgumentCaptor<Report> uCaptor = ArgumentCaptor.forClass(Report.class);

		verify(reportServiceMock, times(1)).saveReport(uCaptor.capture());
		verifyNoMoreInteractions(reportServiceMock);
		

	}
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


}
