package com.creditcard.domain.model.validator;

import com.creditcard.domain.model.exception.InvalidCustomerNameException;

public class CustomerNameValidator {
    public static boolean validateName(String customerName) throws InvalidCustomerNameException {
        if (customerName == null || customerName.isEmpty())
            throw new InvalidCustomerNameException(customerName);
        return true;
    }
}
