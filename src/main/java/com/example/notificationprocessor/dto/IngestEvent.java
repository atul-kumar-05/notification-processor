package com.example.notificationprocessor.dto;

import com.example.notificationprocessor.utils.Channel;
import com.example.notificationprocessor.utils.Priority;

import java.util.List;
import java.util.Map;

public record IngestEvent(
    String eventId,
    String idempotencyKey,
    String templateId,
    String userId,
    Priority priority,
    List<Channel> channels,
    Map<String, Object> payload
){}
