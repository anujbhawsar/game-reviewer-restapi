package com.venom.game;


import com.venom.game.Exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game")
    public List<Game> getGame(){
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id")String id) throws  GameNotFoundException{
        Game game = gameRepository.findById(java.util.UUID.fromString(id)).orElseThrow(
                ()->new GameNotFoundException("No Game with such id present Found"));
        return ResponseEntity.ok().body(game);
    }
    @PostMapping("/game")
    public Game createGame(@RequestBody Game game){
        game.setId(java.util.UUID.randomUUID());
        return gameRepository.save(game);
    }

    @PutMapping("/game/{id}")
    public ResponseEntity updateGame(@PathVariable(value = "id") String id, @RequestBody Game game)
            throws GameNotFoundException{
        Game updatedGame = gameRepository.findById(java.util.UUID.fromString(id)).orElseThrow(
                ()->new GameNotFoundException("No Game to change"));
        updatedGame.setGameName(game.getGameName());
        updatedGame.setGameGenre(game.getGameGenre());
        gameRepository.save(updatedGame);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/game/{id}")
    public ResponseEntity deleteGame(@PathVariable(value = "id") String id) throws GameNotFoundException{
        Game rmvGame = gameRepository.findById(java.util.UUID.fromString(id)).orElseThrow(
                ()->new GameNotFoundException("No game to delete"));
        gameRepository.delete(rmvGame);
        return ResponseEntity.ok().body(rmvGame);
    }
}
