package it.unisalento.se.saw.models;

public class ClassroomModel {

    private Integer idClassroom;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    
	public Integer getIdClassroom() {
		return idClassroom;
	}
	public void setIdClassroom(Integer idClassroom) {
		this.idClassroom = idClassroom;
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
