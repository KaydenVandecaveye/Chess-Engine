package CSCI1933P2;

public class Board {
    // Instance variables (add more if you need)
    public final Piece[][] board;

    // store king positions for faster lookups (used for testing if a king is in check)
    private int[] blackKingPos = {0, 4}; // (row, col)
    private int[] whiteKingPos = {7, 4};// (row, col)

    boolean whiteInCheck = false; // white K under attack
    boolean blackInCheck = false; // black K under attack

    //default constructor
    public Board() {
        this.board = new Piece[8][8]; // initialize the board to chessboard dimensions.
    }
    public int[] getBlackKingPos() {
        return blackKingPos;
    }
    public int[] getWhiteKingPos() {
        return whiteKingPos;
    }
    public void setBlackKingPos(int row, int col) {
        blackKingPos[0] = row;
        blackKingPos[1] = col;
    }
    public void setWhiteKingPos(int row, int col) {
        whiteKingPos[0] = row;
        whiteKingPos[1] = col;
    }

    // Accessor Methods
    /**
     * Gets the piece at a particular row and column of the board.
     * @param row       The row of the piece to be accessed.
     * @param col       The column of the piece to be accessed.
     * @return          The piece at the specified row and column of the board.
     */
    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }
    /**
     * Sets the piece at a particular row and column of the board.
     * @param row       The row to place the piece at.
     * @param col       The column to place the piece at.
     * @param piece     The piece to place at the specified row and column.
     */
    public void setPiece(int row, int col, Piece piece) {
        piece.col = col;
        piece.row = row;
        board[row][col] = piece;
    }

    // determines if the given piece is putting the king of the other color in check
    // updates blackInCheck & whiteInCheck fields if a given piece puts a king in check
    public boolean isCheck(Piece piece) {
        // black piece block
        if (piece != null && piece.isBlack) {
            int king_row = whiteKingPos[0];
            int king_col = whiteKingPos[1];
            // legal move from black piece to white K
            if (piece.canMoveTo(this, king_row, king_col)) {
                whiteInCheck = true;
                return true;
            }
            else {
                whiteInCheck = false;
            }
        }
        // white piece block
        else if (piece != null) {
            int king_row = blackKingPos[0];
            int king_col = blackKingPos[1];
            // legal move from white piece to black K
            if (piece.canMoveTo(this, king_row, king_col)) {
                blackInCheck = true;
                return true;
            }
            else {
                blackInCheck = false;
            }
        }
        return false;
    }

    // determines if there is a check on the board
    public void checkOnBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.isCheck(board[i][j])) {
                    return;
                }
            }
        }
        whiteInCheck = false;
        blackInCheck = false;
    }

    // Movement helper functions
    /**
     * Verifies that the source and destination of a move are valid by performing the following checks:
     *  1. ALL rows and columns provided must be >= 0.
     *  2. ALL rows and columns provided must be < 8.
     *  3. The start position of the move must contain a piece.
     *  4. The piece at the starting position must be the correct color.
     *  5. The destination must be empty OR must contain a piece of the opposite color.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @param isBlack   The expected color of the starting piece.
     * @return True if the above conditions are met, false otherwise.
     */
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // verify rows and columns are correct inputs
        if ((startRow >= 0 && startRow < 8) && (endRow >= 0 && endRow < 8) &&
                (startCol >= 0 && startCol < 8) && (endCol >= 0 && endCol < 8)) {
            // verify there is a piece at the given starting position
            if (getPiece(startRow,startCol) != null) {
                // verify piece at starting position is correct color
                if ((board[startRow][startCol]).isBlack == isBlack) {
                    // verify destination is of opposite color or is empty
                    return ((board[endRow][endCol] == null)) || (board[endRow][endCol]).isBlack != isBlack;
                }
            }
        }
        return false;
    }

    /**
     * Verifies that the source and destination of a move are adjacent squares (within 1 square of each other)
     * Example, Piece P is adjacent to the spots marked X:
     * OOOOO
     * OXXXO
     * OXPXO
     * OXXXO
     * OOOOO
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if the source and destination squares are adjacent, false otherwise.
     */

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow == endRow) && (startCol == endCol)) {
            return true;
        }
        else {
            return ((startRow - 1 <= endRow && endRow <= startRow + 1) && (startCol - 1 <= endCol && endCol <= startCol + 1));
        }
    }

    /**
     * Verifies that a source and destination are in the same row and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same row with no pieces between them, false otherwise.
     */
    // y = y and x != x
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow == endRow) && (startCol == endCol)) {
            return true;
        }
        if((this.board[startCol] != this.board[endCol]) && (this.board[endRow] == this.board[startRow])) {
            //check for obstructions in pieces path
            if (startCol < endCol) {
                for (int i = startCol + 1;i < endCol; i++) {
                    if (this.board[endRow][i] != null) {
                        return false;
                    }
                }
                return true;
            }
            else if (startCol > endCol) {
                for (int i = endCol + 1;i < startCol; i++) {
                    if (this.board[endRow][i] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies that a source and destination are in the same column and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same column with no pieces between them, false otherwise.
     */
    // y != y and x = x
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow == endRow) && (startCol == endCol)) {
            return true;
        }
        if((this.board[startCol] == this.board[endCol]) && (this.board[endRow] != this.board[startRow])) {
            // check for obstructions in pieces path
            if (startRow < endRow) {
                for (int i = startRow + 1;i < endRow; i++) {
                    if (this.board[i][startCol] != null) {
                        return false;
                    }
                }
                return true;
            }
            else if (startRow > endRow) {
                for (int i = endRow + 1;i < startRow; i++) {
                    if (this.board[i][startCol] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies that a source and destination are on the same diagonal and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are on the same diagonal with no pieces between them, false otherwise.
     */
    // abs(delta x) = abs(delta y)
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow == endRow) && (startCol == endCol)) {
            return true;
        }
        if (Math.abs(endRow - startRow) == Math.abs(endCol - startCol)) {
            // up right direction
            if ((endRow > startRow) && (endCol > startCol)) {
                startRow ++;
                startCol ++;
                // iterate through pieces path
                while (startRow < endRow && startCol < endCol) {
                    if (this.board[startRow][startCol] != null) {
                        return false;
                    }
                    startRow ++;
                    startCol ++;
                }
                return true;
            }
            // up left direction
            else if ((endRow < startRow) && (endCol > startCol)) {
                // iterate through pieces path
                startRow --;
                startCol ++;
                while (startRow > endRow && startCol < endCol) {
                    if (this.board[startRow][startCol] != null) {
                        return false;
                    }
                    startRow --;
                    startCol ++;
                }
                return true;
            }
            // down right direction
            else if ((endRow > startRow) && (endCol < startCol)) {
                // iterate through pieces path
                startRow ++;
                startCol --;
                while (startRow < endRow && startCol > endCol) {
                    if (this.board[startRow][startCol] != null) {
                        return false;
                    }
                    startRow ++;
                    startCol --;
                }
                return true;
            }
            // down left direction
            else if ((endRow < startRow) && (endCol < startCol)) {
                // iterate through pieces path
                startRow --;
                startCol --;
                while (startRow > endRow && startCol > endCol) {
                    if (this.board[startRow][startCol] != null) {
                        return false;
                    }
                    startRow --;
                    startCol --;
                }
                return true;
            }
        }
        return false;
    }

    public boolean verifyKnight(int startRow, int startCol, int endRow, int endCol) {
        // vertically "branching" knight moves (ex: down 2 left 1 or up 2 right 1)
        if ((startCol == endCol + 2) || (startCol == endCol - 2)) {
            return (startRow == endRow + 1) || (startRow == endRow - 1);
        }
        // horizontally "branching" knight moves (ex: right 2 down 1 or left 2 up 1)
        else if ((startRow == endRow + 2) || (startRow == endRow - 2)) {
            return (startCol == endCol + 1 || (startCol == endCol - 1));
        }
        return false;
    }

    // Game functionality methods
    /**
     * Moves the piece from startRow, startCol to endRow, endCol if it is legal to do so.
     * IMPORTANT: Make sure to update the internal position of the piece, and the starting position of the piece to null!
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @param sim   T or F for if the move is a simulated move or not. (Used so pawns aren't promoted when checking if a move leaves player in check)
     */
    public void movePiece(int startRow, int startCol, int endRow, int endCol, boolean sim) {
        if (board[startRow][startCol] != null) {
                Piece piece = board[startRow][startCol];

                if (piece instanceof Rook && !sim) {
                    ((Rook) piece).setHasMoved(true);
                }

                // if king is castling
                if (piece instanceof King && Math.abs(endCol - startCol) == 2) {
                    ((King) piece).performCastling(this,endRow,endCol);

                    if (piece.isBlack) {
                        setBlackKingPos(endRow, endCol);
                    }
                    else {
                        setWhiteKingPos(endRow, endCol);
                    }
                }

                // pawn promotion
                if (piece instanceof Pawn && piece.isBlack && endRow == 7 && !sim) {
                    piece = ((Pawn) piece).promotePawn(this,startRow,startCol,true);

                    board[endRow][endCol] = piece;
                    board[startRow][startCol] = null;
                    piece.setPosition(endRow, endCol);
                }
                else if (piece instanceof Pawn && !piece.isBlack && endRow == 0 && !sim) {
                    piece = ((Pawn) piece).promotePawn(this,startRow,startCol,false);

                    board[endRow][endCol] = piece;
                    board[startRow][startCol] = null;
                    piece.setPosition(endRow, endCol);
                }

                // regular move block
                else {
                    board[endRow][endCol] = piece;
                    board[startRow][startCol] = null;
                    piece.setPosition(endRow, endCol);

                    }
                }
        }


    /**
     * legalMoveOnBoard() helper function.
     * counts the # of legal moves a given piece has.
     * @param piece given piece.
     * @return # of legal moves.
     */
    public int countLegalMoves (Piece piece) {
        int count = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                if((i != piece.row || j != piece.col) && piece.isMoveLegal(this,i,j)){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Used to check for checkmate. Checks if there is a legal move for the given color. (T for Black, F for White)
     * @param isBlack given color.
     * @return Returns true if there is a legal move for the color inputted.
     */
    public boolean legalMoveOnBoard(boolean isBlack) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].isBlack == isBlack) {
                    if (countLegalMoves(board[i][j]) > 0) { // legal move available
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if a given color is in check and no legal moves block / evade check.
     * @return If the game is in a game over state / checkmate.
     */
    public boolean isGameOver() {
        if (whiteInCheck) {
            return !legalMoveOnBoard(false);
        }
        else if (blackInCheck) {
            return !legalMoveOnBoard(true);
        }
        return false;
    }

    /**
     * Sets all indexes in the board to null
     */
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = null;
            }
        }
    }

    public void display() {
        System.out.print("\t\t\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("|\t" + board[i][j] + "\t");
                }
            }
            System.out.print("|\n");
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    // checks if the current square can be attacked by an opposing piece
    public boolean isSquareUnderAttack(int row, int col, boolean isBlack) {
        // Check for threats from all pieces (both black and white)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPiece(i, j) != null) {
                    Piece piece = getPiece(i, j); // get the piece at the current square
                    if (piece.getIsBlack() != isBlack) {  // Opposing color
                        if (piece.canMoveTo(this, row, col)) {
                            return true;  // The square is under attack
                        }
                    }
                }
            }
        }
        return false;  // The square is not under attack
    }
}