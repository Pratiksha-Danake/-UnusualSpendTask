package com.creditcard.test;

import com.creditcard.exceptions.InvalidCategoryException;
import com.creditcard.exceptions.InvalidCustomerIdException;
import com.creditcard.exceptions.InvalidTransactionIdException;
import com.creditcard.model.Category;
import com.creditcard.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {
    @Test
    void shouldAbleToCreatetransaction() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        int transactionId = 1001;
        int customerId = 1;
        double amountSpend = 50;
        LocalDate transactionDate = LocalDate.now();

        assertNotNull(Transaction.create(transactionId,customerId,amountSpend,Category.BOOKS,transactionDate));
    }

    @Test
    void shoulsAbleToThrowExceptionWhenTransactionIdIsZero(){
        Assertions.assertThrows(InvalidTransactionIdException.class,()->{
            Transaction.create(0,1,50,Category.ELECTRONICS,LocalDate.now());
        },"0");
    }

    @Test
    void shoulsAbleToThrowExceptionWhenTransactionIdIsNegative(){
        Assertions.assertThrows(InvalidTransactionIdException.class,()->{
            Transaction.create(-1001,1,50,Category.ELECTRONICS,LocalDate.now());
        },"-1001");
    }

    @Test
    void shoulsAbleToThrowExceptionWhenCustomerIdIsZero(){
        Assertions.assertThrows(InvalidCustomerIdException.class,()->{
            Transaction.create(1001,0,50,Category.BOOKS,LocalDate.now());
        },"0");
    }

    @Test
    void shoulsAbleToThrowExceptionWhenCustomerIdIsNegeative(){
        Assertions.assertThrows(InvalidCustomerIdException.class,()->{
            Transaction.create(1001,-1,50, Category.BOOKS,LocalDate.now());
        },"-1");
    }

    @Test
    void shoulsAbleToThrowExceptionWhenCategoryIsNull(){
        Assertions.assertThrows(InvalidCategoryException.class,()->{
            Transaction.create(1001,1,50,null,LocalDate.now());
        },"");
    }
}
