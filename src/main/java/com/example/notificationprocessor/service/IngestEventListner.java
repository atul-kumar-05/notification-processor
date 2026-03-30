package com.example.notificationprocessor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngestEventListner {
    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "notifications")
    public void listen(String message) {
        orchestratorService.processIngestEvent(message);
    }
}
