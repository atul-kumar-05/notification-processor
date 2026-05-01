package com.example.notificationprocessor.kafka.producer;

import com.example.notificationprocessor.dto.ChannelEvent;
import com.example.notificationprocessor.service.TopicResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelEventProducer {

    private final KafkaTemplate<String, ChannelEvent> kafkaTemplate;

    public void publish(ChannelEvent event) {

        String topic = TopicResolver.resolve(event.channel());
        String key = event.tenantId() + ":" + event.userId();

        kafkaTemplate.send(topic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish channel event", ex);
                        throw new RuntimeException("Kafka publish failed");
                    }
                });
    }
}
