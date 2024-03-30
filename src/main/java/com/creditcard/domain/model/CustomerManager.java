package com.creditcard.domain.model;

import com.creditcard.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    List<Customer> customers = new ArrayList<>();
    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomerById(int customerId) {
        Customer customerById = null;
        for (Customer customer : customers){
            if (customer.getId() == customerId)
                customerById = customer;
        }
        return customerById;
    }
}

