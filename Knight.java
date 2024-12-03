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

    @Override
    public boolean canMoveTo(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            return (board.verifyKnight(this.row, this.col, endRow, endCol));
        }
        return false;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int startRow = row;
        int startCol = col;
        Piece capturedPiece = board.board[endRow][endCol]; // backup captured piece
        if (!canMoveTo(board,endRow,endCol)) {
            return false;
        }
        //simulate move
        board.movePiece(row,col,endRow,endCol);
        board.checkOnBoard();

        // invalid move, leaves black in check
        if (isBlack && board.blackInCheck) {
            board.movePiece(endRow,endCol,startRow,startCol);
            if (capturedPiece != null) {
                board.setPiece(endRow,endCol,capturedPiece);
            }
            return false;
        }
        // invalid move, leaves white in check
        if (!isBlack && board.whiteInCheck) {
            board.movePiece(endRow,endCol,startRow,startCol);
            if (capturedPiece != null) {
                board.setPiece(endRow,endCol,capturedPiece);
            }
            return false;
        }
        board.movePiece(endRow,endCol,startRow,startCol);
        if (capturedPiece != null) {
            board.setPiece(endRow,endCol,capturedPiece);
        }
        return true;
    }
}


