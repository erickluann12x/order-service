package com.example.order_service.dto.payment;

import com.example.order_service.entity.payment.PaymentMethod;
import com.example.order_service.entity.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long id;

    private Long orderId;

    private String customerEmail;

    private BigDecimal amount;

    private PaymentMethod method;

    private PaymentStatus status;

    private LocalDateTime paymentDate;
}
