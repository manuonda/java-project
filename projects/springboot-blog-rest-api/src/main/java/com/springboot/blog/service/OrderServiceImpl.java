package com.springboot.blog.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.springboot.blog.dto.OrderRequest;
import com.springboot.blog.dto.OrderResponse;
import com.springboot.blog.entity.Order;
import com.springboot.blog.entity.Payment;
import com.springboot.blog.exception.PaymentException;
import com.springboot.blog.repository.OrderRepository;
import com.springboot.blog.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {
   
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository){
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }



    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }

}
