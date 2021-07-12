package com.training.ecommerce.exceptions;

public class TransactionFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TransactionFailedException(String message) {
        super(message);
    }
}
