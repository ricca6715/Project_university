package it.unisalento.se.saw.models;

import java.util.Date;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Teaching;

public class LectureModel {
	
    private Integer idLecture;
    private ClassroomModel classroom;
    private TeachingModel teaching;
    private Date date;
    private String hour;
    
    
    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getIdLecture() {
		return idLecture;
	}
	public void setIdLecture(Integer idLecture) {
		this.idLecture = idLecture;
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
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}

}
