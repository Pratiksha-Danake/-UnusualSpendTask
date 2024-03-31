package com.creditcard.domain;

import com.creditcard.domain.model.exception.InvalidCustomerEmailException;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {
    @Test
    void shouldThrowInvalidCustomerNameExceptionWhenEmptyCustomerNameIsPassed() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "", "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldThrowInvalidCustomerNameExceptionWhenNullCustomerNameIsPassed() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, null, "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldThrowInvalidCustomerNameExceptionWhenCustomerNamePassedIsNotInCorrectFormat() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Ba          ", "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldThrowInvalidCustomerIdExceptionWhenCustomerIdPassedIsZero() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(0, "sita ram", "sita@gmail.com");
        }, "0");
    }

    @Test
    void shouldThrowInvalidCustomerIdExceptionWhenCustomerIdPassedIsNegative() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(-1, "sita ram", "sita@gmail.com");
        }, "-1");
    }

    @Test
    void shouldThrowInvalidCustomerEmailExceptionWhenCustomerEmailPassedIsInvalid() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            Customer.create(1, "sita ram", "invalid_email@example");
        });
    }

    @Test
    void shouldBeAbleToReturnSameHashcodeForEqualInstancesOfCustomerClass() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // arrange && act
        int firstCustomerHashCode = Customer.create(2, "sita kadam", "sai@gmail.com").hashCode();
        int secondCustomerHashCode = Customer.create(2, "sita kadam", "sai@gmail.com").hashCode();
        // assert
        assertTrue(firstCustomerHashCode == secondCustomerHashCode);
    }

    @Test
    public void shouldBeReturnTrueWhenComparisonIsBetweenSameInstance() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // arrange
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(2, "John Does", "john@example.com");

        // act && assert
        assertTrue(customer1.equals(customer1));
        assertFalse(customer1.equals(null));
        assertFalse(customer1.equals("Other"));
    }

    @Test
    public void shouldBeAbleToReturnFalseWhenCustomerIdForTwoInstanceIsDifferent() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(2, "John Does", "john@example.com");
        assertFalse(customer1.equals(customer2));
    }

    @Test
    public void shouldBeAbleToReturnFalseWhenCustomerNameForTwoInstanceIsDifferent() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(1, "John Doe", "john@example.com");
        assertFalse(customer1.equals(customer2));
    }

    @Test
    public void shouldBeAbleToReturnFalseWhenCustomerEmailForTwoInstanceIsDifferent() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(1, "John Does", "jane@example.com");
        assertFalse(customer1.equals(customer2));
    }
}
