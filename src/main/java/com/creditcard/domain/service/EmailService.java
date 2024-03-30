package com.creditcard.domain.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    public static void sendEmail(String name, String email, double overallUnusualSpend, double overallUsualSpend, String categoryWiseSpend) {
        String fromEmail = "danakepratiksha1020@gmail.com";
        String toEmail = email;
        String password = "jdca jiof oufs exwz";

        String host = "127.0.0.1";

        String subject = "regarding unusual spend in this month..!";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(fromEmail, password);
            }
        });

            String body = "Unusual spending of Rs."+overallUnusualSpend+" detected!" +
                    "\n" +
                    "While your usual spend for last month was:" +overallUsualSpend +
                    "\n" +
                    "Hello "+name+"!\n" +
                    "\n" +
                    "We have detected unusually high spending on your card in these categories:\n" +
                    "\n" +
                    categoryWiseSpend+"\n" +
                    "\n" +
                    "Thanks,\n" + "\n" +
                    "The Credit Card Company\n";
            try {
                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);

            } catch (MessagingException e) {
                System.err.println("Error sending email: " + e.getMessage());
            }
    }
}
