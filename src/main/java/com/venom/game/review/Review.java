package com.venom.game.review;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@Document
public class Review {
    @MongoId
    private UUID id;
    private UUID gameID;
    private String author;
    private String content;
}
