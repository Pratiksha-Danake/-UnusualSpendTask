package com.creditcard.domain.service;

import com.creditcard.domain.model.Category;
import com.creditcard.domain.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class AlertComposerTest {
    @Test
    void shouldBeAbleToCreateInstanceOfAlertComposer() {
        // arrange && act
        AlertComposer alertComposer = new AlertComposer();
        // assert
        Assertions.assertNotNull(alertComposer);
    }
}