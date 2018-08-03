package it.unisalento.se.saw.models;



public class TeachingMaterialModel {
	
    private Integer idTeachingMaterial;
    private LectureModel lecture;
    private UserModel user;
    private String type;
    private String name;
    private String link;
    
    
    
	public Integer getIdTeachingMaterial() {
		return idTeachingMaterial;
	}
	public void setIdTeachingMaterial(Integer idTeachingMaterial) {
		this.idTeachingMaterial = idTeachingMaterial;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

    
    
}
