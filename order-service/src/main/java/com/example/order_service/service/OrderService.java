package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.StatusOrder;
import com.example.order_service.entity.payment.PaymentMethod;
import com.example.order_service.events.OrderCreatedEvent;
import com.example.order_service.events.OrderEventPublisher;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventPublisher orderEventPublisher;


    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = orderMapper.toEntity(request);
        order.setStatus(StatusOrder.PENDING);
        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                UUID.randomUUID().toString(),
                savedOrder.getId(),
                savedOrder.getCustomerEmail(),
                savedOrder.getTotalAmount(),
                request.method()
           );

        orderEventPublisher.publish(event);

        return orderMapper.toResponse(savedOrder);

    }

    public List<Order> IfindAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDTO IfindOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        return orderMapper.toResponse(order);
    }

    public OrderResponseDTO IupdateOrderStatus(UUID id, StatusOrder newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        order.setStatus(StatusOrder.PAID);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
}
