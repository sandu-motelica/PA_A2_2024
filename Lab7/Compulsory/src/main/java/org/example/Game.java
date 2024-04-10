package org.example;

import java.util.ArrayList;
import java.util.List;

class Game {
    private final Bag bag = new Bag();
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            Thread playerThread = new Thread(player);
            player.setRunning(true);
            playerThread.start();
        }
    }

    public boolean isPlayerTurn(Player player) {
        return players.indexOf(player) == currentPlayerIndex;
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        return bag.extractTiles(howMany);
    }

    public synchronized void endTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}