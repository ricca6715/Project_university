package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Report;
import it.unisalento.se.saw.exceptions.ReportNotFoundException;

public interface IReportService {
	public List<Report> getReportsByIdProfessor(int idProfessor);
	public List<Report> getAll();
	public Report getById(int idReport) throws ReportNotFoundException;
	public Report saveReport(Report report);
	public List<Report> getReportsByIdClassroom(int idClassroom);

}
