package com.bookapp.example.booksproducer.controller;

import com.bookapp.example.booksproducer.Producer.BooksAvroProducer;
import com.bookapp.example.booksproducer.Producer.BooksProducer;
import com.bookapp.example.booksproducer.datagenerator.Book;
import com.bookapp.example.booksproducer.datagenerator.BookGenerator;
import com.bookapp.example.booksproducer.dto.bookEvents;
import com.bookapp.example.booksproducer.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/v1/book-orders")
public class BookController {

    private BookGenerator bookGenerator;
    private BooksAvroProducer booksProducer;

    private BookService bookService;

    public BookController(BookGenerator bookGenerator, BooksAvroProducer booksProducer, BookService bookService) {
        this.bookGenerator = bookGenerator;
        this.booksProducer = booksProducer;
        this.bookService = bookService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<bookEvents> newOrder (@RequestBody bookEvents bookEventsmodel) {
        log.info("Received the request for an Order : {} ", bookEventsmodel);
        for(int i = 0; i <= 3000; i ++ ){
            Book book = bookGenerator.getNextBookInvoice();
            try {
                var bookInAvro = bookService.MapBooksAvro(book);
                booksProducer.sendBooksAvroProducer(bookInAvro);
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(bookEventsmodel);

    }
}
