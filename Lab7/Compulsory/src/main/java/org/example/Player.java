
package org.example;

import java.util.ArrayList;
import java.util.List;

class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running;
    private List<Tile> tiles = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void run() {
        while (running) {
            synchronized (game) {
                if (game.isPlayerTurn(this)) {
                    List<Tile> extractedTiles = game.extractTiles(2);
                    if (extractedTiles.isEmpty()) {
                        System.out.println("Empty bag");
                        System.exit(-1);
                    }
                    System.out.println(name + " a ridicat: " + extractedTiles);
                    game.endTurn();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}