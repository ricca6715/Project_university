package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.User;

public class TeachingModel {
	
    private Integer idTeaching;
    private UserModel user;
    private String name;
    private Integer cfu;
    
    
    
	public Integer getIdTeaching() {
		return idTeaching;
	}
	public void setIdTeaching(Integer idTeaching) {
		this.idTeaching = idTeaching;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCfu() {
		return cfu;
	}
	public void setCfu(Integer cfu) {
		this.cfu = cfu;
	}

}
