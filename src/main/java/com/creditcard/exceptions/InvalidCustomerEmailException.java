package com.creditcard.exceptions;

public class InvalidCustomerEmailException extends InvalidCustomerException {

    public InvalidCustomerEmailException(String email) {
        super(email);
    }
}
