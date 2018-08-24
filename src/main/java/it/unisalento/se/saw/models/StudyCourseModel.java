package it.unisalento.se.saw.models;

import java.util.List;

public class StudyCourseModel {
	
    private Integer idStudyCourse;
    private String name;
    private String description;
    private List<CalendarModel> calendars;
    
    
    
    
	public List<CalendarModel> getCalendars() {
		return calendars;
	}
	public void setCalendars(List<CalendarModel> calendars) {
		this.calendars = calendars;
	}
	public Integer getIdStudyCourse() {
		return idStudyCourse;
	}
	public void setIdStudyCourse(Integer idStudyCourse) {
		this.idStudyCourse = idStudyCourse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
