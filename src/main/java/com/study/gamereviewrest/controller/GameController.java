package com.study.gamereviewrest.controller;

import com.study.gamereviewrest.exceptionHangler.GameNotFoundException;
import com.study.gamereviewrest.model.Game;
import com.study.gamereviewrest.model.Review;
import com.study.gamereviewrest.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @GetMapping("/review")
    public List<Game> getGame(){
        return gameRepository.findAll();
    }
    @PostMapping("/review")
    public Game createGameReview(@RequestBody Game game){
        return gameRepository.save(game);
    }

    @PutMapping("/review/{gameName}")
    public ResponseEntity<Game> addGameReview(@PathVariable (value = "gameName") String gameName, @RequestBody Review review) throws GameNotFoundException{           //, @RequestBody Review review
        Game game = gameRepository.findByGameName(gameName);
        if(game == null){
            throw new GameNotFoundException("Game does not exist for giving the review!! Create new entry for Game" + gameName);
        }
        List<Review> newReviews = game.getReviews();
        newReviews.add(review);
        game.setReviews(newReviews);
        gameRepository.save(game);
        System.out.println("Review added");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/review/{gameName}")
    public ResponseEntity<?> deleteGame(@PathVariable(value = "gameName")String gameName)throws GameNotFoundException{
        Game game = gameRepository.findByGameName(gameName);
        if(game == null){
            throw new GameNotFoundException("Game does not exist for giving the review!! Create new entry for Game" + gameName);
        }
        gameRepository.deleteByGameName(gameName);
        return ResponseEntity.ok().build();
    }

}
