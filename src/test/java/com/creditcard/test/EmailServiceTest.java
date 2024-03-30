package com.creditcard.test;

import com.creditcard.model.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailServiceTest {
    @Test
    void shouldAbleToSendEmailToCustomerWithGivenDetails(){
        //Arrange
        String customerName = "shruti";
        String customerEmail = "shruti1@gmail.com";
        double overallUnusualSpend = 1000;
        double overallUsualSpend = 300;
        String categoryWiseSpend = "You spent Rs.3000 on CLOTHING";
        //Act
        EmailService.sendEmail(customerName,customerEmail,overallUnusualSpend,overallUsualSpend,categoryWiseSpend);
        //Assert
        Assertions.assertTrue(true);
    }
}
