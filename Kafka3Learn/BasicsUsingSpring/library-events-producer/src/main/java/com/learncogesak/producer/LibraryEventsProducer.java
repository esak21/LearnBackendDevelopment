package com.learncogesak.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learncogesak.Domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class LibraryEventsProducer {

    @Value("${spring.kafka.topic}")
    public String topicName;

    private final KafkaTemplate<Integer, String>   kafkatemplate;

    private final ObjectMapper objectMapper;

//    Lets Use Constructor injection to generate the class
    public LibraryEventsProducer(KafkaTemplate<Integer, String> kafkatemplate, ObjectMapper objectMapper) {
        this.kafkatemplate = kafkatemplate;
        this.objectMapper = objectMapper;
    }



    public CompletableFuture<SendResult<Integer, String>> sendLibraryEvents(LibraryEvent libraryEvent) throws JsonProcessingException {

        var key = libraryEvent.libraryEventId();
        var message = objectMapper.writeValueAsString(libraryEvent);

        var results = kafkatemplate.send(topicName, key, message);

        return results.whenComplete( (sendResult, throwable) -> {
            if(throwable != null) {

                handleFailure(key, message, throwable);
            }else{

                handleSucesss(key, message, sendResult);
            }
        });
    }

    private void handleSucesss(Integer key, String message, SendResult<Integer, String> sendResult) {
        log.info("Message Sent Sucessfully for the key - {} and Value: {} , Partition is :: {}", key, message, sendResult.getRecordMetadata().toString());
    }

    private void handleFailure(Integer key, String message, Throwable throwable) {
        log.error("Error Sending the Message and the Exception is -- {}",  throwable.getMessage(), throwable);
    }


    public SendResult<Integer, String> sendLibraryEvents_V2(LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        var key = libraryEvent.libraryEventId();
        var message = objectMapper.writeValueAsString(libraryEvent);

//        1. Blocking Call - get metadata about the kafka Cluster
//        2. Blcoking call - wait until message is sent to kafak

        var results = kafkatemplate.send(topicName, key, message).get(2, TimeUnit.MINUTES);
//                .get();

        log.info("key :: {} -- value:: {} -- results:: {}", key, message, results);
        return results;
    }

    public CompletableFuture<SendResult<Integer, String>> sendLibraryEvents_v3(LibraryEvent libraryEvent) throws JsonProcessingException {

        var key = libraryEvent.libraryEventId();
        var message = objectMapper.writeValueAsString(libraryEvent);

//         Create Producer Record
        var appproducerrecord = buildProducerRecord(key,message);


        var results = kafkatemplate.send(appproducerrecord);

        return results.whenComplete( (sendResult, throwable) -> {
            if(throwable != null) {

                handleFailure(key, message, throwable);
            }else{

                handleSucesss(key, message, sendResult);
            }
        });
    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String message) {

        List<Header> headersData = List.of(new RecordHeader("Event-Source", "Scanner".getBytes()));
        return new ProducerRecord<>(topicName, null, key, message, headersData);
    }


}
