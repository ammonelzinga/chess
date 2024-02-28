package chess;

import java.util.Collection;
public class KnightMove {
  public void move(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> potentialMoves, int currentRow, int currentColumn,
                   ChessPosition tempPosition, ChessPiece.PieceType pieceType, ChessGame.TeamColor thisPieceColor) {
    int spotChecker=0;
    while (spotChecker < 8) {
      //up 2 row
      if (spotChecker == 0) {
        tempPosition.updateRow(currentRow + 2);
        tempPosition.updateCol(currentColumn + 1);
      }
      if (spotChecker == 1) {
        tempPosition.updateCol(currentColumn - 1);
        tempPosition.updateRow((currentRow + 2));
      }
      //down 2 row
      if (spotChecker == 2) {
        tempPosition.updateCol(currentColumn - 1);
        tempPosition.updateRow((currentRow - 2));
      }
      if (spotChecker == 3) {
        tempPosition.updateCol(currentColumn + 1);
        tempPosition.updateRow((currentRow - 2));
      }
      //up 2 col
      if (spotChecker == 4) {
        tempPosition.updateCol(currentColumn + 2);
        tempPosition.updateRow((currentRow + 1));
      }
      if (spotChecker == 5) {
        tempPosition.updateCol(currentColumn + 2);
        tempPosition.updateRow((currentRow - 1));
      }
      //down 2 col
      if (spotChecker == 6) {
        tempPosition.updateCol(currentColumn - 2);
        tempPosition.updateRow(currentRow + 1);
      }
      if (spotChecker == 7) {
        tempPosition.updateCol(currentColumn - 2);
        tempPosition.updateRow((currentRow - 1));
      }
      if (tempPosition.row < 9 && tempPosition.row > 0 && tempPosition.Col < 9 && tempPosition.Col > 0) {
        if (board.getPiece(tempPosition) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tempPosition.row, tempPosition.Col), null));
        } else {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tempPosition.row, tempPosition.Col), null));
          }
        }

      }
      spotChecker++;
    }
  }
}
