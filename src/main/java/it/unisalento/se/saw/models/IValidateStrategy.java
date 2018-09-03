package it.unisalento.se.saw.models;

public interface IValidateStrategy {
	
	boolean isValid(UserModel user);
	
	String getError(UserModel user);

}
