package CSCI1933P2;

public class Rook extends Piece {

    private boolean hasMoved = false;
    public boolean hasMoved() {
        return hasMoved;
    }

    public Rook(int row, int col, boolean isBlack) {
        super.col = col;
        super.row = row;
        super.isBlack = isBlack;

        if (super.isBlack) {
            super.representation = '\u265C';
        }
        else {
            super.representation = '\u2656';
        }
    }

    @Override
    public boolean canMoveTo(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyHorizontal(this.row, this.col, endRow, endCol) ||
                    board.verifyVertical(this.row, this.col, endRow, endCol));
        }
        return false;
    }


    public void moveTo(Board board, int newRow, int newCol){
        // Update position
        this.row = newRow;
        this.col = newCol;
        this.hasMoved = true;
    }
}
