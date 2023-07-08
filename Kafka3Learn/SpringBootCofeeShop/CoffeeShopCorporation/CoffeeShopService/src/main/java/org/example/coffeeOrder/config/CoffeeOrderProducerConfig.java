package org.example.coffeeOrder.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CoffeeOrderProducerConfig {

    @Value("${spring.kafka.topic}")
    private String APPTOPICNAME;

    @Bean
    public NewTopic TopicCreation() {
        return TopicBuilder.name(APPTOPICNAME).partitions(1).replicas(1).build();
    }

}
