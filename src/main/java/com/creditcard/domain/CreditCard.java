package com.creditcard.domain;

public class CreditCard {
    private int id;

    public int getId() {
        return id;
    }

    public CreditCard(int id) {
        this.id = id;
    }

    public static CreditCard create(int id) {
        return new CreditCard(id);
    }
}