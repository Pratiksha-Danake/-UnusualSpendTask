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
        //Arrange && Act
        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        //Assert
        assertEquals(2,customerManager.getCustomers().size());
    }

    @Test
    void shouldAbleToReturnCustomerById() throws InvalidCustomerIdException, InvalidCustomerNameException {
        //Arrange
        customerManager.saveCustomer(Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com"));
        customerManager.saveCustomer(Customer.create(2,"Pratiksha","pratikshadanake2001@gmail.com"));
        Customer expectedCustomer = Customer.create(1,"Pratiksha Danake","danakepratiksha1020@gmail.com");
        //Act
        Customer actualCustomer = customerManager.getCustomerById(1);
        //Assert
        assertEquals(expectedCustomer,actualCustomer);
    }
}
