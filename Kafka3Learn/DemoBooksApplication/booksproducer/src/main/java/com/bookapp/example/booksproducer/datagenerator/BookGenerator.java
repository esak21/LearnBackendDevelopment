package com.bookapp.example.booksproducer.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;




public class BookGenerator {

    private Book[] books;

    private final Random random ;

    public BookGenerator() {
        random = new Random();

        String FileLocation = "/home/esak/2K24/LearnBackendDevelopment/Kafka3Learn/DemoBooksApplication/booksproducer/src/main/resources/Data/samplebooks.json";

        final ObjectMapper mapper;
        mapper = new ObjectMapper();
        try {
            var results  = mapper.readValue(new File(FileLocation), Book[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   private Book getNextBook() {
        return books[getIndex()];
   }

    private int getIndex() {
        return random.nextInt(3000);
    }

    public Book getNextBookInvoice() {
        Book bookobjet = books[getIndex()];
        return bookobjet;
    }

}
