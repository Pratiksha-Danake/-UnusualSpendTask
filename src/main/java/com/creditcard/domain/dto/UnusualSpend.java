package com.creditcard.domain.dto;

import com.creditcard.domain.model.Category;

public class UnusualSpend {
    Category category;
    double unusualSpend;
    double usualSpend;
    int customerId;

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


    public UnusualSpend(Category category, double unusualSpend,double usualSpend, int customerId) {
        this.category = category;
        this.unusualSpend = unusualSpend;
        this.usualSpend = usualSpend;
        this.customerId = customerId;
    }
}
