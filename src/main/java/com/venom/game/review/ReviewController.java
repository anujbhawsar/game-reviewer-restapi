package com.venom.game.review;

import com.venom.game.Exceptions.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1.0/game/")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/{gameID}/review")
    public List<Review> getAllGameReview(@PathVariable(value = "gameID")UUID gameID){
        return reviewRepository.findAllByGameID(gameID);
    }

    @GetMapping("/review/{reviewID}")
    public Review getReview(@PathVariable(value = "reviewID")UUID reviewID) throws ReviewNotFoundException {
        return reviewRepository.findById(reviewID).orElseThrow(()->new ReviewNotFoundException("Review ID not found"));
    }
    @GetMapping("/review")
    public List<Review> getAllReview(){
            return reviewRepository.findAll();
    }

    @PostMapping("/{gameID}/review")
    public ResponseEntity addReview(@PathVariable(value = "gameID")UUID gameID,@RequestBody Review review){
        review.setGameID(gameID);
        review.setReviewID(java.util.UUID.randomUUID());
        reviewRepository.save(review);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/review/{reviewID}")
    public ResponseEntity updateGame(@PathVariable(value = "reviewID") UUID reviewID, @RequestBody Review review)
            throws ReviewNotFoundException {
        Review updatedReview = reviewRepository.findById(reviewID).orElseThrow(
                ()->new ReviewNotFoundException("No Review to change"));
        if(review.getGameID()==null) {
            updatedReview.setGameID(review.getGameID());
        }
        updatedReview.setAuthor(review.getAuthor());
        updatedReview.setContent(review.getContent());
        reviewRepository.save(updatedReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/review/{reviewID}")
    public ResponseEntity deleteGame(@PathVariable(value = "reviewID") UUID reviewID) throws ReviewNotFoundException{
        Review rmvReview = reviewRepository.findById(reviewID).orElseThrow(
                ()->new ReviewNotFoundException("No game to delete"));
        reviewRepository.delete(rmvReview);
        return ResponseEntity.ok().body(rmvReview);
    }

}
