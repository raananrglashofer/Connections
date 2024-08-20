package com.example.connections_game.dto;

import com.example.connections_game.Connections.Game;
import java.util.Set;

public class GameState {
    private Set<String> wordsLeft;
    private int livesLeft;
    private int hintsLeft;
    private boolean gameOver;

    public GameState(Game game) {
        this.wordsLeft = game.getCurrentPuzzle().getWords();
        this.livesLeft = game.getPlayer().getLives();
        this.hintsLeft = game.getCurrentPuzzle().getHints().size();
        this.gameOver = game.getPlayer().getLives() <= 0 || game.getCurrentPuzzle().isSolved();
    }

    public void setWordsLeft(Set<String> wordsLeft) {
        this.wordsLeft = wordsLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    public void setHintsLeft(int hintsLeft) {
        this.hintsLeft = hintsLeft;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Set<String> getWordsLeft() {
        return wordsLeft;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public int getHintsLeft() {
        return hintsLeft;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
