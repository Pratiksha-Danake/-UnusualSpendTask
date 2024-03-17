package com.creditcard.exceptions;

import com.creditcard.model.Category;

public class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String msg) {
        super(msg);
    }
}
