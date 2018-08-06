package it.unisalento.se.saw.restapi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IReportStatusService;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.exceptions.ReportstatusNotFoundException;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/reportstatus")
public class ReportStatusRestController {
	
	@Autowired
	IReportStatusService rsService;

	public ReportStatusRestController(IReportStatusService rsService) {
		super();
		this.rsService = rsService;
	}
	
	@GetMapping(
			value = "/getReportStatusById/{idReportStatus}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public Reportstatus getReportStatusById(@PathVariable("idReportStatus") int idReportStatus) throws ReportstatusNotFoundException {
		return rsService.getReportStatusById(idReportStatus);
	}
	
}
