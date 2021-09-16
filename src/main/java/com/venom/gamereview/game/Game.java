package com.venom.gamereview.game;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@Document
public class Game {

    @MongoId
    private UUID id;
    private String gameName;
    private String gameGenre;


}
