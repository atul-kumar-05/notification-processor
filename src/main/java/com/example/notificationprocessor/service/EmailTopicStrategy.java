package com.example.notificationprocessor.service;

public class EmailTopicStrategy implements TopicResolver {
    @Override
    public String resolve(String topic) {
        return "notification.email.v1";
    }
}
