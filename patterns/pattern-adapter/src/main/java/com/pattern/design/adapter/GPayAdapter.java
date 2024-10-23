package com.pattern.design.adapter;

import org.springframework.stereotype.Component;

import com.pattern.design.processor.PaymentProcessor;


@Component
public class GPayAdapter implements PaymentProcessor{

    @Override
    public void makePayment(double amount) {
        //Gpay -specific logic to process payment
        System.out.println("Payment processed via Gpay: " + amount);
    }
    
}
