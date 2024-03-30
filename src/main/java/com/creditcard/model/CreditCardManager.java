package com.creditcard.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        for (Transaction current : currentMonthTransactionList){
            for (Transaction previous : previousMonthTransactionList){
                if (current.getCategory().equals(previous.getCategory()) && current.getAmountSpend() >= (1.5*previous.getAmountSpend())){
                    unusualSpendCustomerList.add(new UnusualSpend(current.getCategory(),
                            current.getAmountSpend() - previous.getAmountSpend(), previous.getAmountSpend(), current.getCustomerId()));
                }
            }
        }
        return unusualSpendCustomerList;
    }

    public void sendEmailAlert(List<UnusualSpend> unusualSpendCustomerList) {
        Map<Integer, List<UnusualSpend>> spendByCustomer = unusualSpendCustomerList.stream()
                .collect(Collectors.groupingBy(UnusualSpend::getCustomerId));

        spendByCustomer.forEach((customerId, spends) -> {
            double overallUnusualSpend = spends.stream().mapToDouble(UnusualSpend::getUnusualSpend).sum();
            double overallUsualSpend = spends.stream().mapToDouble(UnusualSpend::getUsualSpend).sum();

            Map<Category, Double> categoryWiseSpend = spends.stream()
                    .collect(Collectors.groupingBy(UnusualSpend::getCategory,
                            Collectors.summingDouble(UnusualSpend::getUnusualSpend)));

            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<Category, Double> entry : categoryWiseSpend.entrySet()) {
                stringBuilder.append("* You spent Rs." + entry.getValue() + " on " + entry.getKey() + "\n");
            }

            String customerName = customerManager.getCustomerById(customerId).getName();
            String customerEmail = customerManager.getCustomerById(customerId).getEmail();

            EmailService.sendEmail(customerName,customerEmail,overallUnusualSpend,overallUsualSpend, new String(stringBuilder));
        });
    }
}
