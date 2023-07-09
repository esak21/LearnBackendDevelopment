package com.learncogesak.datagenrator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bookId",
        "bookName",
        "bookAuthorName"
})
public class books {
    @JsonProperty("bookId")
    private int bookId;
    @JsonProperty("bookName")
    private String bookName;
    @JsonProperty("bookAuthorName")
    private String bookAuthorName;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bookId", bookId)
                .append("bookName", bookName)
                .append("bookAuthorName", bookAuthorName)
                .toString();

    }
}
