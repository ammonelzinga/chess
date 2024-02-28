package chess;

public class MakeMovePawn {
  public void pawnLogic(ChessPiece currentPiece, ChessBoard gameBoard, int startRow, int startCol, int endRow, int endCol, ChessPosition whitePawnAdvance2Now,
                   ChessPosition blackPawnAdvance2Now){
  if(currentPiece.thisPieceColor == ChessGame.TeamColor.BLACK){
    if(whitePawnAdvance2Now != null){
      if(whitePawnAdvance2Now.Row == 4 && startRow == 4 && ((startCol - whitePawnAdvance2Now.Col == 1) ||
              startCol - whitePawnAdvance2Now.Col == -1)){
        if(endCol == whitePawnAdvance2Now.Col && endRow == 3){
          gameBoard.chessBoardArray[startRow -1][startCol -1] = null;
          gameBoard.chessBoardArray[whitePawnAdvance2Now.Row -1][whitePawnAdvance2Now.Col -1] = null;
          gameBoard.addPiece(new ChessPosition(endRow, endCol), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));}}}}
                else{
    if(blackPawnAdvance2Now != null){
      if(blackPawnAdvance2Now.Row == 5 && startRow == 5 && ((startCol - blackPawnAdvance2Now.Col == 1) ||
              startCol - blackPawnAdvance2Now.Col == -1) && endCol == blackPawnAdvance2Now.Col && endRow == 6){
        gameBoard.chessBoardArray[startRow -1][startCol -1] = null;
        gameBoard.chessBoardArray[blackPawnAdvance2Now.Row -1][blackPawnAdvance2Now.Col -1] = null;
        gameBoard.addPiece(new ChessPosition(endRow, endCol), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));}}}}
}
