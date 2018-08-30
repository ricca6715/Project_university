package it.unisalento.se.saw.models;

public class MaterialsatisfactionModel {
	
    private Integer idMaterialSatisfaction;
    private TeachingMaterialModel teachingmaterial;
    private UserModel user;
    private Integer level;
    private String note;
    
	public MaterialsatisfactionModel() {
		super();
	}
	
	public MaterialsatisfactionModel(Integer idMaterialSatisfaction, TeachingMaterialModel teachingmaterial,
			UserModel user, Integer level, String note) {
		super();
		this.idMaterialSatisfaction = idMaterialSatisfaction;
		this.teachingmaterial = teachingmaterial;
		this.user = user;
		this.level = level;
		this.note = note;
	}
	public Integer getIdMaterialSatisfaction() {
		return idMaterialSatisfaction;
	}
	public void setIdMaterialSatisfaction(Integer idMaterialSatisfaction) {
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
