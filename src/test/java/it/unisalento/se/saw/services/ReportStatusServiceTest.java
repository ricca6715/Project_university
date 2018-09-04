package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Reportstatus;
import it.unisalento.se.saw.exceptions.ReportstatusNotFoundException;
import it.unisalento.se.saw.repositories.ReportStatusRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReportStatusServiceTest {
	
	@Mock
	private ReportStatusRepository rsrep;
	@InjectMocks
	private ReportStatusService rsService;
	
	@Test
    public void getReportStatusByIdTest() throws ReportstatusNotFoundException {
		
		Reportstatus rs = new Reportstatus();
		rs.setIdreportStatus(1);
		rs.setName("In progress");
		
		when(rsrep.getReportStatusById(1)).thenReturn(rs);
		
		Reportstatus rsback = rsService.getReportStatusById(1);
		
		assertEquals(rs.getIdreportStatus(), rsback.getIdreportStatus());
		assertEquals(rs.getName(), rsback.getName());
		
	}
	
	@Test
    public void getReportStatusForModTest() {
		
		Reportstatus rs1 = new Reportstatus();
		Reportstatus rs2 = new Reportstatus();
		
		rs1.setIdreportStatus(1);
		rs1.setName("In progress");
		rs2.setIdreportStatus(2);
		rs2.setName("Refused");
		
		when(rsrep.getReportStatusforMod()).thenReturn(Arrays.asList(rs1, rs2));
		
		List<Reportstatus> rslback = rsService.getReportStatusforMod();
		
		assertEquals(rs1.getIdreportStatus(), rslback.get(0).getIdreportStatus());
		assertEquals(rs1.getName(), rslback.get(0).getName());
		assertEquals(rs2.getIdreportStatus(), rslback.get(1).getIdreportStatus());
		assertEquals(rs2.getName(), rslback.get(1).getName());
		
	}

}
