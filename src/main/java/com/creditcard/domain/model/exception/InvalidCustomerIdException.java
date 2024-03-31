package com.creditcard.domain.model.exception;

public class InvalidCustomerIdException extends InvalidCustomerException {
    public InvalidCustomerIdException(String msg) {
        super(String.format("%s",msg));
    }
}
