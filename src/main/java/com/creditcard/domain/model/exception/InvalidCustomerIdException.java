package com.creditcard.domain.model.exception;

public class InvalidCustomerIdException extends InvalidCustomerException {
    public InvalidCustomerIdException(int msg) {
        super(String.format("%s",msg));
    }
}
