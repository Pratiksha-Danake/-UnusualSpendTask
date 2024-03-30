package com.creditcard.domain.model;

import com.creditcard.domain.model.validator.CustomerIdValidator;
import com.creditcard.domain.model.validator.TransactionValidator;
import com.creditcard.domain.model.exception.InvalidCategoryException;
import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidTransactionIdException;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int customerId;
    double amountSpend;
    Category category;
    LocalDate transactionDate;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    private Transaction(int transactionId, int customerId, double amountSpend, Category category, LocalDate transactionDate) {
        this.id = transactionId;
        this.customerId = customerId;
        this.amountSpend =  amountSpend;
        this.category = category;
        this.transactionDate = transactionDate;
    }

    public double getAmountSpend() {
        return amountSpend;
    }

    public static Transaction create(int transactionId, int customerId, double amountSpend, Category category, LocalDate transactionDate) throws InvalidTransactionIdException, InvalidCustomerIdException, InvalidCategoryException {
        Transaction transaction = null;
        if (TransactionValidator.isValidTransactionId(transactionId)
                && CustomerIdValidator.isValidId(customerId)
                && TransactionValidator.isvalidCategory(category))
            transaction = new Transaction(transactionId,customerId,amountSpend,category,transactionDate);
        return transaction;
    }
}
