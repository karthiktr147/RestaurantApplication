package com.mindtree.restaurantapp.controller.handler;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.restaurantapp.dto.ErrorDto;
import com.mindtree.restaurantapp.dto.ResponseBody;
import com.mindtree.restaurantapp.exception.RestaurantAppException;

@RestControllerAdvice
public class RestaurentappHandler {
	@Autowired
	MessageSource messageSource;

	@ExceptionHandler(RestaurantAppException.class)
	public ResponseEntity<?> errorHandler(Exception e) {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDto(e.getMessage(), e.getCause()), "Data Not Found or no such element", false),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		String errorMessage = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessage += error.getDefaultMessage() + ", ";
		}

		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDto(errorMessage, ex.getCause()), "Error in the input given according to validation", false),
				HttpStatus.EXPECTATION_FAILED);

	}
	@ExceptionHandler(JDBCException.class)
	public ResponseEntity<?> handleMethodErrorInDatabase(MethodArgumentNotValidException ex) {
		String errorMessage = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessage += error.getDefaultMessage() + ", ";
		}

		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDto(errorMessage, ex.getCause()), "Some Error related with database", false),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
