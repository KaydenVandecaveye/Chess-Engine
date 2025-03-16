package CSCI1933P2;

import java.util.ArrayList;

public abstract class Piece {

    // Piece object's internal row position
    public int row;

    // Piece object's internal col position
    public int col;

    // Boolean representing Piece object's color (white/black)
    protected boolean isBlack;

    // Unicode character representing the piece
    protected char representation;

    public int[] getPosition() {
        return new int[] {row, col};
    }

    public abstract double positionalValue();

    public abstract Piece copy();

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (this instanceof King && ((King) this).canCastle(board,endRow, endCol)) {
            return true;
        }

        board.checkOnBoard();
        boolean sim = true;
        int startRow = row;
        int startCol = col;

        Piece capturedPiece = board.board[endRow][endCol]; // backup captured piece
        if (!canMoveTo(board,endRow,endCol)) {
            return false;
        }
        //simulate move
        board.movePiece(row,col,endRow,endCol,sim);
        board.checkOnBoard();

        // invalid move, leaves black in check
        if (isBlack && board.blackInCheck) {
            board.movePiece(endRow,endCol,startRow,startCol,sim);
            if (capturedPiece != null) {
                board.setPiece(endRow,endCol,capturedPiece);
            }
            return false;
        }
        // invalid move, leaves white in check
        if (!isBlack && board.whiteInCheck) {
            board.movePiece(endRow,endCol,startRow,startCol,sim);
            if (capturedPiece != null) {
                board.setPiece(endRow,endCol,capturedPiece);
            }
            return false;
        }
        board.movePiece(endRow,endCol,startRow,startCol,sim);
        if (capturedPiece != null) {
            board.setPiece(endRow,endCol,capturedPiece);
        }
        return true;
    }

    /**
     * Checks if it is legally within a pieces movement pattern to move to the destination,
     * Does not deal with check rules.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public abstract boolean canMoveTo(Board board, int endRow, int endCol);

    /**
     * Sets the internal position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * Return the position of the piece.
     * @return  The row of the piece.
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Return the position of the piece.
     * @return  The col of the piece.
     */
    public int getCol(){
        return this.col;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack(){
        return isBlack;
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return representation + "";
    }

    public ArrayList<int[]> generateLegalMoves(Board board) {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((row != i || j != col) && isMoveLegal(board,i,j)) {
                    int[] move = new int[] {i, j};
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    public abstract int numOfLegalMoves(Board board);
}
