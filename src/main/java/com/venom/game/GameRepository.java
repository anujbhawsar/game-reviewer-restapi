package com.venom.game;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameRepository extends MongoRepository<Game, UUID>{

}
