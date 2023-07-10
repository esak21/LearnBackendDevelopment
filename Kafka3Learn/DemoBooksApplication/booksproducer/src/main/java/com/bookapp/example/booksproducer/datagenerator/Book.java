package com.bookapp.example.booksproducer.datagenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bookID",
        "title",
        "authors",
        "average_rating",
        "isbn",
        "isbn13",
        "language_code",
        "num_pages",
        "ratings_count",
        "text_reviews_count",
        "publication_date",
        "publisher"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @JsonProperty("bookID")
    private int bookId;

    @JsonProperty("title")
    private String  title;

    @JsonProperty("authors")
    private String authors;

    @JsonProperty("average_rating")
    private Double averageRating;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("isbn13")
    private String isbn13;

    @JsonProperty("language_code")
    private String languageCode;

    @JsonProperty("num_pages")
    private int numPages;

    @JsonProperty("ratings_count")
    private Integer ratingsCount;

    @JsonProperty("text_reviews_count")
    private Integer textReviewCount;
    @JsonProperty("publication_date")
    private String publicationDate;

    @JsonProperty("publisher")
    private String  publisher;
}
