package com.serviceAppartment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApartmentNotFoundException(String message) {
		super(message);
	}
}
