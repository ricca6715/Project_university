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

import it.unisalento.se.saw.Iservices.IReportService;
import it.unisalento.se.saw.Iservices.IReportStatusService;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.ReportstatusNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ReportStatusControllerTest {
	
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private IReportStatusService reportStatusServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new ReportStatusRestController(reportStatusServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	@Test
	public void getReportStatusByIdTest() throws Exception {
		
		Reportstatus rs = new Reportstatus();
		rs.setIdreportStatus(1);
		rs.setName("In progress");
		
		when(reportStatusServiceMock.getReportStatusById(1)).thenReturn(rs);
		
		mockMvc.perform(get("/reportstatus/getReportStatusById/{idReportStatus}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.idreportStatus", is(1)))
		.andExpect(jsonPath("$.name", is("In progress")));
	
		verify(reportStatusServiceMock, times(1)).getReportStatusById(1);
		verifyNoMoreInteractions(reportStatusServiceMock);
	}
	
	@Test
	public void getReportStatusByIdErrorTest() throws Exception {
		
		Reportstatus rs = new Reportstatus();
		rs.setIdreportStatus(1);
		rs.setName("Pending");
		
		when(reportStatusServiceMock.getReportStatusById(Mockito.any(int.class)))
		.thenThrow(new ReportstatusNotFoundException());
		
		mockMvc.perform(get("/reportstatus/getReportStatusById/{idReportStatus}", 1))
		.andExpect(status().isNotFound());
	
		verify(reportStatusServiceMock, times(1)).getReportStatusById(1);
		verifyNoMoreInteractions(reportStatusServiceMock);
	}
	
	@Test
	public void getReportStatusforModTest() throws Exception {
		
		Reportstatus rs1 = new Reportstatus();
		rs1.setIdreportStatus(4);
		rs1.setName("Resolved");
		
		Reportstatus rs2 = new Reportstatus();
		rs2.setIdreportStatus(2);
		rs2.setName("In Progress");
		
		when(reportStatusServiceMock.getReportStatusforMod()).thenReturn(Arrays.asList(rs1,rs2));
		
		mockMvc.perform(get("/reportstatus/getReportStatusforMod"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].idreportStatus", is(4)))
		.andExpect(jsonPath("$[0].name", is("Resolved")))
		.andExpect(jsonPath("$[1].idreportStatus", is(2)))
		.andExpect(jsonPath("$[1].name", is("In Progress")));
	
		verify(reportStatusServiceMock, times(1)).getReportStatusforMod();
		verifyNoMoreInteractions(reportStatusServiceMock);
	}
	
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}



}
