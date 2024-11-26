package CSCI1933P2;

public class Queen extends Piece {
    public Queen(int row, int col, boolean isBlack) {
        super.col = col;
        super.row = row;
        super.isBlack = isBlack;

        if (super.isBlack) {
            super.representation = '\u265B';
        }
        else {
            super.representation = '\u2655';
        }
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyDiagonal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol));
        }
        return false;
    }
}



