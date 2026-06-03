package com.example.order_service.mapper;

import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDTO toResponse(Order order) {
        return new OrderResponseDTO(order.getId(),
                order.getName(),
                order.getCustomerEmail(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt());
    }
}
