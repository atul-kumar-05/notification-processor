package com.example.notificationprocessor.dto;

import com.example.notificationprocessor.utils.Channel;
import java.util.Map;

public record ChannelEvent(
        String notificationId,
        String tenantId,
        String userId,
        Channel channel,
        String templateId,
        Map<String, Object> data,
        long createdAt
) {}