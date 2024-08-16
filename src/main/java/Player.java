import java.time.LocalDateTime;

public class Player {
    private String name;
    private long startTime;
    private int lives = 4;
    
    public Player(String name){
        this.name = name;
        this.startTime = System.nanoTime();
    }

    public void decreaseLife(){
        this.lives--;
    }
    public int getLives(){
        return this.lives;
    }
}
