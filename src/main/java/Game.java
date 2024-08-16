import java.util.*;
public class Game {
    private Puzzle currentPuzzle;
    private Set<Puzzle> cachedPuzzles;
    private Player player;
    private PuzzleGenerator puzzleGenerator;

    public Game(String playerName){
        this.player = new Player(playerName);
        this.cachedPuzzles = new HashSet<>();
        this.puzzleGenerator = new PuzzleGenerator();
        startNewGame();
    }

    public void startNewGame(){
        Puzzle newPuzzle = puzzleGenerator.fetchNewPuzzle();
        while (!checkCached(newPuzzle)) {
            newPuzzle = puzzleGenerator.fetchNewPuzzle();
        }
        this.currentPuzzle = newPuzzle;
        cacheThePuzzle(newPuzzle);
        System.out.println("New Puzzle started!");
        System.out.println("Words to guess: " + currentPuzzle.getWords());
    }
    public String guess(Set<String> guess) {
        Set<String> lowerCaseGuess = new HashSet<>();
        for (String word : guess) {
            lowerCaseGuess.add(word.toLowerCase());
        }
        if(guess.contains("hint")){
            return "Here is Your Hint: " + getHint();
        }
        if (guess.size() != 4) {
            throw new IllegalArgumentException("Guess must be four words" + "\n" + "Guess Again");
        }
        String connection = currentPuzzle.isCorrect(lowerCaseGuess);
        if (connection != null) {
            return guessRight(connection);
        } else {
            return guessWrong();
        }
    }

    private String guessRight(String connection){
        StringBuilder str = new StringBuilder("Connection - " + connection);
        if (currentPuzzle.isSolved()) {
            str.append("Congratulations! You've solved the puzzle! " + "\n" + "Do you want to play again?");
            playAgain();
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
            playAgain();
        }
        str.append("Incorrect - Guess Again" + " You have " + this.player.getLives() + " lives left");
        return str.toString();
    }

    private boolean playAgain() {
        return true;
    }

    public String getHint(){
        return currentPuzzle.getHint();
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

//    public static void main (String[] args){
//        Game game = new Game("Raanan");
//
//        while (true) {
//            System.out.println("Enter your guess (4 words separated by spaces): ");
//            String input = scanner.nextLine();
//            if (input.equalsIgnoreCase("EXIT")) {
//                System.out.println("Thanks for playing! Goodbye!");
//                break;
//            }
//            String[] words = input.split(" ");
//            Set<String> guess = new HashSet<>();
//            for (String word : words) {
//                guess.add(word.trim());
//            }
//            game.guess(guess);
//        }
//    }
}
