package com.creditcard.domain.model;

import com.creditcard.domain.model.exception.InvalidCategoryException;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidTransactionIdException;
import com.creditcard.domain.model.validator.CustomerIdValidator;
import com.creditcard.domain.model.validator.TransactionValidator;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int customerId;
    private double amountSpend;
    private Category category;
    private LocalDate transactionDate;

    private Transaction(int transactionId, int customerId, double amountSpend, Category category, LocalDate transactionDate) {
        this.id = transactionId;
        this.customerId = customerId;
        this.amountSpend = amountSpend;
        this.category = category;
        this.transactionDate = transactionDate;
    }

    public static Transaction create(int transactionId, int customerId, double amountSpend, Category category, LocalDate transactionDate) throws InvalidTransactionIdException, InvalidCustomerIdException, InvalidCategoryException {
        Transaction transaction = null;
        if (!TransactionValidator.isValidTransactionId(transactionId))
            throw new InvalidTransactionIdException(transactionId + " is not valid..!");
        else if (!CustomerIdValidator.isValidId(customerId))
            throw new InvalidCustomerIdException(customerId + " is not valid..!");
        else if (!TransactionValidator.isValidCategory(category))
            throw new InvalidCategoryException(category + " is not valid..!");
        else
            transaction = new Transaction(transactionId, customerId, amountSpend, category, transactionDate);
        return transaction;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmountSpend() {
        return amountSpend;
    }
}
