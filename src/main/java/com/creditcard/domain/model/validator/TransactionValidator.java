package com.creditcard.domain.model.validator;

import com.creditcard.domain.model.Category;
import com.creditcard.domain.model.exception.InvalidCategoryException;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidTransactionIdException;

public class TransactionValidator {

    public static boolean isValidTransactionId(int transactionId) throws InvalidCustomerIdException, InvalidTransactionIdException {
        if (transactionId <= 0)
            return false;
        return true;
    }

    public static boolean isValidCategory(Category category) throws InvalidCategoryException {
        if (category == null)
            return false;
        return true;
    }
}
