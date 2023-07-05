package com.learnkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.Entity.LibraryEvent;
import com.learnkafka.jpa.LibraryEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LibraryEventService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private LibraryEventsRepository libraryEventsRepository;
    public void ProcessLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
//         Read the LibraryEvent Value from the JSON
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        log.info("INFO --- Extracted Event is {}", libraryEvent );
//        Add the data to the H2

        switch (libraryEvent.getLibraryEventType()) {
            case NEW :
//                 Save the Event to Database
                saveEvent(libraryEvent);
                break;
            case UPDATE:
//                Validate the Library Event
                    validateEvent(libraryEvent);
//                 Save the Event
                saveEvent(libraryEvent);
                break;
            default:
                log.info("INFO :: Invalid libraryEvent Type");
        }



    }

    private void validateEvent(LibraryEvent libraryEvent) {
        if(libraryEvent.getLibraryEventId() == null) {
            throw new IllegalArgumentException("libraryEventId is Missing");
        }
        Optional<LibraryEvent> libraryEventOptional =  libraryEventsRepository.findById(libraryEvent.getLibraryEventId());
        if(! libraryEventOptional.isPresent()) {
            throw new IllegalArgumentException("Not a valid libraryeventId");

        }

        log.info("INFO :: Validation is Sucessful for library Event :: {} ", libraryEventOptional.get());
    }


    private void saveEvent(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventsRepository.save(libraryEvent);
        log.info("INFO :: Data stored in to Database sucessfully - {}", libraryEvent);
    }
}
