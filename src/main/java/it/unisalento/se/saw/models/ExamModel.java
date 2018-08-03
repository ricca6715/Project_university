package it.unisalento.se.saw.models;

import java.util.Date;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Teaching;

public class ExamModel {
	
    private Integer idExam;
    private ClassroomModel classroom;
    private TeachingModel teaching;
    private Date date;
    private String hour;
    
	public Integer getIdExam() {
		return idExam;
	}
	public void setIdExam(Integer idExam) {
		this.idExam = idExam;
	}
	public ClassroomModel getClassroom() {
		return classroom;
	}
	public void setClassroom(ClassroomModel classroom) {
		this.classroom = classroom;
	}
	public TeachingModel getTeaching() {
		return teaching;
	}
	public void setTeaching(TeachingModel teaching) {
		this.teaching = teaching;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
    

}
