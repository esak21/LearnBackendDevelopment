package com.learncogesak.datagenrator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learncogesak.AppGenerator.AppBookGenerator;
import org.springframework.asm.TypeReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BooksConvertor {

    public static void main(String[] args) {

    BookGenerator bookobject = new BookGenerator();
    AppBookGenerator book = new AppBookGenerator();
    for (int i = 0; i< 3000; i++ ) {
        var result = book.getNextBookInvoice();
        System.out.println(result);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    }
}
