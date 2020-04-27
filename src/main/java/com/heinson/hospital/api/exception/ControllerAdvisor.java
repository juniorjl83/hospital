package com.heinson.hospital.api.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.heinson.hospital.api.dto.FailureDetailDto;
import com.heinson.hospital.api.dto.FailureDetailDto.SeverityEnum;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity ValidationException(Exception ex, WebRequest request) {
		FailureDetailDto failure = FailureDetailDto.builder()
				.timestamp(new Date())
				.status(HttpStatus.BAD_REQUEST.value())
				.exception(HttpStatus.BAD_REQUEST.name())
				.message(ex.getMessage())
				.severity(SeverityEnum.WARN)
				.build();
		return new ResponseEntity<FailureDetailDto>(failure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity DuplicateKeyException(Exception ex, WebRequest request) {
		FailureDetailDto failure = FailureDetailDto.builder()
				.timestamp(new Date())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.exception(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.message(ex.getCause().getCause().getMessage())
				.severity(SeverityEnum.ERROR)
				.build();
		return new ResponseEntity<FailureDetailDto>(failure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
