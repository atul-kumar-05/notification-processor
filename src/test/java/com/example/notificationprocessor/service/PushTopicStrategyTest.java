package com.example.notificationprocessor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PushTopicStrategyTest {

    private final PushTopicStrategy pushTopicStrategy = new PushTopicStrategy();

    @Test
    @DisplayName("Should resolve correct topic for Push")
    void testResolve() {
        String topic = pushTopicStrategy.resolve("some-input-topic");
        assertEquals("notification.push.v1", topic);
    }
}

