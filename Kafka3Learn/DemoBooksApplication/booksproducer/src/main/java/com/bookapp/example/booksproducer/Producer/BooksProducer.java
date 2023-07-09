package com.bookapp.example.booksproducer.Producer;

import com.bookapp.example.booksproducer.datagenerator.Book;
import com.bookapp.example.booksproducer.datagenerator.BookGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class BooksProducer {
    @Value("${spring.kafka.topic}")
    public String topicName;

    private final KafkaTemplate<Integer, String> kafkatemplate;

    private final ObjectMapper objectMapper;

    private final BookGenerator bookgenerator;

    public BooksProducer(KafkaTemplate<Integer, String> kafkatemplate, ObjectMapper objectMapper, BookGenerator bookgenerator) {
        this.kafkatemplate = kafkatemplate;
        this.objectMapper = objectMapper;
        this.bookgenerator = bookgenerator;
    }

    private void handleSucesss(Integer key, String message, SendResult<Integer, String> sendResult) {
        log.info("Message Sent Sucessfully for the key - {} and Value: {} , Partition is :: {}", key, message, sendResult.getRecordMetadata().toString());
    }

    private void handleFailure(Integer key, String message, Throwable throwable) {
        log.error("Error Sending the Message and the Exception is -- {}",  throwable.getMessage(), throwable);
    }


    public CompletableFuture<SendResult<Integer, String>> sendBookEvents(Book bookEvent) throws JsonProcessingException {
        var key = bookEvent.getBookId();
        var value = objectMapper.writeValueAsString(bookEvent);

        var results = kafkatemplate.send(topicName, key, value);

        return results.whenComplete( (sendResult,throwable) -> {

            if(throwable != null ) {
                handleFailure(key, value, throwable);
            }
            else {
                handleSucesss(key, value, sendResult);
            }

        });

    }
}
