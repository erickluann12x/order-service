package com.example.order_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(@NotBlank(message = "O Nome Obrigatório") String name,
                              @NotBlank(message = "O Email é Obrigatório") String email,
                              @NotNull(message = "Valor Total Obrigatório") Integer totalAmount) {
}
