package CSCI1933P2;

import java.util.*;

public class ChessBot {
    Board board;

    public ChessBot(Board board) {
        this.board = board;
    }

    public int[] genMove() {
        double bestEval = Double.NEGATIVE_INFINITY;
        int[] currMove = new int[] {0,0,0,0};

        HashMap<String, List<int[]>> legalMoves = board.generateAllLegalMoves();
        for (int i = 0; i < board.blackPieces.size(); i++) {
            if (board.blackPieces.get(i) != null) {
                if (!board.blackPieces.get(i).isBlack) {
                    continue;
                }
                int[] startPos = new int[]{board.blackPieces.get(i).row, board.blackPieces.get(i).col}; // gets piece position ex: {1, 4}
                String startString = Arrays.toString(startPos); // turns position to a string
                List<int[]> pieceMoves = legalMoves.get(startString); // hash legalMoves to get a given pieces list of legal moves

                if (pieceMoves == null || pieceMoves.isEmpty()) {
                    continue;
                }

                for (int[] pieceMove : pieceMoves) {
                    double evaluation = evaluate(startPos[0], startPos[1], pieceMove[0], pieceMove[1]);
                    if (evaluation > bestEval) {
                        bestEval = evaluation;
                        currMove[0] = startPos[0];
                        currMove[1] = startPos[1];
                        currMove[2] = pieceMove[0];
                        currMove[3] = pieceMove[1];
                    }
                }
            }
        }
        return currMove;
    }

    public double evaluate(int startRow, int startCol, int endRow, int endCol) {
        Random random = new Random();
        return random.nextDouble(100) + 1;
    }
}
