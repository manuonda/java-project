package com.pattern.design.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pattern.design.processor.PaymentProcessor;
import com.pattern.design.request.PaymentRequest;

@Service
public class PaymentService {

    // Paypal, PaypalService
    private Map<String, PaymentProcessor> paymentProcessorMap;

    public PaymentService(List<PaymentProcessor> paymentProcessors) {
        paymentProcessorMap = paymentProcessors
                .stream().collect(Collectors.toMap(
                        processor -> processor.getClass().getSimpleName(), Function.identity()));
    
    }

}
