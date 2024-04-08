package lab6.compulsory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState implements Serializable {
    int rows, cols;
    private boolean playerOneTurn = true;

    private Position previousPosition;
    private int[][] nodesOccupied;
    private List<Stick> sticks = new ArrayList<>();

    public GameState(int rows, int cols, Position prevPoint, int[][] nodesOccupied, boolean playerOneTurn, List<Stick> sticks) {
        this.rows = rows;
        this.cols = cols;
        this.previousPosition = prevPoint;
        this.nodesOccupied = nodesOccupied;
        this.playerOneTurn = playerOneTurn;
        this.sticks = sticks;
    }

    public int[][] getNodesOccupied() {
        return nodesOccupied;
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public List<Stick> getSticks() {
        return sticks;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", playerOneTurn=" + playerOneTurn +
                ", previousPosition=" + previousPosition +
                ", nodesOccupied=" + Arrays.toString(nodesOccupied) +
                ", sticks=" + sticks +
                '}';
    }
}
