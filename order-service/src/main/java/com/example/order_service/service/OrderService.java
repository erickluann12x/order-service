package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.StatusOrder;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setName(orderRequestDTO.name());
        order.setEmail(orderRequestDTO.email());
        order.setTotalAmount(orderRequestDTO.totalAmount());
        order.setStatus(StatusOrder.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> IfindAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDTO IfindOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        return mapToResponse(order);
    }

    private OrderResponseDTO mapToResponse(Order order) {
        return new OrderResponseDTO(order.getId(),
                order.getName(),
                order.getEmail(),
                order.getTotalAmount()
                , order.getStatus()
                , order.getCreatedAt());
    }

    public OrderResponseDTO IupdateOrderStatus(Long id, StatusOrder newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        order.setStatus(StatusOrder.PAID);
        Order updatedOrder = orderRepository.save(order);
        return mapToResponse(order);
    }
}
