package com.alvarezaguilera.javatest.errorhandling;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Simple structure for sending errors
 * 
 * @author anjoal
 *
 */
public class RestApiError {
	
	private int code;
	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
