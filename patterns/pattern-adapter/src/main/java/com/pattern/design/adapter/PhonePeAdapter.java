package com.pattern.design.adapter;

import org.springframework.stereotype.Component;

import com.pattern.design.processor.PaymentProcessor;

@Component
public class PhonePeAdapter implements PaymentProcessor {

    @Override
    public void makePayment(double amount) {
        //call actual phonePe api
        System.out.println("Payment processed via PhonePe: " + amount);
    }
}