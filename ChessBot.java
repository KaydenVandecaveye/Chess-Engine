package CSCI1933P2;

import java.util.*;
import java.util.stream.Collectors;


public class ChessBot {
    Board board;

    public ChessBot(Board board) {
        this.board = board;
    }

    public int[] genMove() {
        double maxEval = Double.NEGATIVE_INFINITY;
        int[] currMove = null;
        HashMap<String, List<int[]>> legalMoves = board.generateLegalMovesByCol(true);

        // debug
//        System.out.println("Num Pieces: " + legalMoves.size());
//        int totalMoves = legalMoves.values().stream().mapToInt(List::size).sum();
//        System.out.println("Total Legal Moves: " + totalMoves);
//        for (Map.Entry<String, List<int[]>> entry : legalMoves.entrySet()) {
//            System.out.println("Piece at " + entry.getKey() + " moves: " +
//                    entry.getValue().stream().map(Arrays::toString).collect(Collectors.joining(", ")));
//        }

        for (Piece p : board.blackPieces) {
            if (p == null || !p.isBlack) {
                System.out.println("GenMove: Error p is null or p isn't black.");
                continue;
            }


            int[] startPos = {p.row, p.col};
            if (board.getPiece(p.row, p.col) == null || !board.getPiece(p.row, p.col).isBlack) {
                System.out.println("GenMove: Error p isn't black.");
                continue;
            }

            String startString = Arrays.toString(startPos); // turns position to a string
            List<int[]> pieceMoves = legalMoves.get(startString); // hash legalMoves to get a given pieces list of legal moves
            if (pieceMoves == null) {
                continue;
            }

            for (int[] pieceMove : pieceMoves) {
                Board copy = board.copy();
                copy.movePiece(startPos[0], startPos[1], pieceMove[0], pieceMove[1], false);
                double eval = minimax(copy, 2, false, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                if (currMove == null) {
                    currMove = new int[] {startPos[0], startPos[1], pieceMove[0], pieceMove[1]};
                }

                if (eval > maxEval) {
                    maxEval = eval;
                    currMove[0] = startPos[0];
                    currMove[1] = startPos[1];
                    currMove[2] = pieceMove[0];
                    currMove[3] = pieceMove[1];
                }
            }
        }
        return currMove;
    }


    public double evalBoard(Board board) {
        double eval = 0;
        for (Piece piece : board.blackPieces) {
            if (piece != null) {
                eval += getPieceVal(piece);
                eval += piece.numOfLegalMoves(board) * 0.25;
            }
        }
        for (Piece piece : board.whitePieces) {
            if (piece != null) {
                eval -= getPieceVal(piece);
                eval -= piece.numOfLegalMoves(board) * 0.25;
            }
        }
        if (board.blackInCheck && !board.blackInCheckmate()) {
            eval -= 25;
        }
        else if (board.whiteInCheck && !board.whiteInCheckmate()) {
            eval += 25;
        }
        else if(board.blackInCheckmate()) {
            return -10000;
        }
        else if(board.whiteInCheckmate()) {
            return 10000;
        }
        else if (board.blackCastled) {
            eval += 75;
        }
        else if (board.whiteCastled) {
            eval -= 75;
        }

        return eval;
    }

    public double getPieceVal(Piece piece) {
        return switch (piece) {
            case King k -> 100000;
            case Queen q -> 900;
            case Rook r -> 500;
            case Bishop b -> 315;
            case Knight k -> 300;
            case Pawn p -> 100;
            default -> 0;
        };
    }

    public double minimax(Board position, int depth, boolean maximizingPlayer, double alpha, double beta) {
        if (depth == 0 || position.isCheckmate()) {
            return evalBoard(position);
        }


        if (maximizingPlayer) { // black pieces
            double maxEval = Double.NEGATIVE_INFINITY;
            HashMap<String, List<int[]>> legalMoves = position.generateAllLegalMoves();
            position.initializePieceArrs();
            for (Piece p : position.blackPieces) {
                int[] startPos = new int[]{p.row, p.col}; // gets piece position ex: {1, 4}


                if (position.getPiece(startPos[0], startPos[1]) == null || !p.isBlack) {
                    System.out.println("MiniMax: Error p is null or p isn't black.");
                    continue;
                }


                String startString = Arrays.toString(startPos); // turns position to a string
                List<int[]> pieceMoves = legalMoves.get(startString); // hash legalMoves to get a given pieces list of legal moves


                if (pieceMoves == null || pieceMoves.isEmpty()) {
                    continue;
                }


//                pieceMoves.sort((a, b) -> {
//                    double evalA = moveHeuristic(position, startPos, a);
//                    double evalB = moveHeuristic(position, startPos, b);
//                    return Double.compare(evalB, evalA);
//                });


//                int movesToConsider = 0;
//                switch (p) {
//                    case King k -> movesToConsider = 3;
//                    case Queen q -> movesToConsider = 8;
//                    case Rook r -> movesToConsider = 6;
//                    case Bishop b -> movesToConsider = 6;
//                    case Knight n -> movesToConsider = 4;
//                    case Pawn pawn -> movesToConsider = 3;
//                    default -> System.out.println("Unknown piece type");
//                }
//
//                if (pieceMoves.size() > movesToConsider) {
//                    pieceMoves = pieceMoves.subList(0, movesToConsider);
//                }


                for (int[] pieceMove : pieceMoves) {
                    Board copy = position.copy();
                    copy.movePiece(startPos[0], startPos[1], pieceMove[0], pieceMove[1], false);
                    double eval = minimax(copy, depth - 1, false, alpha, beta);
                    // Move lastMove = position.moveHist.pop();
                    // position.undoMove(lastMove);


                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, maxEval);
                    if (beta <= alpha) {
                        return maxEval;
                    }
                }
            }
            return maxEval;
        }
        else { // white pieces
            double minEval = Double.POSITIVE_INFINITY;
            HashMap<String, List<int[]>> legalMoves = position.generateAllLegalMoves();
            position.initializePieceArrs();
            for (Piece p : position.whitePieces) {
                int[] startPos = new int[]{p.row, p.col}; // gets piece position ex: {1, 4}


                if (position.getPiece(startPos[0], startPos[1]) == null) {
                    System.out.println("MiniMax: Error - getPiece returned null for " + Arrays.toString(startPos));
                    continue;
                }


                String startString = Arrays.toString(startPos); // turns position to a string
                List<int[]> pieceMoves = legalMoves.get(startString); // hash legalMoves to get a given pieces list of legal moves


                if (pieceMoves == null || pieceMoves.isEmpty()) {
                    continue;
                }


//                pieceMoves.sort((a, b) -> {
//                    double evalA = moveHeuristic(position, startPos, a);
//                    double evalB = moveHeuristic(position, startPos, b);
//                    return Double.compare(evalA, evalB);
//                });


//                int movesToConsider = 0;
//                switch (p) {
//                    case King k -> movesToConsider = 3;
//                    case Queen q -> movesToConsider = 8;
//                    case Rook r -> movesToConsider = 6;
//                    case Bishop b -> movesToConsider = 6;
//                    case Knight n -> movesToConsider = 4;
//                    case Pawn pawn -> movesToConsider = 3;
//                    default -> System.out.println("Unknown piece type");
//                }
//
//                if (pieceMoves.size() > movesToConsider) {
//                    pieceMoves = pieceMoves.subList(0, movesToConsider);
//                }


                for (int[] pieceMove : pieceMoves) {
                    Board copy = position.copy();
                    copy.movePiece(startPos[0], startPos[1], pieceMove[0], pieceMove[1], false);
                    double eval = minimax(copy, depth - 1, false, alpha, beta);


                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, minEval);
                    if (beta <= alpha) {
                        return minEval;
                    }
                }
            }
            return minEval;
        }
    }


}
