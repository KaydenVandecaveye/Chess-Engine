package CSCI1933P2;

public class Bishop extends Piece {
    public Bishop(int row, int col, boolean isBlack) {
        super.col = col;
        super.row = row;
        super.isBlack = isBlack;

        if (super.isBlack) {
            super.representation = '\u265D';
        }
        else {
            super.representation = '\u2657';
        }
    }

    @Override
    public boolean canMoveTo(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyDiagonal(this.row, this.col, endRow, endCol));
        }
        return false;
    }
}



