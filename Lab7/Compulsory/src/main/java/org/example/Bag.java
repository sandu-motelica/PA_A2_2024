package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Bag {
    private final BlockingQueue<Tile> tiles;

    public Bag() {
        tiles = new ArrayBlockingQueue<>(40);
        initializeTiles();
    }

    private void initializeTiles() {
        Random random = new Random();
        for (int i = 1; i <= 40; i++) {
            int num1 = random.nextInt(10) + 1;
            int num2 = random.nextInt(10) + 1;
            Tile tile = new Tile(num1, num2);
            tiles.add(tile);
        }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            Tile tile = tiles.poll();
            if (tile == null) {
                break;
            }
            extracted.add(tile);
        }
        return extracted;
    }
}
