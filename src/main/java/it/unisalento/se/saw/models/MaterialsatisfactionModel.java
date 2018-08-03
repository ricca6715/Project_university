package it.unisalento.se.saw.models;



public class MaterialsatisfactionModel {
	
    private int idMaterialSatisfaction;
    private TeachingMaterialModel teachingmaterial;
    private UserModel user;
    private Integer level;
    
    
    
	public int getIdMaterialSatisfaction() {
		return idMaterialSatisfaction;
	}
	public void setIdMaterialSatisfaction(int idMaterialSatisfaction) {
		this.idMaterialSatisfaction = idMaterialSatisfaction;
	}
	public TeachingMaterialModel getTeachingmaterial() {
		return teachingmaterial;
	}
	public void setTeachingmaterial(TeachingMaterialModel teachingmaterial) {
		this.teachingmaterial = teachingmaterial;
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

}
