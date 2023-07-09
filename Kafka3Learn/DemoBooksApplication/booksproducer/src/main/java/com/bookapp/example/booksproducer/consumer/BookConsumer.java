package com.bookapp.example.booksproducer.consumer;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class BookConsumer {


    @KafkaListener(groupId = "bathirdparty", topics = {"books-events-1"})
    public void onMessage(ConsumerRecord<Integer, String> consumerrecord) throws JsonProcessingException {

        log.info("INFO :: Consumer Record :: {}", consumerrecord);

    }
}





