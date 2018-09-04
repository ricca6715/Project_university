package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Report;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.repositories.LectureRepository;
import it.unisalento.se.saw.repositories.ReportRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {
	
	@Mock
	private ReportRepository reportRepositoryMock;
	@InjectMocks
	private ReportService reportService;
	
	
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
			r1.setNote("Test");
			
			Report r2 = new Report();
			r2.setProblemDescription("Proiettore rotto");
			Classroom cl2 = new Classroom();
			cl2.setIdClassroom(2);
			cl2.setName("i5");
			r2.setClassroom(cl2);
			r2.setReportstatus(rs1);
			r2.setNote("Test2");

			
			when(reportRepositoryMock.getReportsByIdProfessor(Mockito.anyInt())).thenReturn(Arrays.asList(r1, r2));
			
			List<Report> reportsBack = reportService.getReportsByIdProfessor(1);
			
			assertEquals(reportsBack.get(0).getProblemDescription() , r1.getProblemDescription() );
			assertEquals(reportsBack.get(0).getClassroom().getName() , r1.getClassroom().getName() );
			assertEquals(reportsBack.get(0).getNote() , r1.getNote() );
			assertEquals(reportsBack.get(1).getProblemDescription() , r2.getProblemDescription() );
			assertEquals(reportsBack.get(1).getClassroom().getName() , r2.getClassroom().getName() );
			assertEquals(reportsBack.get(1).getNote() , r2.getNote() );
		 
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
			r1.setNote("Test");
			
			Report r2 = new Report();
			r2.setProblemDescription("Proiettore rotto");
			Classroom cl2 = new Classroom();
			cl2.setIdClassroom(2);
			cl2.setName("i5");
			r2.setClassroom(cl2);
			r2.setReportstatus(rs1);
			r2.setNote("Test2");

			
			when(reportRepositoryMock.findAll()).thenReturn(Arrays.asList(r1, r2));
			
			List<Report> reportsBack = reportService.getAll();
			assertEquals(reportsBack.get(0).getProblemDescription() , r1.getProblemDescription() );
			assertEquals(reportsBack.get(0).getClassroom().getName() , r1.getClassroom().getName() );
			assertEquals(reportsBack.get(0).getNote() , r1.getNote() );
			assertEquals(reportsBack.get(1).getProblemDescription() , r2.getProblemDescription() );
			assertEquals(reportsBack.get(1).getClassroom().getName() , r2.getClassroom().getName() );
			assertEquals(reportsBack.get(1).getNote() , r2.getNote() );
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
		r1.setNote("Test");
		
		when(reportRepositoryMock.getReportById(Mockito.anyInt())).thenReturn(r1);
		
		Report rback = reportService.getById(1);
		
		assertEquals(rback.getProblemDescription() , r1.getProblemDescription() );
		assertEquals(rback.getClassroom().getName() , r1.getClassroom().getName() );
		assertEquals(rback.getNote() , r1.getNote() );
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
		r1.setNote("Test");
		
		Report r2 = new Report();
		r2.setProblemDescription("Proiettore rotto");
		Classroom cl2 = new Classroom();
		cl2.setIdClassroom(2);
		cl2.setName("i5");
		r2.setClassroom(cl2);
		r2.setReportstatus(rs1);
		r2.setNote("Test2");
		

		
		when(reportRepositoryMock.getReportsByIdClassroom(Mockito.anyInt())).thenReturn(Arrays.asList(r1, r2));
		
		List<Report> reportsBack = reportService.getReportsByIdClassroom(1);
		assertEquals(reportsBack.get(0).getProblemDescription() , r1.getProblemDescription() );
		assertEquals(reportsBack.get(0).getClassroom().getName() , r1.getClassroom().getName() );
		assertEquals(reportsBack.get(0).getNote() , r1.getNote() );
		assertEquals(reportsBack.get(1).getProblemDescription() , r2.getProblemDescription() );
		assertEquals(reportsBack.get(1).getClassroom().getName() , r2.getClassroom().getName() );
		assertEquals(reportsBack.get(1).getNote() , r2.getNote() );
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
		r1.setNote("Test");
		
		when(reportRepositoryMock.save(Mockito.any(Report.class))).thenReturn(r1);
		
		Report rback = reportService.saveReport(r1);
		
		assertEquals(rback.getProblemDescription() , r1.getProblemDescription() );
		assertEquals(rback.getClassroom().getName() , r1.getClassroom().getName() );
		assertEquals(rback.getNote() , r1.getNote() );
	}
	
	@Test
	public void getPendingReportsTest() throws Exception {
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
		r1.setNote("Test");
		
		Report r2 = new Report();
		r2.setProblemDescription("Proiettore rotto");
		Classroom cl2 = new Classroom();
		cl2.setIdClassroom(2);
		cl2.setName("i5");
		r2.setClassroom(cl2);
		r2.setReportstatus(rs1);
		r2.setNote("Test2");
		

		
		when(reportRepositoryMock.getPendingReports()).thenReturn(Arrays.asList(r1, r2));
		
		List<Report> reportsBack = reportService.getPendingReports();
		assertEquals(reportsBack.get(0).getProblemDescription() , r1.getProblemDescription() );
		assertEquals(reportsBack.get(0).getClassroom().getName() , r1.getClassroom().getName() );
		assertEquals(reportsBack.get(0).getNote() , r1.getNote() );
		assertEquals(reportsBack.get(1).getProblemDescription() , r2.getProblemDescription() );
		assertEquals(reportsBack.get(1).getClassroom().getName() , r2.getClassroom().getName() );
		assertEquals(reportsBack.get(1).getNote() , r2.getNote() );
	}
	
	@Test
	public void getReportsByIdSecretaryTest() throws Exception {
		
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
		r1.setNote("Test");
		
		Report r2 = new Report();
		r2.setProblemDescription("Proiettore rotto");
		Classroom cl2 = new Classroom();
		cl2.setIdClassroom(2);
		cl2.setName("i5");
		r2.setClassroom(cl2);
		r2.setReportstatus(rs1);
		r2.setNote("Test2");
		

		
		when(reportRepositoryMock.getReportsByIdSecretary(Mockito.anyInt())).thenReturn(Arrays.asList(r1, r2));
		
		List<Report> reportsBack = reportService.getReportsByIdSecretary(2);
		assertEquals(reportsBack.get(0).getProblemDescription() , r1.getProblemDescription() );
		assertEquals(reportsBack.get(0).getClassroom().getName() , r1.getClassroom().getName() );
		assertEquals(reportsBack.get(0).getNote() , r1.getNote() );
		assertEquals(reportsBack.get(1).getProblemDescription() , r2.getProblemDescription() );
		assertEquals(reportsBack.get(1).getClassroom().getName() , r2.getClassroom().getName() );
		assertEquals(reportsBack.get(1).getNote() , r2.getNote() );
		
	}


}
