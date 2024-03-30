package com.creditcard.domain.model;

import com.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.creditcard.domain.model.exception.InvalidCustomerNameException;
import com.creditcard.domain.Customer;
import com.creditcard.domain.model.CustomerManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerManagerTest {
    CustomerManager customerManager = new CustomerManager();
    @Test
    void shouldAbleToSaveCustomerToTheList() throws InvalidCustomerIdException, InvalidCustomerNameException {
        //arrange && act
        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        //assert
        assertEquals(2,customerManager.getCustomers().size());
    }

    @Test
    void shouldAbleToReturnCustomerById() throws InvalidCustomerIdException, InvalidCustomerNameException {
        //arrange
        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        Customer expectedCustomer = Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com");
        //act
        Customer actualCustomer = customerManager.getCustomerById(1);
        //assert
        assertEquals(expectedCustomer,actualCustomer);
    }
}
