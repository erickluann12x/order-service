package com.example.order_service.events;

import com.example.order_service.entity.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreatedEvent {
    private String correlationId;
    private UUID orderId;
    private String customerEmail;
    private BigDecimal totalAmount;
    private PaymentMethod method;
}
