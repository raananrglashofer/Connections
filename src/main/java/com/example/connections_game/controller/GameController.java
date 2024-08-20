package com.example.connections_game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.connections_game.Connections.*;

import java.util.HashSet;
import java.util.*;

@RestController
public class GameController {

    private Game game;

    public GameController() {
        this.game = new Game(); // Initialize the game with a default player
    }

    @GetMapping("/start")
    public String startGame() {
        game.startNewGame();

        return "Game Started!";
    }

    @PostMapping("/guess")
    public ResponseEntity<Map<String, Object>> makeGuess(@RequestBody Set<String> guess) {
        Map<String, Object> response = new HashMap<>();

        // Call the guess method on the Game instance
        boolean correct = game.guess(guess);

        response.put("correct", correct);
        response.put("remainingWords", game.getCurrentPuzzle().getWords());
        response.put("remainingLives", game.getPlayer().getLives());

        // Check if the puzzle is solved
        if (game.getCurrentPuzzle().isSolved()) {
            response.put("message", "Congratulations! You've solved the puzzle!");
            response.put("gameOver", true);
        } else if (!correct && game.getPlayer().getLives() == 0) {
            response.put("message", "Game Over. No more lives left.");
            response.put("gameOver", true);
        } else {
            response.put("message", correct ? "Correct! Keep going!" : "Incorrect. Try again.");
            response.put("gameOver", false);
        }

        return ResponseEntity.ok(response);
    }
}
