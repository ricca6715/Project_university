package it.unisalento.se.saw.models;

public class ReportModel {
	
	private Integer idReport;
    private ClassroomModel classroom;
    private ReportstatusModel reportstatus;
    private UserModel userBySecretaryIdSecretary;
    private UserModel userByProfessorIdProfessor;
    private String problemDescription;
    private String note;
    
    
	public Integer getIdReport() {
		return idReport;
	}
	public void setIdReport(Integer idReport) {
		this.idReport = idReport;
	}
	public ClassroomModel getClassroom() {
		return classroom;
	}
	public void setClassroom(ClassroomModel classroom) {
		this.classroom = classroom;
	}
	public ReportstatusModel getReportstatus() {
		return reportstatus;
	}
	public void setReportstatus(ReportstatusModel reportstatus) {
		this.reportstatus = reportstatus;
	}
	public UserModel getUserBySecretaryIdSecretary() {
		return userBySecretaryIdSecretary;
	}
	public void setUserBySecretaryIdSecretary(UserModel userBySecretaryIdSecretary) {
		this.userBySecretaryIdSecretary = userBySecretaryIdSecretary;
	}
	public UserModel getUserByProfessorIdProfessor() {
		return userByProfessorIdProfessor;
	}
	public void setUserByProfessorIdProfessor(UserModel userByProfessorIdProfessor) {
		this.userByProfessorIdProfessor = userByProfessorIdProfessor;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
