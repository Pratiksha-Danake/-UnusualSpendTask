package com.creditcard;

import com.creditcard.domain.model.CustomerManager;
import com.creditcard.domain.model.Transaction;
import com.creditcard.dto.UnusualSpendCustomerDTO;
import com.creditcard.dto.UnusualSpend;
import com.creditcard.domain.service.AlertComposer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditCardManager {

    List<Transaction> transactions = new ArrayList<>();
    List<UnusualSpend> unusualSpendCustomerList = new ArrayList<>();
    CustomerManager customerManager;

    public CreditCardManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionsOfTheMonth(Month month) {
        List<Transaction> monthlyTransactions = transactions.stream()
                .filter(transaction -> transaction.getTransactionDate().getMonth().equals(month))
                .collect(Collectors.toList());
        return monthlyTransactions;
    }

    public List<UnusualSpend> getListOfUnusualSpendCustomer(double thresholdPercentage) {
        List<Transaction> currentMonthTransactionList = this.getTransactionsOfTheMonth(LocalDate.now().getMonth());
        List<Transaction> previousMonthTransactionList = this.getTransactionsOfTheMonth(LocalDate.now().getMonth().minus(1));
        double multiplier = 1 + (thresholdPercentage / 100);
        for (Transaction current : currentMonthTransactionList) {
            for (Transaction previous : previousMonthTransactionList) {
                if (current.getCategory().equals(previous.getCategory()) && current.getAmountSpend() >= (1.5 * previous.getAmountSpend())) {
                    unusualSpendCustomerList.add(new UnusualSpend(current.getCategory(),
                            current.getAmountSpend() - previous.getAmountSpend(), previous.getAmountSpend(), current.getCustomerId()));
                }
            }
        }
        return unusualSpendCustomerList;
    }

    public void sendEmailAlert(List<UnusualSpend> unusualSpendCustomerList) {
        List<UnusualSpendCustomerDTO> unusualSpendCustomerDTOS = new ArrayList<>();

        unusualSpendCustomerList.forEach((unusaulCustomer) -> {
            String customerName = customerManager.getCustomerById(unusaulCustomer.getCustomerId()).getName();
            String customerEmail = customerManager.getCustomerById(unusaulCustomer.getCustomerId()).getEmail();
            unusualSpendCustomerDTOS.add(new UnusualSpendCustomerDTO(customerName, customerEmail, unusaulCustomer.getCustomerId()));
        });

        AlertComposer.generateEmail(unusualSpendCustomerDTOS, unusualSpendCustomerList);
    }
}

