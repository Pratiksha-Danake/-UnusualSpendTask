package com.creditcard.domain.model.validator;

import com.creditcard.domain.model.exception.InvalidCategoryException;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidTransactionIdException;
import com.creditcard.domain.model.Category;

public class TransactionValidator {

    public static boolean isValidTransactionId(int transactionId) throws InvalidCustomerIdException, InvalidTransactionIdException {
        if (transactionId <= 0)
            throw new InvalidTransactionIdException(transactionId);
        return true;
    }

    public static boolean isvalidCategory(Category category) throws InvalidCategoryException {
        if (category == null)
            throw new InvalidCategoryException(category);
        return true;
    }
}
