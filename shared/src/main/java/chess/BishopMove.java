package chess;
import java.util.*;
public class BishopMove {
  public void move(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> potentialMoves, int currentRow, int currentColumn,
               ChessPosition tempPosition, ChessPiece.PieceType pieceType, ChessGame.TeamColor thisPieceColor) {
    if (pieceType == ChessPiece.PieceType.BISHOP || pieceType == ChessPiece.PieceType.QUEEN) {
      //First Check row+, col+
      int tryBishopRow=currentRow;
      int tryBishopCol=currentColumn;
      while (tryBishopRow < 8 && tryBishopCol < 8) {
        tempPosition.updateRow(tryBishopRow + 1);
        tempPosition.updateCol(tryBishopCol + 1);
        if (board.getPiece(tempPosition) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow + 1, tryBishopCol + 1), null));
          tryBishopRow++;
          tryBishopCol++;
        } else {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {

            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow + 1, tryBishopCol + 1), null));
            break;
          } else {
            break;
          }
        }
      }
      //third check row-, col+
      tryBishopRow=currentRow;
      tryBishopCol=currentColumn;
      while (tryBishopRow > 1 && tryBishopCol < 8) {
        tempPosition.updateRow(tryBishopRow - 1);
        tempPosition.updateCol(tryBishopCol + 1);
        if (board.getPiece(tempPosition) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow - 1, tryBishopCol + 1), null));
          tryBishopRow--;
          tryBishopCol++;
        } else {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow - 1, tryBishopCol + 1), null));
            break;
          } else {
            break;
          }
        }
      }
      //fourth check row-, col-
      tryBishopRow=currentRow;
      tryBishopCol=currentColumn;
      while (tryBishopRow > 1 && tryBishopCol > 1) {
        tempPosition.updateRow(tryBishopRow - 1);
        tempPosition.updateCol(tryBishopCol - 1);
        if (board.getPiece(tempPosition) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow - 1, tryBishopCol - 1), null));
          tryBishopRow--;
          tryBishopCol--;
        } else {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow - 1, tryBishopCol - 1), null));
            break;
          } else {
            break;}}}
      //Second check row+, col-
      tryBishopRow=currentRow;
      tryBishopCol=currentColumn;
      while (tryBishopRow < 8 && tryBishopCol > 1) {
        tempPosition.updateRow(tryBishopRow + 1);
        tempPosition.updateCol(tryBishopCol - 1);
        if (board.getPiece(tempPosition) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow + 1, tryBishopCol - 1), null));
          tryBishopRow++;
          tryBishopCol--;
        } else {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow + 1, tryBishopCol - 1), null));
            break;
          } else {
            break;}}}}}
}
