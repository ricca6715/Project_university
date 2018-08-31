package it.unisalento.se.saw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="The sent element is not valid!")
public class ElementNotValidException extends Exception {

	public ElementNotValidException(String errormsg) {
		super(errormsg);
	}
	
	

}
