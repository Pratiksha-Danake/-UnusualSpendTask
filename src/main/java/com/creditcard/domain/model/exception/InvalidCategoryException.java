package com.creditcard.domain.model.exception;

public class InvalidCategoryException extends InvalidTransactionException {
    public InvalidCategoryException(String category) {
        super(String.valueOf(category));
    }
}
