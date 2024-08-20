package com.example.connections_game.dto;

public class GuessResult {
    private boolean correct;
    private GameState gameState;

    public GuessResult(boolean correct, GameState gameState) {
        this.correct = correct;
        this.gameState = gameState;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isCorrect() {
        return correct;
    }

    public GameState getGameState() {
        return gameState;
    }
    // Getters and Setters
}
