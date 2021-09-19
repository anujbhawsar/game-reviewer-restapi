package com.venom.game.review;

import com.venom.game.exceptions.GameNotFoundException;
import com.venom.game.exceptions.ReviewNotFoundException;
import com.venom.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1.0/games/{gameID}")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/reviews")
    public List<Review> getAllGameReview(@PathVariable(value = "gameID")UUID gameID) throws GameNotFoundException{
       gameRepository.findById(gameID).orElseThrow(
                ()->new GameNotFoundException());
        return reviewRepository.findAllByGameID(gameID);
    }

    @PostMapping("/reviews")
    public ResponseEntity addReview(@PathVariable(value = "gameID")UUID gameID,@RequestBody Review review)throws GameNotFoundException{
        gameRepository.findById(gameID).orElseThrow(
                ()->new GameNotFoundException());
        review.setGameID(gameID);
        review.setId(java.util.UUID.randomUUID());
        reviewRepository.save(review);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/reviews/{reviewID}")
    public ResponseEntity updateReview(@PathVariable(value = "gameID")UUID gameID,
                                       @PathVariable(value = "reviewID") UUID reviewID, @RequestBody Review review)
            throws ReviewNotFoundException, GameNotFoundException {
        gameRepository.findById(gameID).orElseThrow(
                ()->new GameNotFoundException());
        Review updatedReview = reviewRepository.findById(reviewID).orElseThrow(
                ()->new ReviewNotFoundException());
        updatedReview.setGameID(review.getGameID());
        updatedReview.setAuthor(review.getAuthor());
        updatedReview.setContent(review.getContent());
        reviewRepository.save(updatedReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/reviews/{reviewID}")
    public ResponseEntity deleteReview(@PathVariable(value = "gameID")UUID gameID,@PathVariable(value = "reviewID") UUID reviewID)
            throws ReviewNotFoundException, GameNotFoundException{
        gameRepository.findById(gameID).orElseThrow(
                ()->new GameNotFoundException());
        Review rmvReview = reviewRepository.findById(reviewID).orElseThrow(
                ()->new ReviewNotFoundException());
        reviewRepository.delete(rmvReview);
        return ResponseEntity.ok().body(rmvReview);
    }

}
