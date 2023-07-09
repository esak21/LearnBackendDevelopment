package com.learncogesak.AppGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learncogesak.datagenrator.books;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class AppBookGenerator {
    private AppBookDetails[] booksColelction ;
    private final Random random;

    public AppBookGenerator()  {

        random = new Random();
        String FileLocation = "/home/esak/2K24/LearnBackendDevelopment/Kafka3Learn/BasicsUsingSpring/library-events-producer/src/main/java/com/learncogesak/AppGenerator/new.json";

        final ObjectMapper mapper;
        mapper = new ObjectMapper();

        try {
            booksColelction = mapper.readValue(new File(FileLocation), AppBookDetails[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    AppBookDetails getNextBook() {
        return booksColelction[getIndex()];
    }

    private int getIndex() {
        return random.nextInt(100);
    }

    public AppBookDetails getNextBookInvoice() {
        AppBookDetails bookinvoice = booksColelction[getIndex()];
        return bookinvoice;
    }

}
