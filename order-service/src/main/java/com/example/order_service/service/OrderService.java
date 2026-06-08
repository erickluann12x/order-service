package com.example.order_service.service;

import com.example.order_service.client.PaymentClient;
import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.dto.payment.PaymentRequestDTO;
import com.example.order_service.dto.payment.PaymentResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.StatusOrder;
import com.example.order_service.entity.payment.PaymentMethod;
import com.example.order_service.entity.payment.PaymentStatus;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.exception.PaymentServiceUnavailableException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentClient paymentClient;


    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setName(orderRequestDTO.name());
        order.setCustomerEmail(orderRequestDTO.customerEmail());
        order.setTotalAmount(orderRequestDTO.totalAmount());
        ;
        order.setStatus(StatusOrder.PENDING);
        Order savedOrder = orderRepository.save(order);

        PaymentRequestDTO paymentRequestDTO =
                new PaymentRequestDTO(savedOrder.getId(),
                        savedOrder.getCustomerEmail(),
                        savedOrder.getTotalAmount(),
                        PaymentMethod.PIX);

        PaymentResponseDTO paymentResponseDTO;

        try {
            paymentResponseDTO=
            paymentClient.createPayment(paymentRequestDTO);

        }catch (ResourceAccessException e){
            throw new PaymentServiceUnavailableException("Payment Service está indisponivel");
        }

        if (paymentResponseDTO.getStatus() == PaymentStatus.APPROVED) {
            savedOrder.setStatus(StatusOrder.PAID);
        } else {
            savedOrder.setStatus(StatusOrder.CANCELED);
        }

        Order updatedOrder = orderRepository.save(savedOrder);
        return orderMapper.toResponse(updatedOrder);
    }

    public List<Order> IfindAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDTO IfindOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        return orderMapper.toResponse(order);
    }

    public OrderResponseDTO IupdateOrderStatus(Long id, StatusOrder newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com ID " + id + " não encontrado"));
        order.setStatus(StatusOrder.PAID);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
}
