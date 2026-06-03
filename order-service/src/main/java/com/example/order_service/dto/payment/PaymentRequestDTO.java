package com.example.order_service.dto.payment;

import com.example.order_service.entity.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long orderId;

    private String customerEmail;

    private BigDecimal amount;
    private PaymentMethod method;

}
