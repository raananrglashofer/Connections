package com.example.connections_game.Connections;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class PuzzleGenerator {
    // Constants for the API URL and the API key
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = System.getenv("API_KEY");

    public Puzzle fetchNewPuzzle() {
        // Use a try-with-resources statement to ensure the HTTP client is closed
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create an HTTP POST request
            HttpPost request = new HttpPost(API_URL);
            // Set the headers for the request
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Bearer " + API_KEY);

            // The JSON payload for the request
            String json = "{\n" +
                    "  \"model\": \"gpt-3.5-turbo\",\n" +
                    "  \"messages\": [{\"role\": \"system\", \"content\": \"Generate a puzzle with 16 words and " +
                    "their connections just like the new york times game connections and provide a hint for " +
                    "each connection. The game works that there are four groups of 4 words and each group is one word or" +
                    "phrase connecting the four words. All 16 words should be one word max and in lowercase form. Please do it on medium mdoe" +
                    "Provide the response in JSON format with the following structure: " +
                    "'words' should be a set of 16 words. 'connections' should be an list of objects, where each object " +
                    "has two fields: 'words', which is a set of 4 words that form a group, and 'connection', which is a " +
                    "string that represents the connection for that group. " +
                    "'hints' should be a list of 4 strings, each representing a hint for one of the connections.\"}]\n" +
                    "}";
            // Set the entity of the request with the JSON payload
            request.setEntity(new StringEntity(json));

            // Send the request and get the response
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // Create an ObjectMapper to parse the JSON response
                ObjectMapper mapper = new ObjectMapper();
                // Parse the JSON response
                JsonNode root = mapper.readTree(response.getEntity().getContent());
                System.out.println(root.toPrettyString());
                // Extract the content of the message from the response
                JsonNode messageContent = root.path("choices").get(0).path("message").path("content");
                // Parse the message content to extract puzzle details
                return parsePuzzle(messageContent.asText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to parse the JSON response into a Puzzle object
    private Puzzle parsePuzzle(String jsonResponse) {
        // Create an ObjectMapper to parse the JSON response
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Parse the JSON response into a Puzzle object
            return mapper.readValue(jsonResponse, Puzzle.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        PuzzleGenerator generator = new PuzzleGenerator();
//        Puzzle puzzle = generator.fetchNewPuzzle();
//        if (puzzle != null) {
//            System.out.println("Puzzle fetched successfully!");
//            System.out.println("Words: " + puzzle.getWords());
//            System.out.println("Hints: " + puzzle.getHints());
//        } else {
//            System.out.println("Failed to fetch puzzle.");
//        }
//    }

}
