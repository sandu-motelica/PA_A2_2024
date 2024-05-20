package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class GameServer {
    private ServerSocket serverSocket;
    private Map<Integer, Game> games = new HashMap<>();
    private int gameIdCounter = 1;
    private static final long TIME_LIMIT = 60000;

    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized int createGame() {
        int gameId = gameIdCounter++;
        games.put(gameId, new Game(TIME_LIMIT));
        return gameId;
    }

    public synchronized Game joinGame(int gameId) {
        return games.get(gameId);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientThread(clientSocket, this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int port = 1234;
        GameServer server = new GameServer(port);
        server.start();
    }
}
