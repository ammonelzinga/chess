package chess;

public class Castle {
  public ChessGame game;

          public Castle(ChessGame gamee){
            game = gamee;
          }
  public boolean canCastle (ChessGame.TeamColor color, boolean queenSide){
    boolean blackKingMoved = game.blackKingMoved;
    boolean blackRookMoved = game.blackRookMoved;
    ChessBoard gameBoard = game.gameBoard;
    ChessPosition blackKingPosition = game.blackKingPosition;
    ChessPosition whiteKingPosition = game.whiteKingPosition;
    boolean blackRook8Moved = game.blackRook8Moved;
    boolean whiteKingMoved = game.whiteKingMoved;
    boolean whiteRookMoved = game.whiteRookMoved;
    boolean whiteRook8Moved = game.whiteRook8Moved;
    boolean canCastle = false;
    if(color == ChessGame.TeamColor.BLACK){
      if(queenSide){
        if(blackKingMoved == false && blackRookMoved == false){

          if((gameBoard.getPiece(new ChessPosition(8, 1)) != null && gameBoard.getPiece(new ChessPosition(8, 5)) != null)){
            if(gameBoard.getPiece(new ChessPosition(8, 1)).pieceType == ChessPiece.PieceType.ROOK &&
                    gameBoard.getPiece(new ChessPosition(8, 1)).thisPieceColor == ChessGame.TeamColor.BLACK &&
                    gameBoard.getPiece(new ChessPosition(8, 5)).pieceType == ChessPiece.PieceType.KING &&
                    gameBoard.getPiece(new ChessPosition(8, 5)).thisPieceColor == ChessGame.TeamColor.BLACK
                    && gameBoard.getPiece(new ChessPosition(8, 4)) == null && gameBoard.getPiece(new ChessPosition(8, 3)) == null &&
                    gameBoard.getPiece(new ChessPosition(8, 2)) == null){
              gameBoard.chessBoardArray[7][4] = null;
              gameBoard.addPiece(new ChessPosition(8, 4 ), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
              blackKingPosition.Col = 4;
              if(game.isInCheck(ChessGame.TeamColor.BLACK) == false){
                gameBoard.chessBoardArray[7][3] = null;
                gameBoard.addPiece(new ChessPosition(8, 3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
                blackKingPosition.Col = 3;
                if(game.isInCheck(ChessGame.TeamColor.BLACK) == false){
                  canCastle = true;}}
              gameBoard.addPiece(new ChessPosition(8, 5),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
              gameBoard.chessBoardArray[7][3] = null;
              gameBoard.chessBoardArray[7][2] = null;
              blackKingPosition.Col = 5;}}}}
      else{
        if(blackKingMoved == false && blackRook8Moved == false){

          if((gameBoard.getPiece(new ChessPosition(8, 8)) != null && gameBoard.getPiece(new ChessPosition(8, 5)) != null)){
            if(gameBoard.getPiece(new ChessPosition(8, 8)).pieceType == ChessPiece.PieceType.ROOK &&
                    gameBoard.getPiece(new ChessPosition(8, 8)).thisPieceColor == ChessGame.TeamColor.BLACK &&
                    gameBoard.getPiece(new ChessPosition(8, 5)).pieceType == ChessPiece.PieceType.KING &&
                    gameBoard.getPiece(new ChessPosition(8, 5)).thisPieceColor == ChessGame.TeamColor.BLACK
                    && gameBoard.getPiece(new ChessPosition(8, 6)) == null && gameBoard.getPiece(new ChessPosition(8, 7)) == null){
              gameBoard.chessBoardArray[7][4] = null;
              gameBoard.addPiece(new ChessPosition(8, 6 ), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
              blackKingPosition.Col = 6;
              if(game.isInCheck(ChessGame.TeamColor.BLACK) == false){
                gameBoard.chessBoardArray[7][5] = null;
                gameBoard.addPiece(new ChessPosition(8, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
                blackKingPosition.Col = 7;
                if(game.isInCheck(ChessGame.TeamColor.BLACK) == false){
                  canCastle = true;}}
              gameBoard.addPiece(new ChessPosition(8, 5),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
              gameBoard.chessBoardArray[7][5] = null;
              gameBoard.chessBoardArray[7][6] = null;
              blackKingPosition.Col = 5;}}}}}
    else{
      if(queenSide){
        if(whiteKingMoved == false && whiteRookMoved == false){
          if((gameBoard.getPiece(new ChessPosition(1, 1)) != null && gameBoard.getPiece(new ChessPosition(1, 5)) != null)){

            if(gameBoard.getPiece(new ChessPosition(1, 1)).pieceType == ChessPiece.PieceType.ROOK &&
                    gameBoard.getPiece(new ChessPosition(1, 1)).thisPieceColor == ChessGame.TeamColor.WHITE &&
                    gameBoard.getPiece(new ChessPosition(1, 5)).pieceType == ChessPiece.PieceType.KING &&
                    gameBoard.getPiece(new ChessPosition(1, 5)).thisPieceColor == ChessGame.TeamColor.WHITE
                    && gameBoard.getPiece(new ChessPosition(1, 4)) == null && gameBoard.getPiece(new ChessPosition(1, 3)) == null &&
                    gameBoard.getPiece(new ChessPosition(1, 2)) == null){
              gameBoard.chessBoardArray[0][4] = null;
              gameBoard.addPiece(new ChessPosition(1, 4 ), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
              whiteKingPosition.Col = 4;
              if(game.isInCheck(ChessGame.TeamColor.WHITE) == false){
                gameBoard.chessBoardArray[0][3] = null;
                gameBoard.addPiece(new ChessPosition(1, 3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
                whiteKingPosition.Col = 3;
                if(game.isInCheck(ChessGame.TeamColor.WHITE) == false){
                  canCastle = true;}}
              gameBoard.addPiece(new ChessPosition(1, 5),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
              gameBoard.chessBoardArray[0][3] = null;
              gameBoard.chessBoardArray[0][2] = null;
              whiteKingPosition.Col = 5;
            }}}}
      else{
        if(whiteKingMoved == false && whiteRook8Moved == false){
          if((gameBoard.getPiece(new ChessPosition(1, 8)) != null && gameBoard.getPiece(new ChessPosition(1, 5)) != null)){

            if(gameBoard.getPiece(new ChessPosition(1, 8)).pieceType == ChessPiece.PieceType.ROOK &&
                    gameBoard.getPiece(new ChessPosition(1, 8)).thisPieceColor == ChessGame.TeamColor.WHITE &&
                    gameBoard.getPiece(new ChessPosition(1, 5)).pieceType == ChessPiece.PieceType.KING &&
                    gameBoard.getPiece(new ChessPosition(1, 5)).thisPieceColor == ChessGame.TeamColor.WHITE
                    && gameBoard.getPiece(new ChessPosition(1, 6)) == null && gameBoard.getPiece(new ChessPosition(1, 7)) == null){
              gameBoard.chessBoardArray[0][4] = null;
              gameBoard.addPiece(new ChessPosition(1, 6 ), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
              whiteKingPosition.Col = 6;
              if(game.isInCheck(ChessGame.TeamColor.WHITE) == false){
                gameBoard.chessBoardArray[0][5] = null;
                gameBoard.addPiece(new ChessPosition(1, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
                whiteKingPosition.Col = 7;
                if(game.isInCheck(ChessGame.TeamColor.WHITE) == false){
                  canCastle = true;}}
              gameBoard.addPiece(new ChessPosition(1, 5),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
              gameBoard.chessBoardArray[0][6] = null;
              gameBoard.chessBoardArray[0][5] = null;
              whiteKingPosition.Col = 5;
            }}}}}
    return canCastle;}

}
