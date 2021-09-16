package com.venom.gamereview.game;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameRepository extends MongoRepository<Game, UUID>{

}
