package CSCI1933P2;

public class Rook extends Piece {
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

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int startRow = row;
        int startCol = col;
        if (!canMoveTo(board,endRow,endCol)) {
            return false;
        }
        //simulate move
        board.movePiece(row,col,endRow,endCol);
        board.checkOnBoard();

        // invalid move, leaves black in check
        if (isBlack && board.blackInCheck) {
            board.movePiece(endRow,endCol,startRow,startCol);
            return false;
        }
        // invalid move, leaves white in check
        if (!isBlack && board.whiteInCheck) {
            board.movePiece(endRow,endCol,startRow,startCol);
            return false;
        }
        board.movePiece(endRow,endCol,startRow,startCol);
        return true;
    }
}

