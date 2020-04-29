package com.mindtree.restaurantapp.dto;

public class ErrorDto {

	private String errorMessage;

	private Throwable cause;

	public ErrorDto() {
	}

	public ErrorDto(String errorMessage, Throwable cause) {
		this.errorMessage = errorMessage;
		this.cause = cause;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
