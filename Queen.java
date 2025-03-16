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

    public Queen copy() {
        return new Queen(row, col, isBlack);
    }

    @Override
    public double positionalValue() {
        return 0;
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
        for (int i = row; i >= 0; i--) {
            for (int j = col; j < 8; j++) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

        // down right
        for (int i = row; i < 8; i++) {
            for (int j = col; j >= 0; j--) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

        // down left
        for (int i = row; i >= 0; i--) {
            for (int j = col; j >= 0; j--) {
                if (i != row && j != col && isMoveLegal(board, i, j)) {
                    num++;
                }
            }
        }

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
                    board.verifyDiagonal(this.row, this.col, endRow, endCol) ||
                    board.verifyVertical(this.row, this.col, endRow, endCol));
        }
        return false;
    }
}




