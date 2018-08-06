package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IReportService;
import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Report;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.ReportNotFoundException;
import it.unisalento.se.saw.models.ReportModel;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	IReportService reportService;

	public ReportRestController(IReportService reportService) {
		super();
		this.reportService = reportService;
	}
	
	@GetMapping(
			value = "/getReportsByIdProfessor/{idProfessor}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Report> getReportsByIdProfessor(@PathVariable("idProfessor") int idProfessor) {
		return reportService.getReportsByIdProfessor(idProfessor);
	}
	
	@GetMapping(
			value = "/getAll",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Report> getAll() {
		return reportService.getAll();
	}
	
	@GetMapping(
			value = "/getReportById/{idReport}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Report getReportById(@PathVariable("idReport") int idReport) throws ReportNotFoundException{
		return reportService.getById(idReport);
	}
	
	
	 @PostMapping(
			   value="/save",
			   produces = MediaType.APPLICATION_JSON_VALUE,
			   consumes = MediaType.APPLICATION_JSON_VALUE)
	 public Report saveReport(@RequestBody ReportModel reportModel) {
		 Report report = new Report();
		 report.setIdReport(reportModel.getIdReport());
		 Classroom cls = new Classroom();
		 cls.setIdClassroom(reportModel.getClassroom().getIdClassroom());
		 report.setClassroom(cls);
		 Reportstatus st = new Reportstatus();
		 st.setIdreportStatus(1);
		 report.setReportstatus(st);
		 User prof = new User();
		 prof.setIdUser(reportModel.getUserByProfessorIdProfessor().getIdUser());
		 report.setUserByProfessorIdProfessor(prof);
		 report.setProblemDescription(reportModel.getProblemDescription());
		 return reportService.saveReport(report);
	 }
	 
	 
	 @GetMapping(
				value = "/getReportsByIdClassroom/{idClassroom}",
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Report> getReportsByIdClassroom(@PathVariable("idClassroom") int idClassroom) {
			return reportService.getReportsByIdClassroom(idClassroom);
		}
	

}
