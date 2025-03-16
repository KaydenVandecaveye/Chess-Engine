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

    public Bishop copy() {
        return new Bishop(row, col, isBlack);
    }

    @Override
    public double positionalValue() { // on the diagonal and not too far in the center
        if ((row == col || (row + col) == 7) && col >= 3 && col <= 4 && row >= 3 && row <= 4) {
            return 25;
        }
        return 0;
    }

    @Override
    public boolean canMoveTo(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyDiagonal(this.row, this.col, endRow, endCol));
        }
        return false;
    }

    public int numOfLegalMoves(Board board) {
        int num = 0;

        // up right
        for (int i = row; i < 8; i++) {
            for (int j = col; j < 8; j++) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

        // up left
        for (int i = row; i > 0; i--) {
            for (int j = col; j < 8; j++) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

        // down right
        for (int i = row; i < 8; i++) {
            for (int j = col; j > 0; j--) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

        // down left
        for (int i = row; i > 0; i--) {
            for (int j = col; j > 0; j--) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

        return num;
    }
}



