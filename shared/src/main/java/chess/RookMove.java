package chess;
import java.util.Collection;
public class RookMove {
  public void move(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> potentialMoves, int currentRow, int currentColumn,
                   ChessPosition tempPosition, ChessPiece.PieceType pieceType, ChessGame.TeamColor thisPieceColor) {
            int tryRookRow = currentRow;
            int tryRookCol = currentColumn;
            while (tryRookCol < 8){
                tempPosition.updateRow(tryRookRow);
                tempPosition.updateCol(tryRookCol+1);
                if(board.getPiece(tempPosition) == null){
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow, tryRookCol+1), null));
                    tryRookCol++;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow, tryRookCol+1), null));
                        break;
                    }
                    else{
                        break;}
                }
            }
            tryRookRow = currentRow;
            tryRookCol = currentColumn;
            while (tryRookCol > 1){
                tempPosition.updateRow(tryRookRow);
                tempPosition.updateCol(tryRookCol-1);
                if(board.getPiece(tempPosition) == null){
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow, tryRookCol-1), null));
                    tryRookCol--;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow, tryRookCol-1), null));
                        break;
                    }
                    else{
                        break;}
                }
            }
            tryRookRow = currentRow;
            tryRookCol = currentColumn;
            while (tryRookRow < 8){
                tempPosition.updateRow(tryRookRow+1);
                tempPosition.updateCol(tryRookCol);
                if(board.getPiece(tempPosition) == null){
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow+1, tryRookCol), null));
                    tryRookRow++;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow+1, tryRookCol), null));
                        break;
                    }
                    else{
                        break;}
                }
            }
            tryRookRow = currentRow;
            tryRookCol = currentColumn;
            while (tryRookRow > 1){
                tempPosition.updateRow(tryRookRow-1);
                tempPosition.updateCol(tryRookCol);
                if(board.getPiece(tempPosition) == null){
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow-1, tryRookCol), null));
                    tryRookRow--;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryRookRow-1, tryRookCol), null));
                        break;
                    }
                    else{
                        break;}
                }
            }
        }
  }

