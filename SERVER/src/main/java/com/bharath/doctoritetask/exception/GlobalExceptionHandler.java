package com.bharath.doctoritetask.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGeneralException(Exception e) {

		ErrorInfo info = new ErrorInfo();
		// info.setErrorMessage(e.getMessage());
		info.setErrorMessage("Something went wrong in server side :reason " + e.getMessage());
		info.setOccuredAt(LocalDateTime.now());
		info.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> handleConVException(ConstraintViolationException e) {

		ErrorInfo info = new ErrorInfo();
		String message = e.getConstraintViolations().stream().map(error -> error.getMessage())
				.collect(Collectors.joining(", "));
		info.setErrorMessage(message);
		// info.setErrorMessage("Something went wrong in server side");
		info.setOccuredAt(LocalDateTime.now());
		info.setErrorCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.BAD_REQUEST);
	}

}
