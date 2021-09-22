package com.study.gamereviewrest.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document
public class Game {
    //use id or MongoId
//    @Autowired
//    Review review;
    @Id
    private String id;
//    @Field(name = "gameName")
    private String gameName;
//    @Field(name = "reviews")
    private List<Review> reviews;
//    @Field(name = "avgRating")
    private String avgRating;



//    public void setReviews(Review review){
//        reviews.add(review);
//    }

}
