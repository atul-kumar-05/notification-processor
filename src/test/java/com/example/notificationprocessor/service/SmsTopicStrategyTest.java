package com.example.notificationprocessor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmsTopicStrategyTest {

    private final SmsTopicStrategy smsTopicStrategy = new SmsTopicStrategy();

    @Test
    @DisplayName("Should resolve correct topic for SMS")
    void testResolve() {
        String topic = smsTopicStrategy.resolve("some-input-topic");
        assertEquals("notification.sms.v1", topic);
    }
}

