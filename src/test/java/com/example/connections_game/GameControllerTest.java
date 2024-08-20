package com.example.connections_game;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.connections_game.Connections.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc; // Spring should handle this

    @Autowired
    private Game game; // Allow Spring to inject this

    @Test
    public void testMakeGuess() throws Exception {
        // Arrange: Set up the game with an initial state if necessary
        game.startNewGame();

        // Act: Perform a POST request to the /guess endpoint
        mockMvc.perform(post("/guess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"word1\", \"word2\", \"word3\", \"word4\"]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").exists())
                .andExpect(jsonPath("$.remainingWords").exists())
                .andExpect(jsonPath("$.remainingLives").exists())
                .andExpect(jsonPath("$.gameOver").exists());
    }
}
