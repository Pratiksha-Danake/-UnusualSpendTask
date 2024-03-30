package com.creditcard.dto;

import com.creditcard.domain.model.Category;

public class UnusualSpend {
    private Category category;
    private double unusualSpend;
    private double usualSpend;
    private int customerId;

    public UnusualSpend(Category category, double unusualSpend, double usualSpend, int customerId) {
        this.category = category;
        this.unusualSpend = unusualSpend;
        this.usualSpend = usualSpend;
        this.customerId = customerId;
    }

    public double getUsualSpend() {
        return usualSpend;
    }

    public Category getCategory() {
        return category;
    }

    public double getUnusualSpend() {
        return unusualSpend;
    }

    public int getCustomerId() {
        return customerId;
    }
}
