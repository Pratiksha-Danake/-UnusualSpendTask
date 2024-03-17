package com.creditcard.test;

import com.creditcard.domain.CreditCard;
import com.creditcard.exceptions.*;
import com.creditcard.model.*;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardManagerTest {
    CustomerManager customerManager = new CustomerManager();
    CreditCardManager creditCardManager = new CreditCardManager(customerManager);
    @Test
    void shouldAbleToCreateCustomerWithGivenDetails() throws InvalidCustomerNameException, InvalidCustomerIdException, InvalidCustomerEmailException {
        int customerId = 1;
        String customerName = "sita";
        String customerEmail = "sita@gmail.com";

        Customer expectedCustomer = Customer.create(customerId,customerName,customerEmail);
        Customer actualCustomer = Customer.create(customerId,customerName,customerEmail);
        assertEquals(expectedCustomer,actualCustomer);
    }

    @Test
    void shouldAbleToCreateCreditCardForCustomer() throws InvalidCustomerNameException, InvalidCustomerIdException, InvalidCustomerEmailException {
        int customerId = 1;
        String customerName = "sita";
        String customerEmail = "sita@gmail.com";
        Customer customer = Customer.create(customerId,customerName,customerEmail);
        customer.setCreditCard(CreditCard.create(1));
        assertEquals(1,customer.getCreditCard().getId());
    }

    @Test
    void shouldAbleToSaveTransactionHistory() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        int transactionId = 1001;
        int customerId = 1;
        double amountSpend = 50;
        String category = "Electronics";
        LocalDate transactionDate = LocalDate.now();
        creditCardManager.addTransaction(Transaction.create(transactionId,customerId,amountSpend, Category.BOOKS,transactionDate));

        assertEquals(1,creditCardManager.getTransactions().size());
    }

    @Test
    void shouldAbleToReturnOverallTransactionsOfMonth() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        LocalDate transactionDate = LocalDate.now();
        creditCardManager.addTransaction(Transaction.create(1001,1,500, Category.BOOKS,transactionDate));
        creditCardManager.addTransaction(Transaction.create(1002,2,1000, Category.ELECTRONICS,transactionDate));
        creditCardManager.addTransaction(Transaction.create(1003,1,1500, Category.ELECTRONICS,LocalDate.parse("2024-02-16")));
        creditCardManager.addTransaction(Transaction.create(1004,2,2000, Category.BOOKS,LocalDate.parse("2024-02-16")));

        List<Transaction> monthlyTransactions = creditCardManager.getTransactionsOfTheMonth(transactionDate.getMonth());
        assertEquals(2,monthlyTransactions.size());
    }

    @Test
    void shouldAbleToCreateListOfCustomersWhoHaveUnusualSpend() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        LocalDate transactionDate = LocalDate.now();
        creditCardManager.addTransaction(Transaction.create(1001,1,1100, Category.BOOKS,transactionDate));
        creditCardManager.addTransaction(Transaction.create(1002,2,400, Category.CLOTHING,transactionDate));
        creditCardManager.addTransaction(Transaction.create(1003,1,500, Category.BOOKS,LocalDate.parse("2024-02-16")));
        creditCardManager.addTransaction(Transaction.create(1004,2,200, Category.CLOTHING,LocalDate.parse("2024-02-16")));

        List<UnusualSpend> unusualSpendCustomerList = creditCardManager.getListOfUnusualSpendCustomer();
        assertEquals(2,unusualSpendCustomerList.size());
    }

    @Test
    void shouldAbleToSendEmailAlertToTheCustomerWhoHaveUnusualSpend() throws MessagingException, InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException, InvalidCustomerNameException {
        LocalDate transactionDate = LocalDate.now();
        creditCardManager.addTransaction(Transaction.create(1001,2,1100, Category.BOOKS,transactionDate));
        creditCardManager.addTransaction(Transaction.create(1002,2,400, Category.CLOTHING,transactionDate));
        creditCardManager.addTransaction(Transaction.create(1003,2,500, Category.BOOKS,LocalDate.parse("2024-02-16")));
        creditCardManager.addTransaction(Transaction.create(1004,2,200, Category.CLOTHING,LocalDate.parse("2024-02-16")));

//        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        List<Customer> customerList = customerManager.getCustomers();
        List<UnusualSpend> unusualSpendCustomerList = creditCardManager.getListOfUnusualSpendCustomer();
        creditCardManager.sendEmailAlert(unusualSpendCustomerList,customerList);

        assertTrue(true);
    }
}
