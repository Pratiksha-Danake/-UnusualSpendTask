package com.creditcard.validatorclasses;

import com.creditcard.exceptions.InvalidCategoryException;
import com.creditcard.exceptions.InvalidCustomerIdException;
import com.creditcard.exceptions.InvalidTransactionIdException;
import com.creditcard.model.Category;

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
