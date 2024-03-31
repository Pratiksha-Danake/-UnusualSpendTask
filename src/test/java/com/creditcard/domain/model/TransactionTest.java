package com.creditcard.domain.model;

import com.creditcard.domain.model.exception.InvalidCategoryException;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidTransactionIdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {
    @Test
    void shouldAbleToCreateTransactionWithGivenDetails() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        //arrange
        int transactionId = 1001;
        int customerId = 1;
        double amountSpend = 50;
        LocalDate transactionDate = LocalDate.now();
        //act && assert
        assertNotNull(Transaction.create(transactionId, customerId, amountSpend, Category.BOOKS, transactionDate));
    }

    @Test
    void shouldBeAbleToReturnFalseWhenTransactionIdIsZero() {
        // act && assert
        Assertions.assertThrows(InvalidTransactionIdException.class, () -> {
            Transaction.create(0, 1, 50, Category.ELECTRONICS, LocalDate.now());
        }, "0");
    }

    @Test
    void shouldThrowInvalidTransactionIdExceptionWhenTransactionIdIsNegative() {
        // act && assert
        Assertions.assertThrows(InvalidTransactionIdException.class, () -> {
            Transaction.create(-1001, 1, 50, Category.ELECTRONICS, LocalDate.now());
        }, "-1001");
    }

    @Test
    void shouldThrowInvalidCategoryExceptionWhenCategoryIsNull() {
        // act && assert
        Assertions.assertThrows(InvalidCategoryException.class, () -> {
            Transaction.create(1001, 1, 50, null, LocalDate.now());
        });
    }

    @Test
    void shouldThrowInvalidTransactionIdExceptionWhenTransactionIdIsInvalid() {
        // act && assert
        Assertions.assertThrows(InvalidTransactionIdException.class, () -> {
            Transaction.create(-1, 1, 100, Category.ELECTRONICS, LocalDate.now());
        });
    }

    @Test
    void shouldThrowInvalidCustomerIdExceptionWhenTransactionIdIsInvalid() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Transaction.create(1, -1, 100, Category.ELECTRONICS, LocalDate.now());
        });
    }
}
