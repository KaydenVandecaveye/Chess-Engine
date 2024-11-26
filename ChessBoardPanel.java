package CSCI1933P2;
import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel{
    private final int SIZE = 8;
    // 2d string representation of chess board
    private final String[][] board = {
            {"\u265C", "\u265E", "\u265D", "\u265B", "\u265A", "\u265D", "\u265E", "\u265C"}, // Black pieces
            {"\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F"}, // Black pawns
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {"\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659"}, // White pawns
            {"\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2657", "\u2658", "\u2656"}  // White pieces
    };

    public ChessBoardPanel(){
        setPreferredSize(new Dimension(600, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChessBoard(g);
        drawPieces(g);
    }

    private void drawChessBoard(Graphics g) {
        int squareSize = getWidth() / SIZE; // Calculate the size of each square
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Alternate colors based on the position
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                // Draw each square
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
            }
        }
    }
    private void drawPieces(Graphics g) {
        int squareSize = getWidth() / SIZE;
        g.setFont(new Font("Arial", Font.PLAIN, 70));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String piece = board[row][col];
                if (!piece.equals(" ")) {
                    g.setColor(Color.BLACK);
                    g.drawString(piece, col * squareSize + squareSize / 4, row * squareSize + (int)(squareSize * 0.75));
                }
            }
        }
    }
    // moves pieces on panel object
    public void movePiece(int startRow,int startCol,int endRow,int endCol) {
        this.board[endRow][endCol] = this.board[startRow][startCol];
        this.board[startRow][startCol] = " ";
        this.repaint();
    }
}

