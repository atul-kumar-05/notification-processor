package com.example.notificationprocessor.service;

public class PushTopicStrategy implements TopicResolver {
    @Override
    public String resolve(String topic) {
        return "notification.push.v1";
    }
}
