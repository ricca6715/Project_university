package it.unisalento.se.saw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IReportStatusService;
import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.exceptions.ReportstatusNotFoundException;
import it.unisalento.se.saw.repositories.ReportStatusRepository;

@Service
public class ReportStatusService implements IReportStatusService {

	@Autowired
	ReportStatusRepository rsRepository;
	
	@Transactional
	public Reportstatus getReportStatusById(int idReportStatus) throws ReportstatusNotFoundException {

		Reportstatus rs = rsRepository.getReportStatusById(idReportStatus);
		if (rs == null) {
			throw new ReportstatusNotFoundException();
		}
		return rs;
	}
	
}
