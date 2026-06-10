package com.example.order_service.dto;


import com.example.order_service.entity.payment.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequestDTO(@NotBlank(message = "O Nome Obrigatório") String name,
                              @NotBlank(message = "O Email é Obrigatório") String customerEmail,
                              @NotNull(message = "Valor Total Obrigatório") BigDecimal totalAmount,
                              PaymentMethod method) {
}
