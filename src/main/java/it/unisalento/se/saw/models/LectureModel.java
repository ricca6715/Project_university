package it.unisalento.se.saw.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LectureModel {
	
    private Integer idLecture;
    private ClassroomModel classroom;
    private TeachingModel teaching;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Europe/Rome")
    private Date date;
    private String starttime;
    private String endtime;
    
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}
