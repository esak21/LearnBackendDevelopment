package com.learnkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class LibraryEventConsumerManualOffset implements AcknowledgingMessageListener<Integer, String> {

    @Override
    @KafkaListener(groupId = "bathirdparty", topics = {"bathirdparty-library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
//         Message Processing
        log.info("INFO :: Consumer Record :: {}", consumerRecord);
// Ack the Message

    acknowledgment.acknowledge();

    }


}
