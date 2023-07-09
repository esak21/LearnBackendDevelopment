package com.learncogesak.datagenrator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class BookGenerator {

    private books[] mybookcollections;

    private final Random random;

    public BookGenerator() {
        random = new Random();
        final String DATAFILE = "/home/esak/2K24/LearnBackendDevelopment/Kafka3Learn/BasicsUsingSpring/library-events-producer/src/main/java/com/learncogesak/datagenrator/books.json";

        final ObjectMapper mapper;
        mapper = new ObjectMapper();

        try {
            mybookcollections = mapper.readValue(new File(DATAFILE), books[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    books getNexyBook() {
        return mybookcollections[getIndex()];
    }

    private int getIndex() {
        return random.nextInt(3);
    }

    public books getNextBookInvoice() {
        books bookinvoice = mybookcollections[getIndex()];
        return bookinvoice;
    }
}
