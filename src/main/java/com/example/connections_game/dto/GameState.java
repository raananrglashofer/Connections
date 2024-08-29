package com.example.connections_game.dto;

import com.example.connections_game.Connections.Game;
import java.util.*;

public class GameState {
    private Set<String> wordsLeft;
    private int livesLeft;
    private int hintsLeft;
    private boolean gameOver;
    private Set<String> hints;

    public GameState(Game game) {
        this.wordsLeft = game.getCurrentPuzzle().getWords();
        this.livesLeft = game.getPlayer().getLives();
        this.hintsLeft = game.getCurrentPuzzle().getHints().size();
        this.gameOver = game.getPlayer().getLives() <= 0 || game.getCurrentPuzzle().isSolved();
        this.hints = game.getCurrentPuzzle().getHintsGiven();
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

    public void setHints(Set<String> hints) {
        this.hints = hints;
    }

    public Set<String> getHints(){
        return this.hints;
    }
}
