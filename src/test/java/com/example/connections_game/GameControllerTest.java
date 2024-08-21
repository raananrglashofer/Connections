package com.example.connections_game;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStartGame() throws Exception {
        mockMvc.perform(post("/game/start"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words").exists());
    }

    @Test
    public void testMakeGuess() throws Exception {
        mockMvc.perform(post("/game/start"));

        mockMvc.perform(post("/game/guess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"word1\", \"word2\", \"word3\", \"word4\"]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").exists())
                .andExpect(jsonPath("$.gameState").exists());
    }

    @Test
    public void testGetHint() throws Exception {
        mockMvc.perform(post("/game/start"));

        mockMvc.perform(get("/game/hint"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hint").exists())
                .andExpect(jsonPath("$.gameState").exists());
    }

    @Test
    public void testGetGameState() throws Exception {
        mockMvc.perform(post("/game/start"));

        mockMvc.perform(get("/game/state"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words").exists());
    }
}
