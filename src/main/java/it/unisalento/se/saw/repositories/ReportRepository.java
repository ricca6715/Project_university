package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
	@Query("select r from Report r "
			+ "where r.userByProfessorIdProfessor.idUser=:idProfessor ")
	public List<Report> getReportsByIdProfessor(@Param("idProfessor") int idProfessor );
	
	@Query("select r from Report r "
			+ "where r.idReport=:idReport ")
	public Report getReportById(@Param("idReport") int idReport );
	
	
	@Query("select r from Report r "
			+ "where r.classroom.idClassroom=:idClassroom ")
	public List<Report> getReportsByIdClassroom(@Param("idClassroom") int idClassroom );

	@Query("select r from Report r where r.userBySecretaryIdSecretary.idUser=:idSecretary")
	public List<Report> getReportsByIdSecretary(@Param("idSecretary") int idSecretary);
	
	@Query("select r from Report r where r.reportstatus.idreportStatus=1")
	public List<Report> getPendingReports();
}
