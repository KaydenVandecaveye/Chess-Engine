package CSCI1933P2;

public class Rook extends Piece {
    private boolean hasMoved = false;

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean moved) {
        hasMoved = moved;
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

    public Rook copy() {
        Rook copy = new Rook(row, col, isBlack);
        copy.setHasMoved(hasMoved);
        return copy;
    }

    @Override
    public double positionalValue() {
        return 0;
    }

    public int numOfLegalMoves(Board board) {
        int num = 0;

        // vertical
        for (int j = 0; j < 8; j++) {
            if (col != j && isMoveLegal(board, row, j)) {
                num++;
            }
        }

        // horizontal
        for (int i = 0; i < 8; i++) {
            if (row != i && isMoveLegal(board, i, col)) {
                num++;
            }
        }

        return num;
    }

    @Override
    public boolean canMoveTo(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyHorizontal(this.row, this.col, endRow, endCol) ||
                    board.verifyVertical(this.row, this.col, endRow, endCol));
        }
        return false;
    }

}
