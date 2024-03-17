package com.creditcard.exceptions;

public class InvalidCustomerNameException extends Exception {

    public InvalidCustomerNameException(String name) {
        super(name);
    }
}
