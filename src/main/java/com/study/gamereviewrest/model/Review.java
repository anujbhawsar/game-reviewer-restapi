package com.study.gamereviewrest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

// in new package inside model?
@Data
public class Review {
    private String reviewerName;
    private String text;
    private double rating;

//    public Review(String reviewerName, String text, double rating) {
//        this.reviewerName = reviewerName;
//        this.text = text;
//        this.rating = rating;
//    }
}
