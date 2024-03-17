package com.creditcard.test;

import com.creditcard.exceptions.InvalidCustomerIdException;
import com.creditcard.exceptions.InvalidCustomerNameException;
import com.creditcard.model.Customer;
import com.creditcard.model.CustomerManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerManagerTest {
    CustomerManager customerManager = new CustomerManager();
    @Test
    void shouldAbleToSaveCustomerToTheList() throws InvalidCustomerIdException, InvalidCustomerNameException {
        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        assertEquals(2,customerManager.getCustomers().size());
    }

    @Test
    void shouldAbleToReturnCustomerById() throws InvalidCustomerIdException, InvalidCustomerNameException {
        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        Customer expectedCusytomer = Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com");
        assertEquals(expectedCusytomer,customerManager.getCustomerById(1));
    }
}
