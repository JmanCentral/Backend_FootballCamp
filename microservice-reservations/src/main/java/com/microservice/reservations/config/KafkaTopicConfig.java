package com.microservice.reservations.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic reservasTopic() {
        return TopicBuilder.name("reservas-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}