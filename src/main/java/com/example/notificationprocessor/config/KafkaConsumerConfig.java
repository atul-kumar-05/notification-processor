package com.example.notificationprocessor.config;

import lombok.Builder;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public DefaultErrorHandler kafkaErrorHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (record, ex) ->
                    // Route to a dead-letter topic based on the exception type
                    new TopicPartition(record.topic()+"dlq", record.partition()
                    )
        );

        FixedBackOff fixedBackOff = new FixedBackOff(2000L, 3); // Retry every 1 second, up to 3 times
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, fixedBackOff);
        errorHandler.addNotRetryableExceptions(IllegalArgumentException.class); // Don't retry for IllegalArgumentException
        return errorHandler;
    }
}
