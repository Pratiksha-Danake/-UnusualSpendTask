package com.creditcard.validatorclasses;

import com.creditcard.exceptions.InvalidCustomerEmailException;

import java.util.regex.Pattern;

public class CustomerEmailIdValidator {
    public static boolean isValidEmail(String customerEmail) throws InvalidCustomerEmailException {
        if (!(Pattern.matches("^[^@]+@[^@.]+\\\\.[^@.]+$",customerEmail)))
            throw new InvalidCustomerEmailException(customerEmail);
        return true;
    }
}
