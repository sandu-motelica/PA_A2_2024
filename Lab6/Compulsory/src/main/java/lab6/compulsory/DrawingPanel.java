package lab6.compulsory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    private List<Stick> sticks = new ArrayList<>();

    public DrawingPanel(int sizeX, int sizeY) {
        init(sizeX, sizeY);
    }

    final void init(int sizeX, int sizeY) {
        this.rows = sizeX;
        this.cols = sizeY;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        System.out.println(cellWidth + " " + cellHeight);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setWidth(canvasWidth);
        setHeight(canvasHeight);
        generateSticks();
        drawGrid();

        this.setOnMousePressed(e -> {
            double x = e.getX();
            double y = e.getY();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    double nodeX = padX + col * cellWidth - halfStone;
                    double nodeY = padY + row * cellHeight - halfStone;
                    if (x > nodeX && x < nodeX + stoneSize && y > nodeY && y < nodeY + stoneSize) {
                        if(playerOneTurn) {
                            drawStone(nodeX, nodeY, Color.BLUE);
                            playerOneTurn = false;
                        }
                        else{
                            drawStone(nodeX, nodeY, Color.RED);
                            playerOneTurn = true;
                        }

                        return;
                    }
                }
            }
        });
    }

    private void generateSticks() {
        Random random = new Random();
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (random.nextBoolean()) {
                    Stick stick = new Stick(new Position(i, j), new Position(i + 1, j));
                    sticks.add(stick);
                }
                if (random.nextBoolean()) {
                    Stick stick = new Stick(new Position(i, j), new Position(i, j + 1));
                    sticks.add(stick);
                }
            }
        }
    }

    private void drawGrid() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

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
        init(sizeX, sizeY);
    }
}
