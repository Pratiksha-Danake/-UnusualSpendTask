package com.creditcard.domain.model.exception;

import com.creditcard.domain.model.Category;

public class InvalidCategoryException extends InvalidTransactionException {
    public InvalidCategoryException(Category category) {
        super(String.valueOf(category));
    }
}
