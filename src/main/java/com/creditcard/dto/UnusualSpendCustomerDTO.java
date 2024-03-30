package com.creditcard.dto;

public class UnusualSpendCustomerDTO {
    String customerName;
    String customerEmail;
    int customerId;

    public UnusualSpendCustomerDTO(String customerName, String customerEmail, int customerId) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getCustomerId() {
        return customerId;
    }
}
