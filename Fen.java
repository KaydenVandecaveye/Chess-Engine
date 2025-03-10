package CSCI1933P2;
/**
 * The Fen class was designed to load an object of type
 * Board with any chess board state given a proper
 * Forsyth-Edwards Notation code. Simply pass a String
 * version of a FEN code and an instance of the Board
 * class to the load function. Example fen codes below.
 * You can find utilities to make FEN codes online, this
 * will help tremendously when debugging.
 *
 * Example FEN codes:
 * empty board: "8/8/8/8/8/8/8/8"
 * starting pos: "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
 * smiley: "8/8/2K2K2/8/2Q2Q2/2Q2Q2/3QQ3/8"
 * "The Immortal Game" ending pos: "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1"
 */
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Fen {
    public static String[] blackPieces = {"♜", "♞", "♝", "♛", "♚", "♟"};
    public static String[] whitePieces = {"♖", "♘", "♗", "♕", "♔", "♙"};

    /**
     * Method used to populate the gui with pieces based on a FEN string.
     * @param fen A valid FEN string
     * @param c ChessGame object to be populated.
     */
    public static void loadGUI(String fen, ChessGame c) {
        int rank = 0;   // Rank or row of the board
        int square = 0; // Square in 'rank'
        char query;     // Temp holder for current char

        c.clearBoard();

        // Begin hashmap solution
        Map<Character, String> map = new HashMap<>();

        map.put('p', blackPieces[5]);
        map.put('P', whitePieces[5]);
        map.put('r', blackPieces[0]);
        map.put('R', whitePieces[0]);
        map.put('n', blackPieces[1]);
        map.put('N', whitePieces[1]);
        map.put('b', blackPieces[2]);
        map.put('B', whitePieces[2]);
        map.put('q', blackPieces[3]);
        map.put('Q', whitePieces[3]);
        map.put('k', blackPieces[4]);
        map.put('K', whitePieces[4]);

        for (int i = 0; i < fen.length(); i++) {
            query = fen.charAt(i);
            if (query == '/') {
                rank++;
                square = 0;
            }
            else if (Character.isDigit(query)) {
                square += Character.getNumericValue(query);
            }
            else {
                String piece = map.get(query);
                if (piece == null) {
                    throw new IllegalArgumentException("Invalid FEN character: " + query);
                }

                c.addPiece(rank,square,piece);
                square++;
            }
        }
        c.revalidate();
        c.repaint();
    }

    /**
     * Method for populating a Board object with chess
     * pieces based on the FEN code passed in.
     *
     * @param fen A FEN code string. Must not include movement commands or erroneous characters
     * @param b A Board object to load with chess position
     */
    public static void load(String fen, Board b) {
        int rank = 0;   // Rank or row of the board
        int square = 0; // Square in 'rank'
        char query;     // Temp holder for current char

        b.clear();

        // Begin hashmap solution
        Map<Character, Function<int[], Piece>> map = new HashMap<>();

        map.put('p', coords -> new Pawn(coords[0], coords[1], true));
        map.put('P', coords -> new Pawn(coords[0], coords[1], false));
        map.put('r', coords -> new Rook(coords[0], coords[1], true));
        map.put('R', coords -> new Rook(coords[0], coords[1], false));
        map.put('n', coords -> new Knight(coords[0], coords[1], true));
        map.put('N', coords -> new Knight(coords[0], coords[1], false));
        map.put('b', coords -> new Bishop(coords[0], coords[1], true));
        map.put('B', coords -> new Bishop(coords[0], coords[1], false));
        map.put('q', coords -> new Queen(coords[0], coords[1], true));
        map.put('Q', coords -> new Queen(coords[0], coords[1], false));
        map.put('k', coords -> new King(coords[0], coords[1], true));
        map.put('K', coords -> new King(coords[0], coords[1], false));

        // Iterate over FEN code chars, updating Board object accordingly
        for (int i = 0; i < fen.length(); i++) {
            query = fen.charAt(i);

            if (query == '/') { // If a '/': proceed to next row
                rank++;
                square = 0;
            } else if (Character.isDigit(query)) { // If a digit, n: proceed n squares forward
                square += Character.getNumericValue(query);
            } else { // Consult the map and create the piece
                Function<int[], Piece> pieceConstructor = map.get(query);
                if (pieceConstructor != null) {
                    Piece newPiece = pieceConstructor.apply(new int[]{rank, square});
                    b.setPiece(rank, square, newPiece);
                    if (Character.isUpperCase(query)) {
                        b.whitePieces.add(newPiece);
                    }
                    else {
                        b.blackPieces.add(newPiece);
                    }
                }
                square++;
            }
        }
    }
}
