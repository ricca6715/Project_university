package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.exceptions.ReportstatusNotFoundException;

public interface IReportStatusService {
	
	public Reportstatus getReportStatusById(int idReportStatus) throws ReportstatusNotFoundException;
	
}
