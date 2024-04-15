package chess;
import java.util.Collection;
public class PawnMove {
  public void move(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> potentialMoves, int currentRow, int currentColumn,
                   ChessPosition tempPosition, ChessPiece.PieceType pieceType, ChessGame.TeamColor thisPieceColor) {
    if (thisPieceColor == ChessGame.TeamColor.WHITE) {
      if (currentRow == 2) {
        tempPosition.updateRow(3);
        tempPosition.updateCol(currentColumn);
        if (board.getPiece(tempPosition) == null && board.getPiece(new ChessPosition(4, currentColumn)) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(4, currentColumn), null));
        }
      }
      if (currentRow < 8) {
        tempPosition.updateRow(currentRow + 1);
        tempPosition.updateCol(currentColumn);
        if (board.getPiece(tempPosition) == null) {
          if (currentRow + 1 == 8) {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn), ChessPiece.PieceType.QUEEN));
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn), ChessPiece.PieceType.KNIGHT));
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn), ChessPiece.PieceType.ROOK));
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn), ChessPiece.PieceType.BISHOP));
          } else {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn), null));
          }}
      }
      if (currentRow < 8 && currentColumn > 1) {
        tempPosition.updateRow(currentRow + 1);
        tempPosition.updateCol(currentColumn - 1);
        if (board.getPiece(tempPosition) != null) {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            if (tempPosition.row == 8) {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn - 1), ChessPiece.PieceType.QUEEN));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn - 1), ChessPiece.PieceType.ROOK));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn - 1), ChessPiece.PieceType.KNIGHT));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn - 1), ChessPiece.PieceType.BISHOP));
            } else {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn - 1), null));
            }}}}
      if (currentRow < 8 && currentColumn < 8) {
        tempPosition.updateRow(currentRow + 1);
        tempPosition.updateCol(currentColumn + 1);
        if (board.getPiece(tempPosition) != null) {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            if (tempPosition.row == 8) {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn + 1), ChessPiece.PieceType.QUEEN));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn + 1), ChessPiece.PieceType.ROOK));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn + 1), ChessPiece.PieceType.KNIGHT));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn + 1), ChessPiece.PieceType.BISHOP));
            } else {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow + 1, currentColumn + 1), null));
            }}}}
    } else {
      //initial movement
      if (currentRow == 7) {
        tempPosition.updateRow(6);
        tempPosition.updateCol(currentColumn);
        if (board.getPiece(tempPosition) == null && board.getPiece(new ChessPosition(5, currentColumn)) == null) {
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(5, currentColumn), null));
        }
      }
      if (currentRow > 1) {
        tempPosition.updateRow(currentRow - 1);
        tempPosition.updateCol(currentColumn);
        if (board.getPiece(tempPosition) == null) {
          if (currentRow - 1 == 1 && currentRow > 1) {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn), ChessPiece.PieceType.QUEEN));
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn), ChessPiece.PieceType.ROOK));
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn), ChessPiece.PieceType.KNIGHT));
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn), ChessPiece.PieceType.BISHOP));
          } else {
            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn), null));
          }}}
      if (currentRow > 1 && currentColumn > 1) {
        tempPosition.updateRow(currentRow - 1);
        tempPosition.updateCol(currentColumn - 1);
        if (board.getPiece(tempPosition) != null) {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            if (tempPosition.row == 1) {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn - 1), ChessPiece.PieceType.QUEEN));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn - 1), ChessPiece.PieceType.ROOK));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn - 1), ChessPiece.PieceType.KNIGHT));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn - 1), ChessPiece.PieceType.BISHOP));
            } else {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn - 1), null));
            }}}}
      if (currentRow > 1 && currentColumn < 8) {
        tempPosition.updateRow(currentRow - 1);
        tempPosition.updateCol(currentColumn + 1);
        if (board.getPiece(tempPosition) != null) {
          if (board.getPiece(tempPosition).getTeamColor() != thisPieceColor) {
            if (tempPosition.row == 1) {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn + 1), ChessPiece.PieceType.QUEEN));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn + 1), ChessPiece.PieceType.ROOK));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn + 1), ChessPiece.PieceType.KNIGHT));
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn + 1), ChessPiece.PieceType.BISHOP));
            } else {
              potentialMoves.add(new ChessMove(myPosition, new ChessPosition(currentRow - 1, currentColumn + 1), null));
            }}}}
    }}
}
