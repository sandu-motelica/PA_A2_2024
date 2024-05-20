package org.example;
import java.io.PrintWriter;

public class Player {
    private Game game;
    private PrintWriter out;

    public Player(Game game, PrintWriter out) {
        this.game = game;
        this.out = out;
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
