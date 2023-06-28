package com.learncogesak.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learncogesak.Domain.LibraryEvent;
import com.learncogesak.Domain.LibraryEventType;
import com.learncogesak.producer.LibraryEventsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
public class LibraryEventsController {

    private final LibraryEventsProducer libraryEventsProducer;

    public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
        this.libraryEventsProducer = libraryEventsProducer;
    }

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryevent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        log.info("INFO :: we are in the event controller ");
        log.info("INFO :: library Events : {}", libraryevent);

         // invoke Kafka Producer
        libraryEventsProducer.sendLibraryEvents_v3(libraryevent);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryevent);
    }

    @PutMapping("/v1/libraryevent")
    public ResponseEntity<?> updateLibraryEvent(@RequestBody  LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("INFO : we are going to update the library Event ");

        if (libraryEvent.libraryEventId() == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PASS the library Event ID ");
        }
        if(! libraryEvent.libraryEventType().equals(LibraryEventType.UPDATE)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only Update Event Type is Supported");
        }
        // Kafka Producer
        libraryEventsProducer.sendLibraryEvents(libraryEvent);

        log.info("INFO :: we have updated the event");
        return ResponseEntity.status(HttpStatus.OK).body(libraryEvent);
    }
}
