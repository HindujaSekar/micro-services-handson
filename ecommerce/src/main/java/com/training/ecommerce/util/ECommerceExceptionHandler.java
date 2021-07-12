package com.training.ecommerce.util;

import com.training.ecommerce.exceptions.ErrorDto;
import com.training.ecommerce.exceptions.ProductExistsException;
import com.training.ecommerce.exceptions.ProductNotExistsException;
import com.training.ecommerce.exceptions.TransactionFailedException;
import com.training.ecommerce.exceptions.UnHandledCartException;

import feign.FeignException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ECommerceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransactionFailedException.class)
	protected ResponseEntity<ErrorDto> handleWhenTransactionFailed(RuntimeException e){
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UnHandledCartException.class)
	protected ResponseEntity<ErrorDto> handleWhenUnhandledCart(RuntimeException e){
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(ProductExistsException.class)
	protected ResponseEntity<ErrorDto> handleWhenProductExistsInCart(RuntimeException e){
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(ProductNotExistsException.class)
	protected ResponseEntity<ErrorDto> handleWhenProductNotExistsInCart(RuntimeException e){
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(java.lang.IndexOutOfBoundsException.class)
	protected ResponseEntity<ErrorDto> handleWhenNoProductsInCart(RuntimeException e){
		return new ResponseEntity<>(ErrorDto
				.builder()
				.errorCode(500)
				.errorMessage("Please add items in cart to make payment")
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FeignException.class)
	protected ResponseEntity<String> handleWhenErrorInFundTransfer(FeignException e){
		return new ResponseEntity<>(e.contentUTF8(), HttpStatus.INTERNAL_SERVER_ERROR);
	}	

}
