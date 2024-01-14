package chess;

import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    PieceType pieceType;
    ChessGame.TeamColor thisPieceColor;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        pieceType = type;
        thisPieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return thisPieceColor;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceType=" + pieceType +
                ", thisPieceColor=" + thisPieceColor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that=(ChessPiece) o;
        return pieceType == that.pieceType && thisPieceColor == that.thisPieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, thisPieceColor);
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //ArrayList<Set<Integer>> potentialMoves = new ArrayList<Set<Integer>>();
        //System.out.print(thisPieceColor);
        Collection<ChessMove> potentialMoves = new HashSet<ChessMove>();
        int currentRow = myPosition.Row;
        int currentColumn = myPosition.Col;
        ChessMove potentialMove;
        //ChessMove potentialMove = new ChessMove(myPosition, myPosition, pieceType);
        ChessPosition tempPosition = new ChessPosition(currentRow, currentColumn);
        if (PieceType.PAWN == pieceType){
            //potentialSet.add(1);
            //potentialSet.add(2);
            //potentialMoves.add(potentialSet);
        }
        //ROOK ROOK ROOK ROOK ROOK ROOK ROOK ROOK ROOK ROOKROOK ROOK ROOK ROOK
        if(pieceType == PieceType.ROOK || pieceType == PieceType.QUEEN){
            //Four different routes
            int tryRookRow = currentRow;
            int tryRookCol = currentColumn;
        }
        // KNIGHT KNIGHT KNIGHT KNIGHT KNIGHT KNIGHT KNIGHT KNIGHT KNIGHT KNIGHT
        if (pieceType == PieceType.KNIGHT){
            //8 Potential movements
            int spotChecker = 0;
            while (spotChecker < 8){
                //up 2 row
                if(spotChecker == 0){
                    tempPosition.updateRow(currentRow + 2);
                    tempPosition.updateCol(currentColumn + 1);
                }
                if(spotChecker == 1){
                    tempPosition.updateCol(currentColumn-1);
                    tempPosition.updateRow((currentRow+2));
                }
                //down 2 row
                if(spotChecker == 2){
                    tempPosition.updateCol(currentColumn-1);
                    tempPosition.updateRow((currentRow-2));
                }
                if(spotChecker == 3){
                    tempPosition.updateCol(currentColumn+1);
                    tempPosition.updateRow((currentRow-2));
                }
                //up 2 col
                if(spotChecker == 4){
                    tempPosition.updateCol(currentColumn+2);
                    tempPosition.updateRow((currentRow+1));
                }
                if(spotChecker == 5){
                    tempPosition.updateCol(currentColumn+2);
                    tempPosition.updateRow((currentRow-1));
                }
                //down 2 col
                if(spotChecker == 6){
                    tempPosition.updateCol(currentColumn-2);
                    tempPosition.updateRow(currentRow+1);
                }
                if(spotChecker == 7){
                    tempPosition.updateCol(currentColumn-2);
                    tempPosition.updateRow((currentRow-1));
                }
                if(tempPosition.Row < 9 && tempPosition.Row > 0 && tempPosition.Col < 9 && tempPosition.Col > 0){
                    if(board.getPiece(tempPosition) == null ){
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tempPosition.Row, tempPosition.Col), pieceType));
                    }
                    else{
                        if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                            potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tempPosition.Row, tempPosition.Col), pieceType));
                        }
                    }

                }
                spotChecker ++;
            }
            return potentialMoves;
        }
        //KING KING KING KING KING KING KING KING KING KING KING KING KING KING KING
        if (pieceType == PieceType.KING){
            int tryKingRow = currentRow;
            int tryKingCol = currentColumn;

            // Check all 8 square next to king
            //Right
            tempPosition.updateRow(tryKingRow+1);
            tempPosition.updateCol(tryKingCol+1);

            if(board.getPiece(tempPosition) == null ){
                if(tempPosition.Row <9 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol+1), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row <9 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol+1), pieceType));
            }}

            tempPosition.updateRow(tryKingRow+1);
            tempPosition.updateCol(tryKingCol);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row <9 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row <9 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol), pieceType));
            }}

            tempPosition.updateRow(tryKingRow+1);
            tempPosition.updateCol(tryKingCol-1);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row <9 && tempPosition.Col > 0){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol-1), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row <9 && tempPosition.Col > 0){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow+1, tryKingCol-1), pieceType));
            }}
            //Above and Below
            tempPosition.updateRow(tryKingRow);
            tempPosition.updateCol(tryKingCol+1);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row <9 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol+1), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row <9 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol+1), pieceType));
            }}

            tempPosition.updateRow(tryKingRow);
            tempPosition.updateCol(tryKingCol-1);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row <9 && tempPosition.Col > 0){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol-1), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row <9 && tempPosition.Col > 0){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow, tryKingCol-1), pieceType));
            }}
            //left
            tempPosition.updateRow(tryKingRow-1);
            tempPosition.updateCol(tryKingCol+1);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row >0 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol+1), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row >0 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol+1), pieceType));
            }}

            tempPosition.updateRow(tryKingRow-1);
            tempPosition.updateCol(tryKingCol);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row >0 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row >0 && tempPosition.Col < 9){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol), pieceType));
            }}

            tempPosition.updateRow(tryKingRow-1);
            tempPosition.updateCol(tryKingCol-1);
            if(board.getPiece(tempPosition) == null){
                if(tempPosition.Row >0 && tempPosition.Col > 0){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol-1), pieceType));
            }}
            else{
            if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor && tempPosition.Row >0 && tempPosition.Col > 0){
                potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryKingRow-1, tryKingCol-1), pieceType));
            }}
            return potentialMoves;
        }
        //BISHOP BISHOP BISHOP BISHOP BISHOP BISHOP BISHOP BISHOP BISHOP BISHOP BISHOP
        if (pieceType == PieceType.BISHOP || pieceType == PieceType.QUEEN){
            //First Check row+, col+
            int tryBishopRow = currentRow;
            int tryBishopCol = currentColumn;
            while (tryBishopRow < 8 && tryBishopCol < 8){
                tempPosition.updateRow(tryBishopRow+1);
                tempPosition.updateCol(tryBishopCol+1);
                if(board.getPiece(tempPosition) == null){
                    potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                    /*System.out.print("(");
                    System.out.print(tempPosition.Row);
                    System.out.print(", ");
                    System.out.print(tempPosition.Col);
                    System.out.print(")");*/
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow+1, tryBishopCol+1), pieceType));
                    tryBishopRow ++;
                    tryBishopCol++;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                        /*System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");*/
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow+1, tryBishopCol+1), pieceType));
                        break;
                    }
                    else{
                        /*System.out.print("nopeeeee");
                        System.out.print("\n");
                        System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");
                        System.out.print(board.getPiece(tempPosition).getTeamColor());
                        System.out.print("\n");*/
                        break;}
                }
            }

            //third check row-, col+
            tryBishopRow = currentRow;
            tryBishopCol = currentColumn;
            while (tryBishopRow > 1 && tryBishopCol < 8){
                tempPosition.updateRow(tryBishopRow-1);
                tempPosition.updateCol(tryBishopCol+1);
                if(board.getPiece(tempPosition) == null){
                    potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow-1, tryBishopCol+1), pieceType));
                    /*System.out.print("(");
                    System.out.print(tempPosition.Row);
                    System.out.print(", ");
                    System.out.print(tempPosition.Col);
                    System.out.print(")");*/
                    tryBishopRow --;
                    tryBishopCol++;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                        /*System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");*/
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow-1, tryBishopCol+1), pieceType));
                        break;
                    }
                    else{
                        /*System.out.print("nopeeeee");
                        System.out.print("\n");
                        System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");
                        System.out.print(board.getPiece(tempPosition).getTeamColor());
                        System.out.print("\n");*/
                        break;
                    }
                    }}
            //fourth check row-, col-
            tryBishopRow = currentRow;
            tryBishopCol = currentColumn;
            while (tryBishopRow > 1 && tryBishopCol > 1){
                tempPosition.updateRow(tryBishopRow-1);
                tempPosition.updateCol(tryBishopCol-1);
                if(board.getPiece(tempPosition) == null){
                    potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow-1, tryBishopCol-1), pieceType));
                    /*System.out.print("(");
                    System.out.print(tempPosition.Row);
                    System.out.print(", ");
                    System.out.print(tempPosition.Col);
                    System.out.print(")");*/
                    tryBishopRow --;
                    tryBishopCol --;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                        /*System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");*/
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow-1, tryBishopCol-1), pieceType));
                        break;
                    }
                    else{
                        /*System.out.print("nopeeeee");
                        System.out.print("\n");
                        System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");
                        System.out.print(board.getPiece(tempPosition).getTeamColor());
                        System.out.print("\n");*/
                        break;
                    }
                    }}
            //Second check row+, col-
            tryBishopRow = currentRow;
            tryBishopCol = currentColumn;
            while (tryBishopRow < 8 && tryBishopCol > 1){
                tempPosition.updateRow(tryBishopRow+1);
                tempPosition.updateCol(tryBishopCol-1);
                if(board.getPiece(tempPosition) == null){
                    potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                    potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow+1, tryBishopCol-1), pieceType));
                    /*System.out.print("(");
                    System.out.print(tempPosition.Row);
                    System.out.print(", ");
                    System.out.print(tempPosition.Col);
                    System.out.print(")");*/
                    tryBishopRow ++;
                    tryBishopCol--;}
                else{
                    if(board.getPiece(tempPosition).getTeamColor() != thisPieceColor){
                        potentialMove = new ChessMove(myPosition, tempPosition, pieceType);
                        /*System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");*/
                        potentialMoves.add(new ChessMove(myPosition, new ChessPosition(tryBishopRow+1, tryBishopCol-1), pieceType));
                        break;
                    }
                    else{
                        /*System.out.print("nopeeeee");
                        System.out.print("\n");
                        System.out.print("(");
                        System.out.print(tempPosition.Row);
                        System.out.print(", ");
                        System.out.print(tempPosition.Col);
                        System.out.print(")");
                        System.out.print(board.getPiece(tempPosition).getTeamColor());
                        System.out.print("\n");*/
                        break;
                    }
                    }}

        }

        //throw new RuntimeException("Not implemented");
        return potentialMoves;
    }
}
