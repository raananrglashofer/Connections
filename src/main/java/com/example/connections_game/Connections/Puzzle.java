package com.example.connections_game.Connections;
import java.util.*;

public class Puzzle {
    private Set<String> words;
    private List<Connection> connections;
    // hints are not given by difficulty for now - given out randomly
    private List<String> hints;
    private Set<Connection> solvedConnections = new HashSet<>();
    private Set<String> hintsGiven = new HashSet<>();
    public Puzzle(){

    }
    public Puzzle(Set<String> words, List<Connection> connections, List<String> hints){
        this.words = words;
        this.connections = connections;
        this.hints = hints;
    }

    public String isCorrect(Set<String> guess){
        for (Connection connection : connections) {
            if (connection.getWords().equals(guess)) {
                if (!solvedConnections.contains(connection)) {
                    solvedConnections.add(connection);
                    getRidOfWords(guess);
                    return connection.getConnection();
                }
            }
        }
        return null;
    }

    public boolean isSolved() {
        return solvedConnections.size() == connections.size();
    }

    public String getHint(){
        if(this.hints.isEmpty()){
            return "All hints have been given";
        }
        String hint = this.hints.get(0);
        hints.remove(hint);
        hintsGiven.add(hint);
        return hint;
    }
    public Set<String> getWords(){
        return this.words;
    }
    public List<String> getHints(){
        return this.hints;
    }
    public Set<String> getHintsGiven(){
        return this.hintsGiven;
    }

    private void getRidOfWords(Set<String> guess){
        for(String word : guess){
            words.remove(word);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return Objects.equals(words, puzzle.words) &&
                Objects.equals(connections, puzzle.connections) &&
                Objects.equals(hints, puzzle.hints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words, connections, hints);
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public Set<Connection> getSolvedConnections(){
        return this.solvedConnections;
    }
}
