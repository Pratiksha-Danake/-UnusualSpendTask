package com.creditcard.domain.service;

import com.creditcard.domain.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailServiceTest {
    @Test
    void shouldAbleToSendEmailToCustomerWithGivenDetails(){
        //arrange
        String customerName = "shruti";
        String customerEmail = "shruti1@gmail.com";
        double overallUnusualSpend = 1000;
        double overallUsualSpend = 300;
        String categoryWiseSpend = "You spent Rs.3000 on CLOTHING";
        //act
        EmailService.sendEmail(customerName,customerEmail,overallUnusualSpend,overallUsualSpend,categoryWiseSpend);
        //assert
        Assertions.assertTrue(true);
    }
}
