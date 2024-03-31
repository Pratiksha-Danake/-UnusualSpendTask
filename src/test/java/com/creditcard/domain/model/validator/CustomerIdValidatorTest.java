package com.creditcard.domain.model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerIdValidatorTest {
    @Test
    void shouldAbleToCreateInstanceOfCustomerIdValidator() {
        // arrange && act
        CustomerIdValidator customerIdValidator = new CustomerIdValidator();
        // assert
        Assertions.assertNotNull(customerIdValidator);
    }
}