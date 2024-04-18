package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Bag {
    private final BlockingQueue<Tile> tiles;
    private int rightLimit = 10;
    private int bagSize = 60;

    public Bag() {
        tiles = new ArrayBlockingQueue<>(60);
        initializeTiles();
    }

    private void initializeTiles() {
        Random random = new Random();
        for (int i = 1; i <= bagSize; i++) {
            int num1 = random.nextInt(rightLimit) + 1;
            int num2;
            if (num1 == rightLimit){
                num2 = 1;
            }
            else num2 = num1 + 1;
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

    public int getRightLimit() {
        return rightLimit;
    }

    public void setRightLimit(int rightLimit) {
        this.rightLimit = rightLimit;
    }

    public int getBagSize() {
        return bagSize;
    }

    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }
}
