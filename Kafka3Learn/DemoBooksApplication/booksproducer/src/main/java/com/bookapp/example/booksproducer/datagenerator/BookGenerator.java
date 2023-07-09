package com.bookapp.example.booksproducer.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Random;



@Component
public class BookGenerator {

    private Book[] books;

    public BookGenerator(Book[] books) {
        this.books = books;
    }

    public void MyBookGenerator() {

        String FileLocation = "src/main/resources/Data/samplebooks.json";

        final ObjectMapper mapper;
        mapper = new ObjectMapper();
        try {
             books  = mapper.readValue(new File(FileLocation), Book[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   private Book getNextBook() {
        return books[getIndex()];
   }

    private int getIndex() {
        Random random = new Random();
        return random.nextInt(3000);
    }

    public Book getNextBookInvoice() {
        MyBookGenerator();
        Book bookobjet = books[getIndex()];
        return bookobjet;
    }

}
