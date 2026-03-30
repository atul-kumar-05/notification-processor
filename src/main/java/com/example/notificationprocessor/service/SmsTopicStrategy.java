package com.example.notificationprocessor.service;

public class SmsTopicStrategy implements TopicResolver{
    @Override
    public String resolve(String topic) {
        return "notification.sms.v1";
    }
}
