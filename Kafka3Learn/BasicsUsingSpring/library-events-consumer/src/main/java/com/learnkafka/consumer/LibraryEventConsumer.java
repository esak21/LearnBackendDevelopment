package com.learnkafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.service.LibraryEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventConsumer {

    @Autowired
    private LibraryEventService libraryEventService;

    @KafkaListener(groupId = "bathirdparty", topics = {"bathirdparty-library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerrecord) throws JsonProcessingException {

        log.info("INFO :: Consumer Record :: {}", consumerrecord);
        libraryEventService.ProcessLibraryEvent(consumerrecord);
    }



}
