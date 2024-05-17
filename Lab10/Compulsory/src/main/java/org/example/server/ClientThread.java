package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private GameServer server;

    public ClientThread(Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server received the request: " + inputLine);
                if (inputLine.equals("stop")) {
                    server.stopServer();
                    out.println("Server stopped");
                    break;
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}