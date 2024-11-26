package CSCI1933P2;

public class Knight extends Piece {
    public Knight(int row, int col, boolean isBlack) {
        super.col = col;
        super.row = row;
        super.isBlack = isBlack;

        if (super.isBlack) {
            super.representation = '\u265E';
        }
        else {
            super.representation = '\u2658';
        }
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return board.verifyKnight(this.row, this.col, endRow, endCol);
        }
        return false;
    }
}


