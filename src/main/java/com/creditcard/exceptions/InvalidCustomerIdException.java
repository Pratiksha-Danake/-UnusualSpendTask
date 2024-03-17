package com.creditcard.exceptions;

public class InvalidCustomerIdException extends InvalidCustomerException {
    public InvalidCustomerIdException(int msg) {
        super(String.format("%s",msg));
    }
}
