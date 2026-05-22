package com.example.notificationprocessor.service;

import com.example.notificationprocessor.utils.Channel;

public class TopicResolver {

    public static String resolve(Channel channel) {
        return switch (channel) {
            case EMAIL -> "notifications.email.v1";
            case SMS -> "notifications.sms.v1";
            case PUSH -> "notifications.push.v1";
            default -> throw new IllegalArgumentException("Unsupported channel");
        };
    }
}