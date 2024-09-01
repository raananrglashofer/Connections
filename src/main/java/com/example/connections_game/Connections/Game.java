package com.example.connections_game.Connections;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Game {
    private Puzzle currentPuzzle;
    private Set<Puzzle> cachedPuzzles;
    private Player player;
    private PuzzleGenerator puzzleGenerator;
    private boolean gameOver = false;

    public Game(){
        //this.player = new Player(playerName);
        this.player = new Player("Raanan");
        this.cachedPuzzles = new HashSet<>();
        this.puzzleGenerator = new PuzzleGenerator();
        startNewGame();
    }

    public boolean isGameOver(){
        return this.gameOver;
    }


    public void startNewGame(){
        Puzzle newPuzzle = puzzleGenerator.fetchNewPuzzle();
        while (!checkCached(newPuzzle)) {
            newPuzzle = puzzleGenerator.fetchNewPuzzle();
        }
        this.gameOver = false;
        this.currentPuzzle = newPuzzle;
        cacheThePuzzle(newPuzzle);
        getPlayer().resetLives();
        System.out.println("New Puzzle started!");
        System.out.println("Words to guess: " + currentPuzzle.getWords());
    }
    public boolean guess(Set<String> guess) {
        Set<String> lowerCaseGuess = new HashSet<>();
        for (String word : guess) {
            lowerCaseGuess.add(word.toLowerCase());
        }
        if (guess.size() != 4) {
            // I want to return a seperate message though
            //throw new IllegalArgumentException("Guess must be four words" + "\n" + "Guess Again");
            getPlayer().decreaseLife();
            if(getPlayer().getLives() < 1){
                gameOver();
            }
            return false;
        }
        String connection = currentPuzzle.isCorrect(lowerCaseGuess);
        if (connection != null) {
            guessRight(connection);
            return true;
        } else {
            guessWrong();
            return false;
        }
    }

    public String getHint() {
        return "Here is Your Hint: " + currentPuzzle.getHint();
    }


    private String guessRight(String connection){
        StringBuilder str = new StringBuilder("Connection - " + connection);
        if (currentPuzzle.isSolved()) {
            str.append("Congratulations! You've solved the puzzle! " + "\n" + "Do you want to play again?");
            gameOver();
        } else {
            str.append("Keep going! Words left: " + currentPuzzle.getWords());
        }
        return str.toString();
    }

    private String guessWrong(){
        player.decreaseLife();
        StringBuilder str = new StringBuilder();
        if (player.getLives() == 0) {
            str.append("Game Over" + "\n");
            // reveal connections
            for(Connection connect : this.currentPuzzle.getConnections()){
                str.append(connect.getWords() + " - " + connect.getConnection() + "\n");
            }
            gameOver();
        }
        str.append("Incorrect - Guess Again" + " You have " + this.player.getLives() + " lives left");
        return str.toString();
    }
    // need to figure out logic for this and get user input
    private void gameOver() {
        this.gameOver = true;
    }

    // returns true if new puzzle
    private boolean checkCached(Puzzle puzzle) {
        return !cachedPuzzles.contains(puzzle);
    }
    private void cacheThePuzzle(Puzzle puzzle) {
        if (cachedPuzzles.size() >= 20) {
            cachedPuzzles.remove(cachedPuzzles.iterator().next());
        }
        cachedPuzzles.add(puzzle);
    }

    public Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public Player getPlayer() {
        return player;
    }
}
