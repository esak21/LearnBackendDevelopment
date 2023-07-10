package com.bookapp.example.booksproducer.service;

import com.bookapp.example.booksproducer.Producer.BooksAvroProducer;
import com.bookapp.example.booksproducer.avro.BooksAvro;
import com.bookapp.example.booksproducer.datagenerator.Book;
import com.bookapp.example.booksproducer.datagenerator.BookGenerator;
import com.bookapp.example.booksproducer.dto.bookEvents;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BookService {

    private BooksAvroProducer booksProducer;
    private BookGenerator bookGenerator;

    public BookService(BooksAvroProducer booksProducer, BookGenerator bookGenerator) {
        this.booksProducer = booksProducer;
        this.bookGenerator = bookGenerator;
    }


    public BooksAvro MapBooksAvro(Book book) {
        return BooksAvro.newBuilder()
                .setBookID(book.getBookId())
                .setTitle(book.getTitle())
                .setAuthors(book.getAuthors())
                .setIsbn(book.getIsbn())
                .setIsbn13(book.getIsbn13())
                .setAverageRating(book.getAverageRating())
                .setLanguageCode(book.getLanguageCode())
                .setNumPages(book.getNumPages())
                .setRatingsCount(book.getRatingsCount())
                .setTextReviewsCount(book.getTextReviewCount())
                .setPublicationDate(book.getPublicationDate())
                .setPublisher(book.getPublisher())
                .build();

    }
}
