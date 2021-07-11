package com.training.ecommerce.util;

import com.training.ecommerce.exceptions.ErrorDto;
import com.training.ecommerce.exceptions.TransactionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ECommerceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransactionFailedException.class)
	protected ResponseEntity<ErrorDto> handleWhenTransactionFailed(RuntimeException e){
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.NOT_FOUND);
	}

	/*@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<FieldErrorDto> handleWhileValidating(ConstraintViolationException e){
		
		List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> error : e.getConstraintViolations()) {
	        errors.add(error.getMessage());
	    }
		return new ResponseEntity<>(FieldErrorDto
				.builder()
				.errorCode(500)
				.errorMessage("Field error")
				.errors(errors)
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(DuplicateUserException.class)
	protected ResponseEntity<ErrorDto> handleWhileDuplicateEmail(DuplicateUserException e){
		
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	

}
