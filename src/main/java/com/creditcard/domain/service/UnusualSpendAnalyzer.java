package com.creditcard.domain.service;

import com.creditcard.domain.model.Transaction;
import com.creditcard.dto.UnusualSpend;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UnusualSpendAnalyzer {
    List<UnusualSpend> unusualSpendCustomerList = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<UnusualSpend> getCustomerWithUnusualSpend(double thresholdPercentage) {
        List<Transaction> currentMonthTransactionList = this.getTransactionsOfTheMonth(LocalDate.now().getMonth());
        List<Transaction> previousMonthTransactionList = this.getTransactionsOfTheMonth(LocalDate.now().getMonth().minus(1));
        double multiplier = 1 + (thresholdPercentage / 100);
        List<UnusualSpend> unusualSpendCustomerList = new ArrayList<>();

        for (Transaction current : currentMonthTransactionList) {
            for (Transaction previous : previousMonthTransactionList) {
                if (current.getCustomerId() == previous.getCustomerId() && current.getCategory().equals(previous.getCategory())) {
                    if (current.getAmountSpend() >= (multiplier * previous.getAmountSpend())) {
                        unusualSpendCustomerList.add(new UnusualSpend(current.getCategory(),
                                current.getAmountSpend() - previous.getAmountSpend(), previous.getAmountSpend(), current.getCustomerId()));
                    }
                }
            }
        }
        return unusualSpendCustomerList;
    }


    public List<Transaction> getTransactionsOfTheMonth(Month month) {
        List<Transaction> monthlyTransactions = transactions.stream()
                .filter(transaction -> transaction.getTransactionDate().getMonth().equals(month))
                .collect(Collectors.toList());
        return monthlyTransactions;
    }
}
