package org.example;
public class Board {
    private final int SIZE = 10;
    private char[][] grid;

    public Board() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '-';
            }
        }
        // Example ship placement
        grid[0][0] = 'S';
        grid[0][1] = 'S';
        grid[0][2] = 'S';
        grid[9][5] = 'S';
        grid[9][6] = 'S';
        grid[9][7] = 'S';
        grid[9][8] = 'S';
    }

    public String attack(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'H';
            return "Hit";
        } else if (grid[x][y] == '-') {
            grid[x][y] = 'M';
            return "Miss";
        } else {
            return "Already attacked";
        }
    }

    public int getSIZE() {
        return SIZE;
    }
}
