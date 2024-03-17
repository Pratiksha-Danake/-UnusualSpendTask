package com.creditcard.validatorclasses;

import com.creditcard.exceptions.InvalidCustomerNameException;

public class CustomerNameValidator {
    public static boolean validateName(String customerName) throws InvalidCustomerNameException {
        if (customerName == null || customerName.isEmpty())
            throw new InvalidCustomerNameException(customerName);
        return true;
    }
}
