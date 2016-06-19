package com.citrix.training.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.citrix.training.service.impl.EntityNotFoundException;
import com.citrix.training.service.impl.FieldErrorException;

public class ApiBaseController {

	@ExceptionHandler({ DataAccessException.class })
	public ResponseEntity<ApiError> databaseError(DataAccessException dae) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST.value(), dae.getMostSpecificCause().getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<List<FieldError>> fieldError(ConstraintViolationException cve) {
		List<FieldError> messages = new ArrayList<FieldError>();
		for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
			messages.add(new FieldError(WordUtils.uncapitalize(constraintViolation.getRootBeanClass().getSimpleName()),
					constraintViolation.getPropertyPath().toString(), constraintViolation.getInvalidValue(), false,
					null, null, constraintViolation.getMessage()));
		}
		return new ResponseEntity<List<FieldError>>(messages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<ApiError> entityNotFound(EntityNotFoundException enfe) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.NOT_FOUND.value(), enfe.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ FieldErrorException.class })
	public ResponseEntity<ApiError> validateError(FieldErrorException fee) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST.value(), fee.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
