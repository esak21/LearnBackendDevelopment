package com.bookapp.example.booksproducer.Producer;

import com.bookapp.example.booksproducer.avro.BooksAvro;
import com.bookapp.example.booksproducer.datagenerator.Book;
import com.bookapp.example.booksproducer.datagenerator.BookGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class BooksAvroProducer {
    @Value("${spring.kafka.topic}")
    public String topicName;

    private final KafkaTemplate<Integer, BooksAvro > kafkatemplate;

    private final ObjectMapper objectMapper;

    private final BookGenerator bookgenerator;

    public BooksAvroProducer(KafkaTemplate<Integer, BooksAvro > kafkatemplate, ObjectMapper objectMapper, BookGenerator bookgenerator) {
        this.kafkatemplate = kafkatemplate;
        this.objectMapper = objectMapper;
        this.bookgenerator = bookgenerator;
    }

    private void handleSucesss(BooksAvro booksAvro, SendResult<Integer, BooksAvro> sendResult) {
        log.info("Message Sent Sucessfully for the key - {} and Value: {} , Partition is :: {} - {} -- {}", booksAvro.getBookID(), booksAvro, sendResult.getRecordMetadata().partition(), sendResult.getRecordMetadata().offset(), sendResult.getRecordMetadata().timestamp());
    }

    private void handleFailure( BooksAvro booksAvro, Throwable throwable) {
        log.error("Error Sending the Message and the Exception is -- {}",  throwable.getMessage(), throwable);
    }

    public void sendBooksAvroProducer(BooksAvro booksAvro) {
        var booksAvroRecord = new ProducerRecord<>(topicName, booksAvro.getBookID(), booksAvro);

        var bookresults = kafkatemplate.send(booksAvroRecord);

        bookresults.whenComplete( (sendResult,throwable) -> {

            if(throwable != null ) {
                handleFailure(booksAvro, throwable);
            }
            else {
                handleSucesss(booksAvro, sendResult);
            }

        });

    }

//    public CompletableFuture<SendResult<Integer, String>> sendBookEvents(Book bookEvent) throws JsonProcessingException {
//        Integer key = bookEvent.getBookId();
//        var value = objectMapper.writeValueAsString(bookEvent);
//
//        var results = kafkatemplate.send(topicName, key, value);
//
//        return results.whenComplete( (sendResult,throwable) -> {
//
//            if(throwable != null ) {
//                handleFailure(key, value, throwable);
//            }
//            else {
//                handleSucesss(key, value, sendResult);
//            }
//
//        });
//
//    }
}
