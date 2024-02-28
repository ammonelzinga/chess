package chess;

public class DiagonalCheck {

  public boolean checkDiagonal(ChessPosition KingPosition, ChessBoard gameBoard, ChessGame.TeamColor teamColor) {
    //potential check from diagonal
    for (int i=0; i < 4; i++) {
      int tempRow=KingPosition.row;
      int tempCol=KingPosition.Col;
      boolean firstDiagonal=true;
      //check ++ row, ++ col Diagonal, queen/bishop
      if (i == 0) {
        tempRow=KingPosition.row + 1;
        tempCol=KingPosition.Col + 1;
        while (tempRow < 9 && tempCol < 9) {
          if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null) {
            tempRow++;
            tempCol++;
            firstDiagonal=false;
          } else {
            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                    && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                    (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))) {
              return true;
            } else {
              break;
            }}}
      }
      //check ++ row, -- col Diagonal, queen/bishop
      tempRow=KingPosition.row + 1;
      tempCol=KingPosition.Col - 1;
      if (i == 1) {
        firstDiagonal=true;
        while (tempRow < 9 && tempCol > 0) {
          if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null) {
            tempRow++;
            tempCol--;
            firstDiagonal=false;
          } else {
            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                    && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                    (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))) {
              return true;
            } else {
              break;
            }}}
      }
      //check -- row, ++ col diagonal, queen/bishop
      tempRow=KingPosition.row - 1;
      tempCol=KingPosition.Col + 1;
      if (i == 2) {
        firstDiagonal=true;
        while (tempRow > 0 && tempCol < 9) {
          if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null) {
            tempRow--;
            tempCol++;
            firstDiagonal=false;
          } else {
            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                    && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                    (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))) {
              return true;
            } else {
              break;
            }}}
      }
      // row--, col -- diagonal queen/bishop
      tempRow=KingPosition.row - 1;
      tempCol=KingPosition.Col - 1;
      if (i == 3) {
        firstDiagonal=true;
        while (tempRow > 0 && tempCol > 0) {
          if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null) {
            tempRow--;
            tempCol--;
            firstDiagonal=false;
          } else {
            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                    && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                    (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))) {
              return true;
            } else {
              break;}}}}}
    return false;
  }
}
