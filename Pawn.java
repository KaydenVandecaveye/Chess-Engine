package CSCI1933P2;
import java.util.Scanner;
public class Pawn extends Piece {
    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Pawn(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the Pawn object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack){
            // Black Pawn
            super.representation = '\u265F';
        }
        else{
            // White Pawn
            super.representation = '\u2659';
        }
    }

    public Pawn copy() {
        return new Pawn(row, col, isBlack);
    }

    @Override
    public double positionalValue() {
        double eval = 0;
        if (row >= 2 && row <= 4) { // reward central pawn moves
            eval += 25;
            if (col >= 3 && col <= 4) {
                eval += 25;
            }
        }
        return eval;
    }


    /**
     * Handle promotion of a pawn.
     * @param board Board instance
     * @param row Current row of the pawn
     * @param col Current col of the pawn
     * @param isBlack Color of the pawn
     */
    public Piece promotePawn(Board board, int row, int col, boolean isBlack) {
        return new Queen(row,col,isBlack);
    }

    public int numOfLegalMoves(Board board) {
        int num = 0;

        int[][] directions = {
                {0, 1}, {0, 2}, {1, 1}, {-1, 1}
        };
        for (int [] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if ((newRow <= 7  && newRow >= 0) && (newCol <= 7  && newCol >= 0) && isMoveLegal(board, newRow, newCol)) {
                num++;
            }
        }

        return num;
    }

    @Override
    public boolean canMoveTo(Board board, int endRow, int endCol) {
        if (board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Case 1: Forward movement to empty square.
            // Determine if the distance being moved is valid.
            if (this.isBlack) {
                return (endRow == this.row + 1) || ((endRow == this.row + 2) && (this.row == 1));
            } else {
                return (endRow == this.row - 1) || ((endRow == this.row - 2) && (this.row == 6));
            }
        }

        else if (this.col == endCol+1 || this.col == endCol-1) {
            // Case 2: Capturing a piece.
            if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                // There is a piece of the opposite color to be captured.
                if (this.isBlack) {
                    return (endRow == this.row + 1);
                } else {
                    return (endRow == this.row - 1);
                }
            } else {
                return false;
            }
        }
        else {
            // Case 4: Moving in a non-adjacent column. (illegal move)
            return false;
        }
    }
}
