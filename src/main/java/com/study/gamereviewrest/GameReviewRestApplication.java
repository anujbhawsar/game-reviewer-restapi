package com.study.gamereviewrest;

import com.study.gamereviewrest.model.Game;
import com.study.gamereviewrest.model.Review;
import com.study.gamereviewrest.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GameReviewRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameReviewRestApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner(GameRepository gameRepository){
//		return args->{
//			Review review = new Review(
//					"Anuj",
//					"mst game",
//					9.0
//			);
//			Game game = new Game(
//					"MineCraft",
//					List.of(review),
//					"9"
//			);
//			gameRepository.insert(game);
//		};
//	}

}
