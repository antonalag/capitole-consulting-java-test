package com.alvarezaguilera.javatest.errorhandling;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.alvarezaguilera.javatest.util.IBuilder;


public class RestApiErrorBuilder implements IBuilder<RestApiError> {
	
	private int code;
	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public RestApiErrorBuilder withCode(int code) {
		this.code = code;
		return this;
	}
	
	public RestApiErrorBuilder withStatus(HttpStatus status) {
		this.status = status;
		return this;
	}
	
	public RestApiErrorBuilder withMessage(String message) {
		this.message = message;
		return this;
	}
	
	public RestApiErrorBuilder withErrors(List<String> errors) {
		this.errors = errors;
		return this;
	}

	@Override
	public RestApiError build() {
		RestApiError restApiError = new RestApiError();
		restApiError.setCode(this.code);
		restApiError.setStatus(this.status);
		restApiError.setMessage(this.message);
		restApiError.setErrors(this.errors);
		return restApiError;
	}

}
