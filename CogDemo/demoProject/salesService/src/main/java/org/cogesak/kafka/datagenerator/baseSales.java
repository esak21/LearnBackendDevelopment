package org.cogesak.kafka.datagenerator;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
        "id",
        "title",
        "Rating",
        "maincateg",
        "platform",
        "price1",
        "actprice1",
        "Offer %",
        "norating1",
        "noreviews1",
        "star_5f",
        "star_4f",
        "star_3f",
        "star_2f",
        "star_1f",
        "fulfilled1"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class baseSales {

    @JsonProperty("id")
    private Integer salesId;

    @JsonProperty("title")
    private String title;
    @JsonProperty("Rating")
    private Double rating;
    @JsonProperty("maincateg")
    private String category;
    @JsonProperty("platform")
    private String platform ;
    @JsonProperty("price1")
    private Integer price;
    @JsonProperty("actprice1")
    private Integer actualprice;
    @JsonProperty("Offer %")
    private String offer;
    @JsonProperty("norating1")
    private Integer norating;
    @JsonProperty("noreviews1")
    private Integer noreviews;
    @JsonProperty("star_5f")
    private Integer star5;
    @JsonProperty("star_4f")
    private Integer star4;
    @JsonProperty("star_3f")
    private Integer  star3;
    @JsonProperty("star_2f")
    private Integer star2;
    @JsonProperty("star_1f")
    private Integer star1;
    @JsonProperty("fulfilled1")
    private Integer fulfilled;
}
