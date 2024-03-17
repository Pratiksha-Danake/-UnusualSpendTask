package com.creditcard.model;

public class UnusualSpend {
    Category category;
    double unusualSpend;

    public Category getCategory() {
        return category;
    }

    public double getUnusualSpend() {
        return unusualSpend;
    }

    public int getCustomerId() {
        return customerId;
    }

    int customerId;
    public UnusualSpend(Category category, double unusualSpend, int customerId) {
        this.category = category;
        this.unusualSpend = unusualSpend;
        this.customerId = customerId;
    }
}
