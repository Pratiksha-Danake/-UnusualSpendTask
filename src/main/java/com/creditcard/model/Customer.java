package com.creditcard.model;
import com.creditcard.domain.CreditCard;
import com.creditcard.exceptions.InvalidCustomerIdException;
import com.creditcard.exceptions.InvalidCustomerNameException;
import com.creditcard.validatorclasses.CustomerIdValidator;
import com.creditcard.validatorclasses.CustomerNameValidator;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String email;
    private CreditCard creditCard;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    private Customer(int customerId, String customerName, String custommerEmail) {
        this.id = customerId;
        this.name = customerName;
        this.email = custommerEmail;
    }

    public static Customer create(int customerId, String customerName, String email) throws InvalidCustomerNameException, InvalidCustomerIdException {
        Customer customer = null;
        if (CustomerNameValidator.validateName(customerName) &&
                CustomerIdValidator.isValidId(customerId))
            customer = new Customer(customerId,customerName,email);
        return  customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }


}
