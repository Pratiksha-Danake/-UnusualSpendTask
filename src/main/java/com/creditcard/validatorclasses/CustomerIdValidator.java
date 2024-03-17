package com.creditcard.validatorclasses;

import com.creditcard.exceptions.InvalidCustomerIdException;

public class CustomerIdValidator {
    public static boolean isValidId(int customerId) throws InvalidCustomerIdException {
        if(customerId <= 0)
            throw new InvalidCustomerIdException(customerId);
        return true;
    }
}
