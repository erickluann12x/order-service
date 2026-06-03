package com.example.order_service.client;

import com.example.order_service.dto.payment.PaymentRequestDTO;
import com.example.order_service.dto.payment.PaymentResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentClient {
    private final RestClient restClient;

    public PaymentClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081").build();
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO){
        return restClient.post()
                .uri("/payments")
                .body(requestDTO)
                .retrieve()
                .body(PaymentResponseDTO.class);
    }
}
