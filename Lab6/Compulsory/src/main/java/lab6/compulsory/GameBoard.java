//package lab6.compulsory;
//
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class GameBoard extends Canvas {
//    private int rows;
//    private int cols;
//
//    private int cellWidth, cellHeight;
//    private List<Stick> sticks;
//
//    public GameBoard(int rows, int cols, int cellW, int cellH) {
//
//        this.rows = rows;
//        this.cols = cols;
//        this.cellHeight = cellH;
//        this.cellWidth = cellW;
//        this.sticks = new ArrayList<>();
//        initialize();
//        draw();
//    }
//
//    private void initialize() {
//        Random random = new Random();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (random.nextBoolean()) {
//                    Stick stick = new Stick(new Position(i, j), new Position(i + 1, j));
//                    sticks.add(stick);
//                }
//                if (random.nextBoolean()) {
//                    Stick stick = new Stick(new Position(i, j), new Position(i, j + 1));
//                    sticks.add(stick);
//                }
//            }
//        }
//    }
//
//    private void draw() {
//        GraphicsContext gc = getGraphicsContext2D();
//
//        gc.setStroke(Color.BLUE);
//        for (Stick stick : sticks) {
//            Position start = stick.getStart();
//            Position end = stick.getEnd();
//            double startX = start.getCol() * cellWidth + (double) cellWidth / 2;
//            double startY = start.getRow() * cellHeight + (double) cellHeight / 2;
//            double endX = end.getCol() * cellWidth + (double) cellWidth / 2;
////            double endY = end.getRow() * cellHeight + (double) cellHeight / 2;
//            gc.strokeLine(startX, startY, endX, endY);
//        }
//    }
//
////    public void redraw() {
////        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
////        draw();
////    }
//}
