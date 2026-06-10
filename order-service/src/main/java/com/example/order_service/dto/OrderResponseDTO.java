package com.example.order_service.dto;

import com.example.order_service.entity.StatusOrder;
import com.example.order_service.entity.payment.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDTO(UUID id,
                               String name,
                               String customerEmail,
                               BigDecimal totalAmount,
                               PaymentMethod paymentMethod,
                               StatusOrder status,
                               LocalDateTime createdAt) {
}
