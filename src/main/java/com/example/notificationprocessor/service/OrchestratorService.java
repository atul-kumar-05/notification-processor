package com.example.notificationprocessor.service;

import com.example.notificationprocessor.dto.ChannelEvent;
import com.example.notificationprocessor.dto.IngestEvent;
import com.example.notificationprocessor.kafka.producer.ChannelEventProducer;
import com.example.notificationprocessor.utils.Channel;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchestratorService {

    private final ChannelEventProducer channelEventProducer;

    public void processIngestEvent(IngestEvent event) {

        validate(event);

        for(Channel channel : event.channels()){
            ChannelEvent channelEvent = new ChannelEvent(
                    event.notificationId(),
                    event.tenantId(),
                    event.userId(),
                    channel,
                    event.templateId(),
                    event.payload(),
                    event.createdAt()
            );
            channelEventProducer.publish(channelEvent);
        }
    }

    private void validate(IngestEvent ingestEvent) {
        if(ingestEvent.channels() == null || ingestEvent.channels().isEmpty()) {
            throw new IllegalArgumentException("At least one channel must be specified");
        }

        if(ingestEvent.notificationId() == null || ingestEvent.notificationId().isEmpty()) {
            throw new IllegalArgumentException("Notification ID is required");
        }
    }

    public void processIngestEvent(String message) {
        System.out.println("Processing ingest event string: " + message);
    }
}
