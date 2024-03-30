package com.creditcard.test;

import com.creditcard.domain.Customer;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    void shouldAbleToThrowExceptionWhenEmptyCustomerNameIsPassed() {
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "", "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldAbleToThrowExceptionWhenNullNameIsPassed() {
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, null, "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldAbleToThrowExceptionWhenCustomerIdPassedIsZero() {
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(0, "sita ram", "sita@gmail.com");
        }, "0");
    }

    @Test
    void shouldAbleToThrowExceptionWhenCustomerIdPassedIsNegative() {
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(-1, "sita ram", "sita@gmail.com");
        }, "-1");
    }
}
