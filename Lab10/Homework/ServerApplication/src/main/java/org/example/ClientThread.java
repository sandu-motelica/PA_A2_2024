package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private GameServer server;
    private PrintWriter out;
    private BufferedReader in;
    private Game game;
    private Player player;
    private static final long MOVE_TIME = 5000;

    public ClientThread(Socket socket, GameServer server) {
        this.clientSocket = socket;
        this.server = server;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                String[] commands = inputLine.split(" ");
                switch (commands[0].toLowerCase()) {
                    case "create":
                        int gameId = server.createGame();
                        out.println("Game created with ID: " + gameId);
                        break;
                    case "join":
                        gameId = Integer.parseInt(commands[1]);
                        game = server.joinGame(gameId);
                        if (game != null) {
                            player = new Player(game, out);
                            game.addPlayer(player);
                            if (game.isGameStarted()) {
                                game.notifyGameStart();
                            } else {
                                out.println("Joined game with ID: " + gameId);
                            }
                        } else {
                            out.println("Game not found");
                        }
                        break;
                    case "move":
                        int x = Integer.parseInt(commands[1]);
                        int y = Integer.parseInt(commands[2]);
                        if (game != null && player != null) {
                            String result = game.makeMove(player, x, y);
                            out.println(result);
                            if(result.equals("Miss")) {
                                game.getPlayers().get(game.getCurrentPlayerIndex()).sendMessage("Your turn");
                                game.getPlayers().get((game.getCurrentPlayerIndex() + 1) % 2).sendMessage("Wait your opponent move");
                            }
                            game.updatePlayerTime();
                            if (game.isTimeUp(game.getCurrentPlayerIndex())) {
                                game.notifyTimeUp(game.getCurrentPlayerIndex());
                            }
                        } else {
                            out.println("You are not in a game");
                        }
                        break;
                    case "stop":
                        out.println("Server stopped");
                        clientSocket.close();
                        return;
                    default:
                        out.println("Unknown command");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
