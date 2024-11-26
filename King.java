package CSCI1933P2;

public class King extends Piece {
    public King(int row, int col, boolean isBlack) {
        super.col = col;
        super.row = row;
        super.isBlack = isBlack;

        if (super.isBlack) {
            super.representation = '\u265A';
        }
        else {
            super.representation = '\u2654';
        }
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return board.verifyAdjacent(this.row, this.col, endRow, endCol);
        }
        return false;
    }
}
