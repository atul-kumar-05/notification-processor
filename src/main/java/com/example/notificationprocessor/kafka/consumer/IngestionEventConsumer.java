package com.example.notificationprocessor.kafka.consumer;

import com.example.notificationprocessor.dto.IngestEvent;
import com.example.notificationprocessor.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngestionEventConsumer {

    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "ingestion-events", groupId = "notification-processor-group")
    public void consume(IngestEvent ingestEvent, Acknowledgment acknowledgment) {
        System.out.println("Consumed message: " + ingestEvent);
        try{
            orchestratorService.processIngestEvent(ingestEvent);
            acknowledgment.acknowledge(); // Acknowledge the message after successful processing
        } catch (Exception e) {
            log.error("Error processing message: {}", ingestEvent, e);
            // Optionally, you can choose to not acknowledge the message to allow for retries
        }

    }
}
