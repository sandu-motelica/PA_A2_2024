package org.example;
import java.util.concurrent.TimeUnit;

public class TimeKeeper implements Runnable {
    private final long timeLimitMillis;
    private final Game game;

    public TimeKeeper(long timeLimitMillis, Game game) {
        this.timeLimitMillis = timeLimitMillis;
        this.game = game;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= timeLimitMillis) {
                game.stopGame();
                System.out.println("Time limit exceeded. Stopping the game.");

                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

