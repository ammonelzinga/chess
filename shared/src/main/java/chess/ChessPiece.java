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
    public PieceType promotePiece(PieceType type){
        if(type != null){
        pieceType = type;
        return pieceType;}
        else{
            return null;
        }
    }
    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
        //throw new RuntimeException("Not implemented");
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
        BishopMove bishopMove = new BishopMove();
        KingMove kingMove = new KingMove();
        KnightMove knightMove = new KnightMove();
        RookMove rookMove = new RookMove();
        PawnMove pawnMove = new PawnMove();
        Collection<ChessMove> potentialMoves = new HashSet<ChessMove>();
        int currentRow = myPosition.Row;
        int currentColumn = myPosition.Col;
        ChessPosition tempPosition = new ChessPosition(currentRow, currentColumn);
        if (PieceType.PAWN == pieceType){
            pawnMove.move(board, myPosition, potentialMoves, currentRow, currentColumn, tempPosition, pieceType, thisPieceColor);
        }
        if(pieceType == PieceType.ROOK || pieceType == PieceType.QUEEN){
            rookMove.move(board, myPosition, potentialMoves, currentRow, currentColumn, tempPosition, pieceType, thisPieceColor);
        }
        if (pieceType == PieceType.KNIGHT){
            knightMove.move(board, myPosition, potentialMoves, currentRow, currentColumn, tempPosition, pieceType, thisPieceColor);
            return potentialMoves;
        }
        if (pieceType == PieceType.KING){
            kingMove.move(board, myPosition, potentialMoves, currentRow, currentColumn, tempPosition, pieceType, thisPieceColor);
        }
        if (pieceType == PieceType.BISHOP || pieceType == PieceType.QUEEN){
            bishopMove.move(board, myPosition, potentialMoves, currentRow, currentColumn, tempPosition, pieceType, thisPieceColor);
        }
        return potentialMoves;
    }
}
