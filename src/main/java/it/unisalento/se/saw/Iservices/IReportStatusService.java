package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.exceptions.ReportstatusNotFoundException;

public interface IReportStatusService {
	
	public Reportstatus getReportStatusById(int idReportStatus) throws ReportstatusNotFoundException;
	public List<Reportstatus> getReportStatusforMod();
}
