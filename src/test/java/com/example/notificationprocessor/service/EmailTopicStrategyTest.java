package com.example.notificationprocessor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTopicStrategyTest {

    private final EmailTopicStrategy emailTopicStrategy = new EmailTopicStrategy();

    @Test
    @DisplayName("Should resolve correct topic for email")
    void testResolve() {
        String topic = emailTopicStrategy.resolve("some-input-topic");
        assertEquals("notification.email.v1", topic);
    }
}

