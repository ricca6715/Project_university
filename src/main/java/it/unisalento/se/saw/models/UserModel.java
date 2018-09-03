package it.unisalento.se.saw.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel implements IValidateModel{
	
    private Integer idUser;
    private StudyCourseModel studycourse;
    private UserTypeModel usertype;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String fcmtoken;
    private Integer courseYear;
    private List<TeachingModel> teachings;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private IValidateStrategy validator;

	public String getFcmToken() {
		return fcmtoken;
	}
	public void setFcmToken(String fcmToken) {
		this.fcmtoken = fcmToken;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public StudyCourseModel getStudycourse() {
		return studycourse;
	}
	public void setStudycourse(StudyCourseModel studycourse) {
		this.studycourse = studycourse;
	}
	public UserTypeModel getUsertype() {
		return usertype;
	}
	public void setUsertype(UserTypeModel usertype) {
		this.usertype = usertype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCourseYear() {
		return courseYear;
	}
	public void setCourseYear(Integer courseYear) {
		this.courseYear = courseYear;
	}
	public List<TeachingModel> getTeachings() {
		return teachings;
	}
	public void setTeachings(List<TeachingModel> teachings) {
		this.teachings = teachings;
	}
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void setValidator(IValidateStrategy validator) {
		this.validator = validator;
	}
	@Override
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public boolean isValid() {
		// TODO Auto-generated method stub
		return this.validator.isValid(this);
	}
	@Override
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getError() {
		// TODO Auto-generated method stub
		return this.validator.getError(this);
	}


}
