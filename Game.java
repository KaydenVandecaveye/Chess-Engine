package CSCI1933P2;
import java.util.Arrays;
import java.util.Scanner;
public class Game {
  public static void main(String[] args) {
    Board board = new Board();
    Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);

    // colorToMove keeps track of whose turn it is to move (T for white, F for black)
    boolean colorToMove = true;
    Scanner s = new Scanner(System.in);

    while (!board.isGameOver()) {
      System.out.println("\n" + board.toString());
      if (colorToMove) {
        board.checkOnBoard();
        System.out.println("White King pos" + Arrays.toString(board.getWhiteKingPos()));
        System.out.println("Black in Check:" + board.blackInCheck);
        System.out.println("White in Check:" + board.whiteInCheck);
        // white to move code block
        System.out.println("""          
                White to move:\
                
                Enter move in terms of Row Column EX: StartRow StartCol EndRow EndCol\s
                """);
        String input = s.nextLine();
        String[] inputs = input.split(" ");
        int startRow = Integer.parseInt(inputs[0]);
        int startCol = Integer.parseInt(inputs[1]);
        int endRow = Integer.parseInt(inputs[2]);
        int endCol = Integer.parseInt(inputs[3]);

        // ensure piece being moved is correct color
        while (board.getPiece(startRow, startCol) == null || board.getPiece(startRow, startCol).isBlack) {
          System.out.println("Invalid Move, Try again.");
          System.out.println("White to move. \n");
          input = s.nextLine();
          inputs = input.split(" ");
          startRow = Integer.parseInt(inputs[0]);
          startCol = Integer.parseInt(inputs[1]);
          endRow = Integer.parseInt(inputs[2]);
          endCol = Integer.parseInt(inputs[3]);
        }

        // ensure move is legal
        while (colorToMove) {
          if (!board.board[startRow][startCol].isMoveLegal(board, endRow, endCol)) {
            System.out.println("""
                    Incorrect Input Try again:
                    White to move:\
                    
                    Enter move in terms of Row Column EX:StartRow StartCol EndRow EndCol\s
                    """);
            input = s.nextLine();
            inputs = input.split(" ");
            startRow = Integer.parseInt(inputs[0]);
            startCol = Integer.parseInt(inputs[1]);
            endRow = Integer.parseInt(inputs[2]);
            endCol = Integer.parseInt(inputs[3]);
          } else {
            System.out.println(Arrays.deepToString(board.getPiece(startRow, startCol).generateLegalMoves(board)));
            board.movePiece(startRow, startCol, endRow, endCol);
            colorToMove = false;
          }
        }
      } else {
        board.checkOnBoard();
        System.out.println("Black King pos" + Arrays.toString(board.getBlackKingPos()));
        System.out.println("Black in Check:" + board.blackInCheck);
        System.out.println("White in Check:" + board.whiteInCheck);
        // black to move code block
        System.out.println("""
                Black to move:\
                
                Enter move in terms of Row Column EX:StartRow StartCol EndRow EndCol\s
                """);
        String input = s.nextLine();
        String[] inputs = input.split(" ");
        int startRow = Integer.parseInt(inputs[0]);
        int startCol = Integer.parseInt(inputs[1]);
        int endRow = Integer.parseInt(inputs[2]);
        int endCol = Integer.parseInt(inputs[3]);

        // ensure piece being moved is correct color
        while (board.getPiece(startRow, startCol) == null || !board.getPiece(startRow, startCol).isBlack) {
          System.out.println("Invalid Move, Try again.");
          System.out.println("Black to move. \n");
          input = s.nextLine();
          inputs = input.split(" ");
          startRow = Integer.parseInt(inputs[0]);
          startCol = Integer.parseInt(inputs[1]);
          endRow = Integer.parseInt(inputs[2]);
          endCol = Integer.parseInt(inputs[3]);
        }

        // ensure move is legal
        while (!colorToMove) {
          if (!board.board[startRow][startCol].isMoveLegal(board, endRow, endCol)) {
            System.out.println("""
                    Incorrect Input Try again:
                    Black to move:\
                    
                    Enter move in terms of Row Column EX:StartRow StartCol EndRow EndCol\s
                    """);
            input = s.nextLine();
            inputs = input.split(" ");
            startRow = Integer.parseInt(inputs[0]);
            startCol = Integer.parseInt(inputs[1]);
            endRow = Integer.parseInt(inputs[2]);
            endCol = Integer.parseInt(inputs[3]);
          } else {
            board.movePiece(startRow, startCol, endRow, endCol);
            colorToMove = true;
          }
        }
      }
    }
    boolean whiteKing = true;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board.getPiece(i, j) != null) {
          if (board.getPiece(i, j) instanceof King && board.getPiece(i, j).isBlack) {
            whiteKing = false;
          }
        }
      }
    }
        System.out.println(board.toString());
        if (whiteKing) {
            System.out.println("Black is in Checkmate, White wins.");
      } else {
            System.out.println("White is in Checkmate, Black wins.");
      }
    }
  }