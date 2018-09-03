package it.unisalento.se.saw.models;

public class RegistrationValidateStrategy implements IValidateStrategy {

	@Override
	public boolean isValid(UserModel user) {
		
		boolean valid = getError(user).equals("");
		return valid;
	}

	@Override
	public String getError(UserModel user) {

		String error = "";
		if (user.getName().equals("")) {
			return "Name is not set";
		}
		if (user.getSurname().equals("")) {
			return "Surname is not set";			
		}
		if (user.getEmail().equals("")) {
			return "Email is not set";			
		}
		if (user.getPassword().equals("")) {
			return "Password is not set";			
		}
		return error;
	}

}
