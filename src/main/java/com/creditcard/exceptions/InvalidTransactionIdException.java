package com.creditcard.exceptions;

public class InvalidTransactionIdException extends InvalidTransactionException{
    public InvalidTransactionIdException(int id) {
        super(String.format("%s",id));
    }
}
