package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.StatusOrder;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


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
        return orderMapper.ToResponse(order);
    }

    public OrderResponseDTO IupdateOrderStatus(Long id, StatusOrder newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        order.setStatus(StatusOrder.PAID);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.ToResponse(order);
    }
}
