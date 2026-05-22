package com.example.notificationprocessor.kafka.consumer;

import com.example.notificationprocessor.dto.IngestEvent;
import com.example.notificationprocessor.service.OrchestratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngestionEventConsumerTest {

    @Mock
    private OrchestratorService orchestratorService;

    @Mock
    private Acknowledgment acknowledgment;

    @InjectMocks
    private IngestionEventConsumer ingestionEventConsumer;

    private IngestEvent ingestEvent;

    @BeforeEach
    void setUp() {
        ingestEvent = new IngestEvent("id1", "type1", "source1", "tenant1", java.util.Collections.emptyList(), java.util.Collections.emptyMap(), 123456789L);
    }

    @Test
    @DisplayName("Should successfully consume event and acknowledge")
    void testConsumeSuccess() {
        // Act
        ingestionEventConsumer.consume(ingestEvent, acknowledgment);

        // Assert
        verify(orchestratorService, times(1)).processIngestEvent(ingestEvent);
        verify(acknowledgment, times(1)).acknowledge();
    }

    @Test
    @DisplayName("Should handle exception during processing and NOT acknowledge")
    void testConsumeException() {
        // Arrange
        doThrow(new RuntimeException("Simulated processing error"))
                .when(orchestratorService).processIngestEvent(any(IngestEvent.class));

        // Act
        ingestionEventConsumer.consume(ingestEvent, acknowledgment);

        // Assert
        verify(orchestratorService, times(1)).processIngestEvent(ingestEvent);
        verify(acknowledgment, never()).acknowledge();
    }
}
