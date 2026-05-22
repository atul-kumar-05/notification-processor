package com.example.notificationprocessor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IngestEventListnerTest {

    @Mock
    private OrchestratorService orchestratorService;

    @InjectMocks
    private IngestEventListner ingestEventListner;

    @Test
    @DisplayName("Should successfully listen and pass string message to orchestrator")
    void testListen() {
        // Arrange
        String testMessage = "test-notification-message";

        // Act
        ingestEventListner.listen(testMessage);

        // Assert
        verify(orchestratorService, times(1)).processIngestEvent(testMessage);
    }
}

