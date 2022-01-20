package com.alvarezaguilera.javatest.errorhandling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alvarezaguilera.javatest.exception.NotFoundException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method handles the exception that is thrown when the client sends a 
	 * request with an unsupported HTTP method
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info(ex.getClass().getName());
		
		StringBuilder errorBuilder = new StringBuilder();
		errorBuilder.append(ex.getMethod());
		errorBuilder.append(" method is not supported for this request. Supported methods are: ");
		String supportedMethods = ex.getSupportedHttpMethods() == null ? "Not defined" : 
			ex.getSupportedHttpMethods().stream().map(Enum::name).collect(Collectors.joining(" "));
		errorBuilder.append(supportedMethods);
		
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.BAD_REQUEST.value())
				.withStatus(HttpStatus.BAD_REQUEST)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(Collections.singletonList(errorBuilder.toString()))
				.build();
		
		return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
	}
	
	/**
	 * This method handles the exception that is thrown when an argument annotated
	 * with @Valid failed validation
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info(ex.getClass().getName());
		
		final List<String> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
		ex.getBindingResult().getGlobalErrors().forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));
		
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.BAD_REQUEST.value())
				.withStatus(HttpStatus.BAD_REQUEST)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(errors)
				.build();
		
		return handleExceptionInternal(ex, restApiError, headers, restApiError.getStatus(), request);		
	}
	
	/**
	 * This method handles the exception that is thrown when trying to set a
	 * property with the wrong type
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.info(ex.getClass().getName());
		
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();
		
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.BAD_REQUEST.value())
				.withStatus(HttpStatus.BAD_REQUEST)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(Collections.singletonList(error))
				.build();
		
		return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
	}
	
	/**
	 * This method handles the exception when a group of people not found
	 * 
	 * @param ex the exception
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Object> handleNotFound(final NotFoundException ex) {
		logger.info(ex.getClass().getName());
		
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.NOT_FOUND.value())
				.withStatus(HttpStatus.NOT_FOUND)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(Collections.singletonList(ex.getMessage()))
				.build();
		
		return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
	}
	
	/**
	 * This method handles the exception that is thrown when an argument is not the
	 * expected type
	 * 
	 * @param ex      the exception
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {
		logger.info(ex.getClass().getName());

		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.BAD_REQUEST.value())
				.withStatus(HttpStatus.BAD_REQUEST)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(Collections.singletonList(error))
				.build();
		
		return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
	}
	
	/**
	 * This method handles the exception that is thrown to report the result of
	 * constraint violations
	 * 
	 * @param ex      the exception
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		logger.info(ex.getClass().getName());

		final List<String> errors = new ArrayList<>();

		ex.getConstraintViolations().stream().forEach(violation -> {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		});
		
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.BAD_REQUEST.value())
				.withStatus(HttpStatus.BAD_REQUEST)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(errors)
				.build();
		
		return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
		
	}

	/**
	 * This method handles the exception that is thrown when a request param is missed
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info(ex.getClass().getName());
				
		final RestApiError restApiError = new RestApiErrorBuilder()
				.withCode(HttpStatus.BAD_REQUEST.value())
				.withStatus(HttpStatus.BAD_REQUEST)
				.withMessage(ex.getLocalizedMessage())
				.withErrors(Collections.singletonList(ex.getMessage()))
				.build();
		
		return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
	}
	
}
