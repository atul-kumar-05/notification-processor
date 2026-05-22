package com.example.notificationprocessor.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeadLetterProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(Object event) {
        kafkaTemplate.send("notifications.orchestrator.dlq", event);
    }
}