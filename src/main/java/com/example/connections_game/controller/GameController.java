package com.example.connections_game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.connections_game.Connections.*;
import com.example.connections_game.dto.*;

import java.util.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final Game game;
    @Autowired
    public GameController() {
        this.game = new Game(); // Initialize the game
    }

    @PostMapping("/start")
    public ResponseEntity<GameState> startGame() {
        game.startNewGame();
        return ResponseEntity.ok(new GameState(game));
    }

    @PostMapping("/guess")
    public ResponseEntity<GuessResult> makeGuess(@RequestBody Set<String> guess) {
        boolean correct = game.guess(guess);
        return ResponseEntity.ok(new GuessResult(correct, new GameState(game)));
    }

    @GetMapping("/hint")
    public ResponseEntity<HintResponse> getHint() {
        String hint = game.getHint();
        return ResponseEntity.ok(new HintResponse(hint, new GameState(game)));
    }

    @GetMapping("/state")
    public ResponseEntity<GameState> getGameState() {
        return ResponseEntity.ok(new GameState(game));
    }
}
