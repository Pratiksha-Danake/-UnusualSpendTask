package com.creditcard;

import com.creditcard.domain.model.CustomerManager;
import com.creditcard.domain.model.Transaction;
import com.creditcard.domain.service.AlertComposer;
import com.creditcard.domain.service.UnusualSpendAnalyzer;
import com.creditcard.dto.UnusualSpend;
import com.creditcard.dto.UnusualSpendCustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CreditCardManager {
    CustomerManager customerManager;
    UnusualSpendAnalyzer unusualSpendAnalyzer;

    public CreditCardManager(CustomerManager customerManager, UnusualSpendAnalyzer unusualSpendAnalyzer) {
        this.unusualSpendAnalyzer = unusualSpendAnalyzer;
        this.customerManager = customerManager;
    }

    public List<UnusualSpend> getListOfUnusualSpendCustomer(double thresholdPercentage) {
        return unusualSpendAnalyzer.getCustomerWithUnusualSpend(thresholdPercentage);
    }

    public void sendEmailAlert(List<UnusualSpend> unusualSpendCustomerList) {
        List<UnusualSpendCustomerDTO> unusualSpendCustomerDTOS = new ArrayList<>();

        unusualSpendCustomerList.forEach((unusualCustomer) -> {
            String customerName = customerManager.getCustomerById(unusualCustomer.getCustomerId()).getName();
            String customerEmail = customerManager.getCustomerById(unusualCustomer.getCustomerId()).getEmail();
            unusualSpendCustomerDTOS.add(new UnusualSpendCustomerDTO(customerName, customerEmail, unusualCustomer.getCustomerId()));
        });

        AlertComposer.generateEmail(unusualSpendCustomerDTOS, unusualSpendCustomerList);
    }
}

