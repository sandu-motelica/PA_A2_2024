package org.example.server;
import java.io.*;
import java.net.*;

public class GameServer {
    private boolean running = true;

    public void stopServer() {
        running = false;
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.start(1234);
    }
}
