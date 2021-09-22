package com.study.gamereviewrest.repository;

import com.study.gamereviewrest.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
//    @Query("{'gameName':?0}") // refer the first parameter of the argument if another then ?1, ?2 ... etc
    Game findByGameName(String gameName);

    void deleteByGameName(String gameName);
}
