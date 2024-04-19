
package org.example;

import java.util.ArrayList;
import java.util.Collections;
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
                while (!game.isPlayerTurn(this)) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!game.isGameRunning()) {
                    System.exit(-1);
                }

                List<Tile> extractedTiles = game.extractTiles(2);
                if (extractedTiles.isEmpty()) {
                    System.out.println("Empty bag");
                    game.displayWinner();
                    System.exit(-1);
                }
                tiles.add(extractedTiles.get(0));
                tiles.add(extractedTiles.get(1));
                System.out.println(name + " a ridicat: " + extractedTiles);
                if (completeSequence()) {
                    System.out.println("Winner was determined before emptying bag");
                    game.displayWinner();
                    System.exit(-1);
                }
                game.endTurn();
                game.notifyAll();
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

    public String getName() {
        return name;
    }


    public List<Tile> bestSequence() {
        Collections.sort(tiles);
        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i - 1).getNumber1() == tiles.get(i).getNumber1()) {
                tiles.remove(i);
                i--;
            }
        }

        int maxLengthSeq = 0;
        List<Tile> bestSeq = new ArrayList<>();
        int currentLength = 0;
        int endIndex = 0;

        if (tiles.isEmpty()) {
            return bestSeq;
        }

        for (int i = 1; i < tiles.size(); i++) {
            Tile prev = tiles.get(i - 1);
            Tile current = tiles.get(i);

            if (prev.getNumber2() == current.getNumber1()) {
                currentLength++;
            } else {
                currentLength = 0;
            }
            if (currentLength > maxLengthSeq) {
                maxLengthSeq = currentLength;
                endIndex = i;
            }
        }
        for (int i = endIndex - maxLengthSeq; i <= endIndex; i++) {
            bestSeq.add(tiles.get(i));
        }
        return bestSeq;
    }

    public boolean completeSequence(){
        List<Tile> bestSeq = this.bestSequence();
        if(bestSeq.get(0).getNumber1() == bestSeq.get(bestSeq.size()-1).getNumber2()){
            return true;
        }
        return false;
    }

}