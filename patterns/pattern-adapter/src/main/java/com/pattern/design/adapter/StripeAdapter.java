package com.pattern.design.adapter;

import org.springframework.stereotype.Component;

import com.pattern.design.processor.PaymentProcessor;

@Component
public class StripeAdapter implements PaymentProcessor {

    public void makePayment(double amount) {
        // Stripe-specific logic to process payment
        System.out.println("Payment processed via Stripe: " + amount);
    }
}