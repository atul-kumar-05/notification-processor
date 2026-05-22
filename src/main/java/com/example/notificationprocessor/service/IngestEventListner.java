package com.example.notificationprocessor.service;

import com.example.notificationprocessor.dto.IngestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngestEventListner {
    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "notifications")
    public void listen(IngestEvent message, Acknowledgment ack) {
        try{
            orchestratorService.processIngestEvent(message);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Failed to process message: {}", message, e);
            throw e;
        }
    }
}
