package com.example.connections_game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.connections_game.Connections.*;
import com.example.connections_game.dto.*;

import java.util.*;

@Controller
public class GameController {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @GetMapping("/")
    public String welcomePage() {
        return "start";
    }

    @GetMapping("/game/start")
    public String startGame(Model model) {
        game.startNewGame();
        model.addAttribute("gameState", new GameState(game));
        return "game";  // Return the name of the game.html template
    }



    @PostMapping("/game/guess")
    public String makeGuess(@RequestParam("guess") String guessInput, Model model) {
        Set<String> guess = new HashSet<>(Arrays.asList(guessInput.split(",")));
        boolean correct = game.guess(guess);
        GameState gameState = new GameState(game);
        model.addAttribute("correct", correct);
        model.addAttribute("gameState", gameState);
        return "game";
    }

    @PostMapping("game/hint")
    public String getHint(Model model) {
        String hint = game.getHint();
        model.addAttribute("hint", hint);
        model.addAttribute("gameState", new GameState(game));
        return "game";
    }

    @GetMapping("/game/state")
    public String getGameState(Model model) {
        model.addAttribute("gameState", new GameState(game));
        return "game";
    }
}
