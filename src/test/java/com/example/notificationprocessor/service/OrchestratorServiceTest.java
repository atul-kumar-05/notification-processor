package com.example.notificationprocessor.service;

import com.example.notificationprocessor.dto.IngestEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OrchestratorServiceTest {

    private OrchestratorService orchestratorService;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        orchestratorService = new OrchestratorService();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Should successfully process IngestEvent object")
    void testProcessIngestEvent() {
        // Arrange
        IngestEvent ingestEvent = new IngestEvent("id1", "type1", "source1", "tenant1", java.util.Collections.emptyList(), java.util.Collections.emptyMap(), 123456789L);

        // Act
        orchestratorService.processIngestEvent(ingestEvent);

        // Assert
        assertTrue(outputStreamCaptor.toString().trim().contains("Processing ingest event:"));
    }

    @Test
    @DisplayName("Should successfully process String message")
    void testProcessIngestEventString() {
        // Arrange
        String message = "sample-message";

        // Act
        orchestratorService.processIngestEvent(message);

        // Assert
        assertTrue(outputStreamCaptor.toString().trim().contains("Processing ingest event string: sample-message"));
    }

}
