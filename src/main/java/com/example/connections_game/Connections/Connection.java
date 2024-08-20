package com.example.connections_game.Connections;

import java.util.*;
public class Connection {
    private Set<String> words;
    private String connection;

    public Connection(){
        // for jackson
    }

    public Connection(Set<String> words, String connection){
        this.words = words;
        this.connection = connection;
    }

    public Set<String> getWords() {
        return words;
    }

    public String getConnection() {
        return connection;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}
