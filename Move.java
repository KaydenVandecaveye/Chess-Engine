package CSCI1933P2;
import java.util.ArrayList;

public class Move { // stores the data of board state after a move has been made

    int startRow, startCol, endRow, endCol;
    Piece capturedPiece, movedPiece;
    boolean blackKingMoved, whiteKingMoved, whiteKingCastled, blackKingCastled, blackInCheck, whiteInCheck;
    ArrayList<Piece> blackPieces, whitePieces;
    int[] whiteKingPos, blackKingPos;

    public Move(int startRow, int startCol, int endRow, int endCol, Piece capturedPiece, Piece movedPiece,
                boolean blackKingMoved, boolean whiteKingMoved, boolean whiteKingCastled, boolean blackKingCastled,
                boolean blackInCheck, boolean whiteInCheck, ArrayList<Piece> blackPieces, ArrayList<Piece> whitePieces,
                int[] whiteKingPos, int[] blackKingPos) {

        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.capturedPiece = capturedPiece;
        this.movedPiece = movedPiece;
        this.blackKingMoved = blackKingMoved;
        this.whiteKingMoved = whiteKingMoved;
        this.whiteKingCastled = whiteKingCastled;
        this.blackKingCastled = blackKingCastled;
        this.blackInCheck = blackInCheck;
        this.whiteInCheck = whiteInCheck;
        this.blackPieces = new ArrayList<>(blackPieces);
        this.whitePieces = new ArrayList<>(whitePieces);
        this.whiteKingPos = whiteKingPos.clone();
        this.blackKingPos = blackKingPos.clone();
    }

    public String toString() {
        return "\nMove information:\n" +
                "Start pos & Start Piece: (" + startRow + "," + startCol + ") " + movedPiece + "\n" +
                "End pos & End Piece: (" + endRow + "," + endCol + ") " + capturedPiece + "\n" +
                "Black king fields: Pos: (" + blackKingPos[0] + "," + blackKingPos[1] + ") Has moved: " + blackKingMoved + "\n" +
                "White king fields: Pos: (" + whiteKingPos[0] + "," + whiteKingPos[1] + ") Has moved: " + whiteKingMoved + "\n" +
                "Castling fields: Black: " + blackKingCastled + " White: " + whiteKingCastled + "\n" +
                "Check fields: Black: " + blackInCheck + " White: " + whiteInCheck + "\n" +
                "Piece array size: Black: " + blackPieces.size() + " White: " + whitePieces.size() + "\n";

    }
}
