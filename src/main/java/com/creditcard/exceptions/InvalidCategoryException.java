package com.creditcard.exceptions;

import com.creditcard.model.Category;

public class InvalidCategoryException extends InvalidTransactionException {
    public InvalidCategoryException(Category category) {
        super(String.valueOf(category));
    }
}
