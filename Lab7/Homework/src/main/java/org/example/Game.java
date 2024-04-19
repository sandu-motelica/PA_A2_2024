package org.example;

import java.util.ArrayList;
import java.util.List;

class Game {
    private final Bag bag = new Bag();
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    private boolean gameRunning = true;


    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        Thread timeKeeperThread = new Thread(new TimeKeeper(1, this));
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();

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

    public synchronized boolean isGameRunning() {
        return gameRunning;
    }

    public synchronized void stopGame() {
        gameRunning = false;
        notifyAll();
        displayWinner();
    }

    public void displayWinner() {
        int maxScore = -1;
        int winner = 0;
        int i = 0;
        for (Player p : players) {
            List<Tile> bestSequence = p.bestSequence();
            if (bestSequence.size() > maxScore) {
                maxScore = bestSequence.size();
                winner = i;
            }
            System.out.println(p.getName());
            for (Tile t : bestSequence) {
                System.out.print(t + " ");
            }
            System.out.println();
            i++;
        }
        System.out.println("🏆 Winner is " + players.get(winner).getName());
    }

    @Override
    public String toString() {
        StringBuilder playersTemp = new StringBuilder();
        for (Player p : players) {
            playersTemp.append(p.getName());
            playersTemp.append(", ");
        }
        return "Game{" +
                "bag=" + bag +
                ", players=" + playersTemp +
                ", currentPlayerIndex=" + currentPlayerIndex +
                '}';
    }

}
