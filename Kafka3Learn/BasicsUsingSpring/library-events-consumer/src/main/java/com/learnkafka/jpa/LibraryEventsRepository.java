package com.learnkafka.jpa;

import com.learnkafka.Entity.LibraryEvent;
import com.learnkafka.Entity.LibraryEventType;
import org.springframework.data.repository.CrudRepository;

public interface LibraryEventsRepository extends CrudRepository<LibraryEvent, Integer> {
}
