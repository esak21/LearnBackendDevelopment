package com.learncogesak.controller;

import com.learncogesak.Domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LibraryEventsController {

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryevent) {

        log.info("INFO :: we are in the event controller ");
        log.info("INFO :: library Events : {}", libraryevent);

         // invoke Kafka Producer

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryevent);
    }
}
