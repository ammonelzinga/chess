package Play;

import chess.*;

import java.util.Scanner;

public class ParseUserMove {
  private ChessGame.TeamColor playerColor;

  public ChessMove run(ChessBoard board, ChessGame.TeamColor playerColorr) {
    playerColor = playerColorr;
    System.out.println("Enter the position of the piece you'd like to move. Ex: b2 ");
      Scanner scanner=new Scanner(System.in);
      String startPositionString=scanner.nextLine();
      char startPositionRow = startPositionString.charAt(1);
      int startRow = startPositionRow - '0';
      char startPositionCol=startPositionString.charAt(0);
      int startCol = letterToCol(startPositionCol);
      if(startCol == 0){
        return null;
      }
      System.out.println("Enter the position where you'd like this piece to move to. Ex: c5");
      String endPositionString=scanner.nextLine();
      char endPositionRow = endPositionString.charAt(1);
      int endRow = endPositionRow - '0';
      char endPositionCol=endPositionString.charAt(0);
      int endCol = letterToCol(endPositionCol);
      if(endCol == 0){
        return null;
      }
      ChessPosition startPosFinal = new ChessPosition(startRow, startCol);
      ChessPosition endPosFinal = new ChessPosition(endRow, endCol);
      if(board.getPiece(startPosFinal) == null){
        return null;
      }
      if(board.getPiece(startPosFinal).getTeamColor() != playerColor){
        System.out.println("Hmmm, that's not your piece");
        return null;
      }
      if(board.getPiece(startPosFinal).getPieceType() == ChessPiece.PieceType.PAWN){
        if((endRow == 8 && board.getPiece(startPosFinal).getTeamColor() == ChessGame.TeamColor.WHITE)
            || endRow == 1 && board.getPiece(startPosFinal).getTeamColor() == ChessGame.TeamColor.BLACK){
          ChessPiece.PieceType promotionPiece = getPromotionPiece();
          if(promotionPiece == null){return null;}
            else{
              return new ChessMove(startPosFinal, endPosFinal, promotionPiece);
            }
      }}
      else{
      return new ChessMove(startPosFinal, endPosFinal, null);}
      return null;
  }

  private ChessPiece.PieceType getPromotionPiece(){
    System.out.println("It looks like you are trying to promote a pawn. What piece would you like to promote to:");
    System.out.println("queen, rook, bishop, or knight? (or type 'stop' to cancel)");
    ChessPiece.PieceType promotionPiece = null;
    String wordPromotionPiece = "";
    Scanner scanner=new Scanner(System.in);
    while(promotionPiece == null){
      wordPromotionPiece =scanner.nextLine();
      wordPromotionPiece = wordPromotionPiece.toLowerCase();
      if(wordPromotionPiece.equals("stop")){
        return null;
      }
      promotionPiece = wordToChessPiece(wordPromotionPiece);
    }
    return promotionPiece;
  }
  private ChessPiece.PieceType wordToChessPiece(String word){
        if(word == "queen"){
          return ChessPiece.PieceType.QUEEN;
        } else if (word == "rook") {
          return ChessPiece.PieceType.ROOK;
        }
        else if (word == "bishop") {
          return ChessPiece.PieceType.BISHOP;
        }
        else if (word == "knight") {
          return ChessPiece.PieceType.KNIGHT;
        }
        System.out.println("Sorry, try again: ");
        System.out.println("queen, rook, bishop, or knight? (or type 'stop' to cancel)");
        return null;
  }

  private int letterToCol(char letter) {
    if (letter == 'a' || letter == 'A') {
      return 1;
    } else if (letter == 'b' || letter == 'B') {
      return 2;
    } else if (letter == 'c' || letter == 'C') {
      return 3;
    } else if (letter == 'd' || letter == 'D') {
      return 4;
    } else if (letter == 'e' || letter == 'E') {
      return 5;
    } else if (letter == 'f' || letter == 'F') {
      return 6;
    } else if (letter == 'g' || letter == 'G') {
      return 7;
    } else if (letter == 'h' || letter == 'H') {
      return 8;
    }
    return 0;
  }

}
