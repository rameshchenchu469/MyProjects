package com.hunger.saviour.portal.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunger.saviour.portal.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceKafkaPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public void publishOrderDetailsToOrdersTopic(OrderDTO orderDTO) {
        log.info("Publishing the payment & restaurant details {} details to orders topic", orderDTO);
        try {
            this.kafkaTemplate.send("orders", objectMapper.writeValueAsString(orderDTO));
        } catch (Exception e) {
            log.error("Error while publishing the payment & restaurant details {} details to orders topic", orderDTO);
        }
    }
}
