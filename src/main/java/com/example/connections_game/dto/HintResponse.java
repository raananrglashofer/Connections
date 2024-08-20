package com.example.connections_game.dto;

public class HintResponse {
    private String hint;
    private GameState gameState;

    public HintResponse(String hint, GameState gameState) {
        this.hint = hint;
        this.gameState = gameState;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getHint() {
        return hint;
    }

    public GameState getGameState() {
        return gameState;
    }
    // Getters and Setters
}
