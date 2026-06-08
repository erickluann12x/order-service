package com.example.order_service.dto;

import com.example.order_service.entity.StatusOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponseDTO(Long id,
                               String name,
                               String customerEmail,
                               BigDecimal totalAmount,
                               StatusOrder status,
                               LocalDateTime createdAt) {
}
