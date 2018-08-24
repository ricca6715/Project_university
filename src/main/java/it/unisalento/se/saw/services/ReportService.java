package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IReportService;
import it.unisalento.se.saw.domain.Report;
import it.unisalento.se.saw.exceptions.ReportNotFoundException;
import it.unisalento.se.saw.repositories.ReportRepository;

@Service
public class ReportService implements IReportService {

	@Autowired
	ReportRepository reportRepository;
	
	
	@Transactional
	public List<Report> getReportsByIdProfessor(int idProfessor)  {
		return reportRepository.getReportsByIdProfessor(idProfessor); 	//Get report by professor
	}
	
	@Transactional
	public List<Report> getAll(){
		return reportRepository.findAll();
		
	}

	@Transactional
	public Report getById(int idReport) throws ReportNotFoundException {
		Report r = reportRepository.getReportById(idReport);
		if (r == null) {
			throw new ReportNotFoundException();
		}
		return r;
	}


	@Transactional
	public Report saveReport(Report report) {
		return reportRepository.save(report);
	}

	@Transactional
	public List<Report> getReportsByIdClassroom(int idClassroom) {
		return reportRepository.getReportsByIdClassroom(idClassroom);
	}

	@Transactional
	public List<Report> getReportsByIdSecretary(int idSecretary) {
		// TODO Auto-generated method stub
		return reportRepository.getReportsByIdSecretary(idSecretary);
	}

	@Transactional
	public List<Report> getPendingReports() {
		return reportRepository.getPendingReports();
	}


}
