package com.example.order_service.dto;


import jakarta.validation.constraints.NotBlank;

public record OrderRequestDTO(@NotBlank(message = "O Nome Obrigatório") String name,
                              @NotBlank(message = "O Email é Obrigatório") String email,
                              @NotBlank(message = "Valor Total Obrigatório") Integer totalAmount) {
}
