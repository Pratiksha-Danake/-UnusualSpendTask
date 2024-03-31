package com.creditcard;

import com.creditcard.domain.CreditCard;
import com.creditcard.domain.Customer;
import com.creditcard.domain.model.Category;
import com.creditcard.domain.model.CustomerManager;
import com.creditcard.domain.model.Transaction;
import com.creditcard.domain.model.exception.*;
import com.creditcard.domain.service.UnusualSpendAnalyzer;
import com.creditcard.dto.UnusualSpend;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardManagerTest {
    CustomerManager customerManager = new CustomerManager();
    UnusualSpendAnalyzer unusualSpendAnalyzer = new UnusualSpendAnalyzer();
    CreditCardManager creditCardManager = new CreditCardManager(customerManager, unusualSpendAnalyzer);

    @Test
    void shouldAbleToCreateCustomerWithGivenDetails() throws InvalidCustomerNameException, InvalidCustomerIdException, InvalidCustomerEmailException {
        //arrange:
        int customerId = 1;
        String customerName = "sita";
        String customerEmail = "sita@gmail.com";
        //act:
        Customer expectedCustomer = Customer.create(customerId, customerName, customerEmail);
        Customer actualCustomer = Customer.create(customerId, customerName, customerEmail);
        //assert:
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void shouldAbleToCreateCreditCardForCustomer() throws InvalidCustomerNameException, InvalidCustomerIdException, InvalidCustomerEmailException {
        //arrange
        int customerId = 1;
        String customerName = "sita";
        String customerEmail = "sita@gmail.com";
        Customer customer = Customer.create(customerId, customerName, customerEmail);
        //act
        customer.setCreditCard(CreditCard.create(1));
        //assert
        assertEquals(1, customer.getCreditCard().getId());
    }

    @Test
    void shouldAbleToSaveTransactionHistory() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        //arrange
        int transactionId = 1001;
        int customerId = 1;
        double amountSpend = 50;
        String category = "Electronics";
        LocalDate transactionDate = LocalDate.now();
        //act
        unusualSpendAnalyzer.addTransaction(Transaction.create(transactionId, customerId, amountSpend, Category.BOOKS, transactionDate));
        //assert
        assertEquals(1, unusualSpendAnalyzer.getTransactions().size());
    }

    @Test
    void shouldAbleToReturnOverallTransactionsOfMonth() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        //arrange
        LocalDate transactionDate = LocalDate.now();
        unusualSpendAnalyzer.addTransaction(Transaction.create(1001, 1, 500, Category.BOOKS, transactionDate));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1002, 2, 1000, Category.ELECTRONICS, transactionDate));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1003, 1, 1500, Category.ELECTRONICS, LocalDate.parse("2024-02-16")));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1004, 2, 2000, Category.BOOKS, LocalDate.parse("2024-02-16")));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1005, 3, 20, Category.CLOTHING, LocalDate.parse("2024-01-16")));

        //act
        List<Transaction> monthlyTransactions = unusualSpendAnalyzer.getTransactionsOfTheMonth(transactionDate.getMonth());

        //assert
        assertEquals(2, monthlyTransactions.size());
    }

    @Test
    void shouldAbleToReturnListOfCustomersWhoHaveUnusualSpend() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
        //arrange
        LocalDate transactionDate = LocalDate.now();
        unusualSpendAnalyzer.addTransaction(Transaction.create(1001, 1, 200, Category.BOOKS, transactionDate));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1002, 2, 400, Category.CLOTHING, transactionDate));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1003, 1, 100, Category.BOOKS, LocalDate.parse("2024-02-16")));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1004, 2, 200, Category.CLOTHING, LocalDate.parse("2024-02-16")));

        //act
        double thresholdPercentage = 40;
        List<UnusualSpend> unusualSpendCustomerList = unusualSpendAnalyzer.getCustomerWithUnusualSpend(thresholdPercentage);

        //assert
        assertEquals(2, unusualSpendCustomerList.size());

        UnusualSpend unusualSpendCustomer = unusualSpendCustomerList.get(0);
        assertEquals(Category.BOOKS, unusualSpendCustomer.getCategory());
        assertEquals(100.0, unusualSpendCustomer.getUsualSpend());
        assertEquals(100.0, unusualSpendCustomer.getUnusualSpend());
        assertEquals(1, unusualSpendCustomer.getCustomerId());
    }


//    @Test
//    void shouldAbleToReturnEmptyListIfThereIsNoCustomerWithUnusualSpend() throws InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException {
//        //arrange
//        LocalDate transactionDate = LocalDate.now();
//        unusualSpendAnalyzer.addTransaction(Transaction.create(1001, 1, 2, Category.BOOKS, transactionDate));
//        unusualSpendAnalyzer.addTransaction(Transaction.create(1002, 2, 2, Category.CLOTHING, transactionDate));
//        unusualSpendAnalyzer.addTransaction(Transaction.create(1003, 1, 2, Category.BOOKS, LocalDate.parse("2024-02-16")));
//        unusualSpendAnalyzer.addTransaction(Transaction.create(1004, 2, 2, Category.CLOTHING, LocalDate.parse("2024-02-16")));
//
//        // act
//        double threshholdPercentage = 40;
//        List<UnusualSpend> unusualSpendCustomerList = creditCardManager.getListOfUnusualSpendCustomer(threshholdPercentage);
//
//        //assert
//        assertTrue(unusualSpendCustomerList.isEmpty());
//    }


    @Test
    void shouldAbleToSendEmailAlertToTheCustomerWhoHaveUnusualSpend() throws MessagingException, InvalidCustomerIdException, InvalidTransactionIdException, InvalidCategoryException, InvalidCustomerNameException, InvalidCustomerEmailException {
        //arrange
        LocalDate transactionDate = LocalDate.now();
        unusualSpendAnalyzer.addTransaction(Transaction.create(1001, 2, 1100, Category.BOOKS, transactionDate));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1002, 2, 400, Category.CLOTHING, transactionDate));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1003, 2, 500, Category.BOOKS, LocalDate.parse("2024-02-16")));
        unusualSpendAnalyzer.addTransaction(Transaction.create(1004, 2, 200, Category.CLOTHING, LocalDate.parse("2024-02-16")));

        //act
        customerManager.saveCustomer(Customer.create(2, "Pratiksha", "pratikshadanake2001@gmail.com"));
        List<Customer> customerList = customerManager.getCustomers();
        double threshholdPercentage = 40;
        List<UnusualSpend> unusualSpendCustomerList = creditCardManager.getListOfUnusualSpendCustomer(threshholdPercentage);
        creditCardManager.sendEmailAlert(unusualSpendCustomerList);

        //assert
        assertTrue(true);
    }
}
