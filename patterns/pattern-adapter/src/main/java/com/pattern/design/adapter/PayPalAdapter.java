package com.pattern.design.adapter;

import org.springframework.stereotype.Component;

import com.pattern.design.processor.PaymentProcessor;

@Component
public class PayPalAdapter implements PaymentProcessor{

    @Override
    public void makePayment(double amount) {
       // PayPal-specific logic to process payment
        //actual api
        System.out.println("Payment processed via PayPal: " + amount);
    }

}
