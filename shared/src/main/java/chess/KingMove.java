package chess;
import java.util.Collection;
public class KingMove {
  public void move(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> potentialMoves, int currentRow, int currentColumn,
                   ChessPosition tempPosition, ChessPiece.PieceType pieceType, ChessGame.TeamColor thisPieceColor) {
    int tryKingRow = currentRow;
    int tryKingCol = currentColumn;
    tempPosition.updateRow(tryKingRow+1);
    tempPosition.updateCol(tryKingCol+1);
    if(tempPosition.Row <9 && tempPosition.Col < 9)
    {
      if(board.getPiece(tempPosition) == null ){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol+1), null));
      }
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol+1), null));
        }}}
    tempPosition.updateRow(tryKingRow+1);
    tempPosition.updateCol(tryKingCol);
    if(tempPosition.Row <9 && tempPosition.Col < 9)
    {
      if(board.getPiece(tempPosition) == null){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol), null));
      }
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol), null));
        }}}
    tempPosition.updateRow(tryKingRow+1);
    tempPosition.updateCol(tryKingCol-1);
    if(tempPosition.Row <9 && tempPosition.Col > 0){
      if(board.getPiece(tempPosition) == null){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol-1), null));
      }
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol-1), null));
        }}}
    //Above and Below
    tempPosition.updateRow(tryKingRow);
    tempPosition.updateCol(tryKingCol+1);
    if(tempPosition.Row <9 && tempPosition.Col < 9)
    {
      if(board.getPiece(tempPosition) == null){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol+1), null));
      }
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol+1), null));
        }}}
    tempPosition.updateRow(tryKingRow);
    tempPosition.updateCol(tryKingCol-1);
    if(board.getPiece(tempPosition) == null){
      if(tempPosition.Row <9 && tempPosition.Col > 0){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol-1), null));
      }}
    else{
      if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row <9 && tempPosition.Col > 0){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol-1), null));
      }}
    //left
    tempPosition.updateRow(tryKingRow-1);
    tempPosition.updateCol(tryKingCol+1);
    if(tempPosition.Row >0 && tempPosition.Col < 9)
    {
      if(board.getPiece(tempPosition) == null){
        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol+1), null));
      }
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol+1), null));
        }}}
    if(tryKingRow > 1){
      tempPosition.updateRow(tryKingRow-1);
      tempPosition.updateCol(tryKingCol);
      if(board.getPiece(tempPosition) == null){
        if(tempPosition.Row >0 && tempPosition.Col < 9){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol), null));
        }}
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row >0 && tempPosition.Col < 9){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol), null));
        }}}
    if(tryKingRow > 1 && tryKingCol > 1){
      tempPosition.updateRow(tryKingRow-1);
      tempPosition.updateCol(tryKingCol-1);
      if(board.getPiece(tempPosition) == null){
        if(tempPosition.Row >0 && tempPosition.Col > 0){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol-1), null));
        }}
      else{
        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row >0 && tempPosition.Col > 0){
          potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol-1), null));
        }}}
  }
}
