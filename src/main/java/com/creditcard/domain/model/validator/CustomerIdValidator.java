package com.creditcard.domain.model.validator;

import com.creditcard.domain.model.exception.InvalidCustomerIdException;

public class CustomerIdValidator {
    public static boolean isValidId(int customerId) throws InvalidCustomerIdException {
        if(customerId <= 0)
            throw new InvalidCustomerIdException(customerId);
        return true;
    }
}
