package com.creditcard.domain.model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionValidatorTest {
    @Test
    void shouldBeAbleToCreateInstanceOfTransactionValidator(){
        // arrange && act
        TransactionValidator transactionValidator = new TransactionValidator();
        // act
        Assertions.assertNotNull(transactionValidator);
    }
}