package lab6.compulsory;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingPanel extends Canvas {
    int rows, cols;
    int canvasWidth = 440, canvasHeight = 440;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;


    public DrawingPanel(int sizeX, int sizeY) {
        init(sizeX,sizeY);
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
        drawGrid();
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
                double x = padX + col * cellWidth - stoneSize / 2;
                double y = padY + row * cellHeight - stoneSize / 2;
                gc.strokeOval(x, y, stoneSize, stoneSize);
            }
        }
    }
    public void updateBoardSize(int sizeX, int sizeY) {
        init(sizeX, sizeY);
    }
}
