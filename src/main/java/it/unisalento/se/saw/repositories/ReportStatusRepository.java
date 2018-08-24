package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Reportstatus;

@Repository
public interface ReportStatusRepository extends JpaRepository<Reportstatus, Integer> {

	@Query("select rs from Reportstatus rs  where rs.idreportStatus=:idReportStatus ")
	public Reportstatus getReportStatusById(@Param("idReportStatus") int idReportStatus);

	@Query("select rs from Reportstatus rs where rs.idreportStatus!=1 and rs.idreportStatus!=3")
	public List<Reportstatus> getReportStatusforMod();
	
}