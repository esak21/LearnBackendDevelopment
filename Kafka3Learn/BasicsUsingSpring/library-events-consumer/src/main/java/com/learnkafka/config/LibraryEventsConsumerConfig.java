package com.learnkafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.List;

@Configuration
@EnableKafka
@Slf4j
public class LibraryEventsConsumerConfig {

    public DefaultErrorHandler errorHandler () {
        var fixedbackoff = new FixedBackOff(1000L, 2);

        var errorHandler = new DefaultErrorHandler(fixedbackoff);

        var ExceptionToAvoid = List.of(
                IllegalArgumentException.class,
                IllegalMonitorStateException.class
        );
        ExceptionToAvoid.forEach(errorHandler::addNotRetryableExceptions);
                


        // Adding Code for the Retry Listner - What to do
        errorHandler.setRetryListeners((consumerRecord, e, i) -> {
            log.info("Failed Record in Retry Listener exception {} ", e.getMessage());
            log.info("Failed Number of  Retry Listener exception {} ", i);
        });

        return errorHandler;

    }

//     Override the Consumer configuration

    @Bean
    ConcurrentKafkaListenerContainerFactory<?,?> kafkaListenerContainerFactory (
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory
    ) {

        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);

//         It sets the concurrency to 3 threads
        factory.setConcurrency(3);
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

//         Adding Error Handler
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

}
