package com.creditcard.domain.model.exception;

public class InvalidCustomerNameException extends Exception {

    public InvalidCustomerNameException(String name) {
        super(name);
    }
}
