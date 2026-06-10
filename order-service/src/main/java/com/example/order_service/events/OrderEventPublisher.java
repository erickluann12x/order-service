package com.example.order_service.events;


import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final SnsTemplate snsTemplate;

    public void publish(OrderCreatedEvent event){

        log.info("Publicando evento: {}", event);

        snsTemplate.convertAndSend("order-created-topic",event);

        log.info("Evento enviado com succeso");
    }
}
