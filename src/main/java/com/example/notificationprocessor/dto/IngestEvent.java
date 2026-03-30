package com.example.notificationprocessor.dto;

import com.example.notificationprocessor.utils.Channel;

import java.util.List;
import java.util.Map;

public record IngestEvent(String notificationId,
                          String tenantId,
                          String userId,
                          String templateId,
                          List<Channel> channels,
                          Map<String, Object> payload,
                          long createdAt) { }
