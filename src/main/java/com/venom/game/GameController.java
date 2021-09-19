package com.venom.game;


import com.venom.game.exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getGame(){
        return gameRepository.findAll();
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id")String id) throws  GameNotFoundException{
        Game game = gameRepository.findById(java.util.UUID.fromString(id)).orElseThrow(
                ()->new GameNotFoundException());
        return ResponseEntity.ok().body(game);
    }
    @PostMapping("/games")
    public Game createGame(@RequestBody Game game){
        game.setId(java.util.UUID.randomUUID());
        return gameRepository.save(game);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity updateGame(@PathVariable(value = "id") String id, @RequestBody Game game)
            throws GameNotFoundException{
        Game updatedGame = gameRepository.findById(java.util.UUID.fromString(id)).orElseThrow(
                ()->new GameNotFoundException());
        updatedGame.setName(game.getName());
        updatedGame.setGenre(game.getGenre());
        gameRepository.save(updatedGame);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity deleteGame(@PathVariable(value = "id") String id) throws GameNotFoundException{
        Game rmvGame = gameRepository.findById(java.util.UUID.fromString(id)).orElseThrow(
                ()->new GameNotFoundException());
        gameRepository.delete(rmvGame);
        return ResponseEntity.ok().body(rmvGame);
    }
}
