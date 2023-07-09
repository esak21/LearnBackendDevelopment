package com.bookapp.example.booksproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class AutoCreateConfig {

    @Value("${spring.kafka.topic}")
    public String AppTopicName;

    @Bean
    public NewTopic bookEvent() {
        return TopicBuilder.
                name(AppTopicName).
                partitions(1).
                replicas(1).
                build();
    }
}
