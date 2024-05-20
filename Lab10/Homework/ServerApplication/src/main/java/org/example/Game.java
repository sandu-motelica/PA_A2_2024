package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class Game {
    private long startTime;
    private long timeLimit;
    private long player1TimeLeft;
    private long player2TimeLeft;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private boolean gameStarted = false;
    private Board board;
    private long lastUpdateTime;


    public Game(long timeLimit) {
        this.timeLimit = timeLimit;
        this.player1TimeLeft = timeLimit;
        this.player2TimeLeft = timeLimit;
        board = new Board();
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) {
            players.add(player);
            if (players.size() == 2) {
                gameStarted = true;
            }
        }
    }

    public String makeMove(Player player, int x, int y) {
        if (!gameStarted) {
            return "Game has not started yet.";
        }
        if (players.get(currentPlayerIndex) != player) {
            return "It's not your turn.";
        }

        if ((x >= board.getSIZE() || x < 0) || (y >= board.getSIZE() || y < 0)) {
            return "Move out of bounds.";
        }

        String result = board.attack(x, y);
        if (result.equals("Miss")) {
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }
        return result;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void notifyGameStart() {
        for (Player player : players) {
            player.sendMessage("Game started! You have 1 minute");
        }
        players.get(currentPlayerIndex).sendMessage("Your turn");
        players.get((currentPlayerIndex + 1) % 2).sendMessage("Wait your opponent move");

    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public void startGame() {
        startTime = System.currentTimeMillis();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updatePlayerTime();
            }
        }, 0, 1000);
    }
    public synchronized void updatePlayerTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastUpdateTime;
        player1TimeLeft -= elapsedTime;
        player2TimeLeft -= elapsedTime;
        lastUpdateTime = currentTime;
    }


    public synchronized boolean isTimeUp(int player) {
        long remainingTime = (player == 1) ? player1TimeLeft : player2TimeLeft;
        return remainingTime <= 0;
    }

    public void notifyTimeUp(int player) {
        players.get(currentPlayerIndex).sendMessage("Time's up!");
        players.get((currentPlayerIndex + 1) % 2).sendMessage("You win!");
    }

}
