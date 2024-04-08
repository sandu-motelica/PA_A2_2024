package lab6.compulsory;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends Canvas {
    int rows, cols;
    int canvasWidth = 440, canvasHeight = 440;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    double halfStone = (double) stoneSize / 2;
    private boolean playerOneTurn = true;
    private int status = 0;
    private Position previousPosition;
    private int[][] nodesOccupied;
    private List<Stick> sticks = new ArrayList<>();

    public DrawingPanel(int sizeX, int sizeY) {
        nodesOccupied = new int[sizeX][sizeY];
        previousPosition = new Position(-1, -1);
        this.rows = sizeX;
        this.cols = sizeY;
        generateSticks();
        init();
    }

    final void init() {
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setWidth(canvasWidth);
        setHeight(canvasHeight);
        drawGrid();
        clickStones();

    }

    public void clickStones(){
        this.setOnMousePressed(e -> {
            if(status == 0 || status == 1 && playerOneTurn) {
                double x = e.getX();
                double y = e.getY();
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        double nodeX = padX + col * cellWidth - halfStone;
                        double nodeY = padY + row * cellHeight - halfStone;
                        if (x > nodeX && x < nodeX + stoneSize && y > nodeY && y < nodeY + stoneSize) {
                            if (isValidMove(row, col)) {
                                previousPosition.setRow(row);
                                previousPosition.setCol(col);
                                if (playerOneTurn) {
                                    nodesOccupied[row][col] = 2;
                                    drawStone(nodeX, nodeY, Color.BLUE);
                                    playerOneTurn = false;
                                } else {
                                    nodesOccupied[row][col] = 3;
                                    drawStone(nodeX, nodeY, Color.RED);
                                    playerOneTurn = true;
                                }
                                if (gameOver()) {
                                    exportBoardToPNG();
                                    System.out.println("Player " + (playerOneTurn ? "RED" : "BLUE") + " win");
                                }
                                return;
                            }
                        }
                    }
                }
            }
            else{
                AIChoice();
            }
        });
    }

    public boolean isValidMove(int x, int y) {
        if (nodesOccupied[x][y] != 1) {
//            System.out.println("MISCARE INTERZISA");
            return false;
        }
        return previousPosition.getRow() == -1 || isAdjacent(x, y, previousPosition.getRow(), previousPosition.getCol());
    }

    public void AIChoice() {
        System.out.println(previousPosition.getRow()+ " " + previousPosition.getCol());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isValidMove(i, j)) {
                    double nodeX = padX + j * cellWidth - halfStone;
                    double nodeY = padY + i * cellHeight - halfStone;
                    previousPosition.setRow(i);
                    previousPosition.setCol(j);
                    System.out.println(previousPosition.getRow()+ " " + previousPosition.getCol());
                    nodesOccupied[i][j] = 3;
                    drawStone(nodeX, nodeY, Color.RED);
                    playerOneTurn = true;
                    return;
                }
            }
        }
    }

    public boolean gameOver() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isValidMove(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isAdjacent(int x, int y, int prevX, int prevY) {
        if (x != prevX && y != prevY)
            return false;
        if (x == prevX) {
            if (y > prevY) {
                int aux = y;
                y = prevY;
                prevY = aux;
            }
            for (int currentY = y; currentY < prevY; currentY++) {
                if (!existXStick(x, currentY, currentY + 1))
                    return false;
            }
        }
        if (y == prevY) {
            if (x > prevX) {
                int aux = x;
                x = prevX;
                prevX = aux;
            }
            for (int currentX = x; currentX < prevX; currentX++) {
                if (!existYStick(y, currentX, currentX + 1))
                    return false;
            }
        }
        return true;
    }

    public boolean existXStick(int x, int y1, int y2) {
        for (Stick s : sticks) {
            if (s.getStart().getRow() == x && s.getStart().getCol() == y1 && s.getEnd().getRow() == x && s.getEnd().getCol() == y2) {
                return true;
            }
        }
        return false;
    }

    public boolean existYStick(int y, int x1, int x2) {
        for (Stick s : sticks) {
            if (s.getStart().getRow() == x1 && s.getStart().getCol() == y && s.getEnd().getRow() == x2 && s.getEnd().getCol() == y) {
                return true;
            }
        }
        return false;
    }

    private void generateSticks() {
        Random random = new Random();
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (random.nextBoolean()) {
                    Position pos1 = new Position(i, j);
                    Position pos2 = new Position(i + 1, j);
                    Stick stick = new Stick(pos1, pos2);
                    sticks.add(stick);
                    nodesOccupied[i][j] = 1;
                    nodesOccupied[i + 1][j] = 1;
                }
                if (random.nextBoolean()) {
                    Position pos1 = new Position(i, j);
                    Position pos2 = new Position(i, j + 1);
                    Stick stick = new Stick(pos1, pos2);
                    sticks.add(stick);
                    nodesOccupied[i][j] = 1;
                    nodesOccupied[i][j + 1] = 1;
                }
            }
        }
    }

    private void drawGrid() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setLineWidth(2.0);
        gc.setStroke(Color.DARKGRAY);
        for (int row = 0; row < rows; row++) {
            double y = padY + row * cellHeight;
            gc.strokeLine(padX, y, padX + boardWidth, y);
        }

        for (int col = 0; col < cols; col++) {
            double x = padX + col * cellWidth;
            gc.strokeLine(x, padY, x, padY + boardHeight);
        }

        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1.0);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = padX + col * cellWidth - halfStone;
                double y = padY + row * cellHeight - halfStone;
                gc.strokeOval(x, y, stoneSize, stoneSize);
            }
        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4.0);
        for (Stick stick : sticks) {
            Position start = stick.getStart();
            Position end = stick.getEnd();
            double startX = padX + start.getCol() * cellWidth;
            double startY = padY + start.getRow() * cellHeight;
            double endX = padX + end.getCol() * cellWidth;
            double endY = padY + end.getRow() * cellHeight;
            gc.strokeLine(startX, startY, endX, endY);
        }
    }

    private void drawStone(double x, double y, Color c) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(c);
        gc.fillOval(x, y, stoneSize, stoneSize);
    }

    public void updateBoardSize(int sizeX, int sizeY) {
        playerOneTurn = true;
        nodesOccupied = new int[sizeX][sizeY];
        sticks = new ArrayList<>();
        previousPosition = new Position(-1, -1);
        this.cols = sizeY;
        this.rows = sizeX;
        generateSticks();
        init();
    }

    public void save() {
        GameState g = new GameState(rows, cols, previousPosition, nodesOccupied, playerOneTurn, sticks);
        try {
            FileOutputStream file = new FileOutputStream("src/save.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(g);
            out.close();
            file.close();
            System.out.println("Game saved");
        } catch (IOException e) {
            System.out.println("IOException from save method" + e.getMessage());
        }
    }

    public void drawStones() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(nodesOccupied[row][col] > 1) {
                    double nodeX = padX + col * cellWidth - halfStone;
                    double nodeY = padY + row * cellHeight - halfStone;
                    if(nodesOccupied[row][col] == 2)
                        drawStone(nodeX, nodeY, Color.BLUE);
                    else drawStone(nodeX, nodeY, Color.RED);
                }
            }
        }
    }

    public void load() {
        GameState g = new GameState(rows, cols, previousPosition, nodesOccupied, playerOneTurn, sticks);
        try {
            FileInputStream file = new FileInputStream("src/save.ser");
            ObjectInput in = new ObjectInputStream(file);
            g = (GameState) in.readObject();
            in.close();
            file.close();
            System.out.println("Game loaded");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception from load: " + e.getMessage());
        }
        this.rows = g.getRows();
        this.cols = g.getCols();
        this.previousPosition = g.getPreviousPosition();
        this.nodesOccupied = g.getNodesOccupied();
        this.playerOneTurn = g.isPlayerOneTurn();
        this.sticks = g.getSticks();
        init();
        drawStones();
    }

    public void exportBoardToPNG() {
        WritableImage writableImage = new WritableImage((int) getWidth(), (int) getHeight());
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.WHITE);

        snapshot(parameters, writableImage);

        File file = new File("src/joc.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("Tabloul de joc a fost exportat ca imagine PNG cu succes.");
        } catch (IOException e) {
            System.out.println("A apărut o eroare la exportarea tabloului de joc ca imagine PNG: " + e.getMessage());
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
