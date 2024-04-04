package com.serviceAppartment.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serviceAppartment.Payloads.ResponseHandler;
import com.serviceAppartment.exceptions.ApartmentNotAvailableException;
import com.serviceAppartment.exceptions.ApartmentNotFoundException;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ApartmentNotFoundException.class,ApartmentNotAvailableException.class})
	public ResponseEntity<?> UserExceptionHandler(Exception exception){
		exception.printStackTrace();
		return ResponseHandler.responseBuilder(exception.getMessage(), HttpStatus.NOT_FOUND, null);
	}
}

