package CSCI1933P2;

import java.util.Arrays;

public abstract class Piece {

    // Piece object's internal row position
    protected int row;

    // Piece object's internal col position
    protected int col;

    // Boolean representing Piece object's color (white/black)
    protected boolean isBlack;

    // Unicode character representing the piece
    protected char representation;

    /**
     * Checks if a move to a destination square is legal. (canMoveTo() method)
     * Includes check rules from the isCheck() & checkOnBoard() methods.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public abstract boolean isMoveLegal(Board board, int endRow, int endCol);

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

    public int[][] generateLegalMoves(Board board) {
        int[][] moves = new int[27][2];
        int movesIdx = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((row != i || j != col) && isMoveLegal(board,i,j)) {
                    moves[movesIdx] = new int[] {i, j};
                    movesIdx++;
                }
            }
        }
        return Arrays.copyOf(moves,movesIdx);
    }
}
