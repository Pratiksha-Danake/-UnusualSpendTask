package com.creditcard.domain.service;

import com.creditcard.dto.UnusualSpendCustomerDTO;
import com.creditcard.domain.model.Category;
import com.creditcard.dto.UnusualSpend;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlertComposer {

    public static void generateEmail(List<UnusualSpendCustomerDTO> unusualSpendCustomerDTOS, List<UnusualSpend> unusualSpendCustomerList) {

        Map<Integer, List<UnusualSpend>> spendByCustomer = unusualSpendCustomerList.stream()
                .collect(Collectors.groupingBy(UnusualSpend::getCustomerId));

        spendByCustomer.forEach((customerId, spends) -> {
            String customerName = unusualSpendCustomerDTOS.stream()
                    .filter(dto -> dto.getCustomerId() == (customerId))
                    .map(UnusualSpendCustomerDTO::getCustomerName)
                    .findFirst()
                    .toString();

            String customerEmail = unusualSpendCustomerDTOS.stream()
                    .filter(dto -> dto.getCustomerId() == (customerId))
                    .map(UnusualSpendCustomerDTO::getCustomerEmail)
                    .findFirst()
                    .toString();


            double overallUnusualSpend = spends.stream().mapToDouble(UnusualSpend::getUnusualSpend).sum();
            double overallUsualSpend = spends.stream().mapToDouble(UnusualSpend::getUsualSpend).sum();

            Map<Category, Double> categoryWiseSpend = spends.stream()
                    .collect(Collectors.groupingBy(UnusualSpend::getCategory,
                            Collectors.summingDouble(UnusualSpend::getUnusualSpend)));

            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<Category, Double> entry : categoryWiseSpend.entrySet()) {
                stringBuilder.append("* You spent Rs." + entry.getValue() + " on " + entry.getKey() + "\n");
            }

            EmailService.sendEmail(customerName,customerEmail,overallUnusualSpend,overallUsualSpend, new String(stringBuilder));
        });
    }
}
