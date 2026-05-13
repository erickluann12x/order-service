package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.StatusOrder;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public Order createOrder(OrderRequestDTO orderRequestDTO){
        Order order = new Order();
        order.setName(orderRequestDTO.name());
        order.setEmail(orderRequestDTO.email());
        order.setTotalAmount(orderRequestDTO.totalAmount());
        order.setStatus(StatusOrder.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> IfindAllOrders(){
        return orderRepository.findAll();
    }
}
