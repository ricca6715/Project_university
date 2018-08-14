package it.unisalento.se.saw.models;



public class LecturesatisfactionModel {
	
    private Integer idlectureSatisfaction;
    private LectureModel lecture;
    private UserModel user;
    private Integer level;
	private String note;
    
    
    
	public Integer getIdlectureSatisfaction() {
		return idlectureSatisfaction;
	}
	public void setIdlectureSatisfaction(Integer idlectureSatisfaction) {
		this.idlectureSatisfaction = idlectureSatisfaction;
	}
	public LectureModel getLecture() {
		return lecture;
	}
	public void setLecture(LectureModel lecture) {
		this.lecture = lecture;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
     public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
