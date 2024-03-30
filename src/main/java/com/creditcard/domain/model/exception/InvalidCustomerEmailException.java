package com.creditcard.domain.model.exception;

public class InvalidCustomerEmailException extends InvalidCustomerException {

    public InvalidCustomerEmailException(String email) {
        super(email);
    }
}
