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

    public Knight copy() {
        return new Knight(row, col, isBlack);
    }

    @Override
    public double positionalValue() { // encourage movement to the center
        if (row <= 5 && row >= 2 && col <= 5 && col >= 2) {
            return 50;
        }
        return 0;
    }

    public int numOfLegalMoves(Board board) {
        int num = 0;

        int[][] directions = {
                {-1,2}, {1,2}, // up
                {-2, 1}, {-2, -1}, // left
                {-1, -2}, {1, -2}, // down
                {-2,1}, {-2, -1} // right
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
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyKnight(this.row, this.col, endRow, endCol));
        }
        return false;
    }
}


