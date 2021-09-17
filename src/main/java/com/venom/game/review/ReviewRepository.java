package com.venom.game.review;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {

    List<Review> findAllByGameID(UUID gameID);
}
