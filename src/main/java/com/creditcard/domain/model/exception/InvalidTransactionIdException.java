package com.creditcard.domain.model.exception;

public class InvalidTransactionIdException extends InvalidTransactionException{
    public InvalidTransactionIdException(int id) {
        super(String.format("%s",id));
    }
}
