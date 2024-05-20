package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread listenerThread = new Thread(new ServerResponseListener());
            listenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerResponseListener implements Runnable {
        @Override
        public void run() {
            try {
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameClient client = new GameClient();
        client.startConnection("127.0.0.1", 1234);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        try {
            while ((userInput = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInput)) {
                    client.stopConnection();
                    break;
                } else {
                    client.sendMessage(userInput);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
