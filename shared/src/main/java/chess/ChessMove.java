package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {
    ChessPosition startPos;
    ChessPosition endPos;

    ChessPiece.PieceType promotion;
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        startPos = startPosition;
        endPos = endPosition;
        promotion = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return startPos;
        //throw new RuntimeException("Not implemented");
    }

    public String wordsToString(){
        String wordMove = "";
        String letterStartCol = "";
        String letterEndCol = "";
        switch (startPos.Col){
            case 1:
                letterStartCol = "a";
            case 2:
                letterStartCol = "b";
            case 3:
                letterStartCol = "c";
            case 4:
                letterStartCol = "d";
            case 5:
                letterStartCol = "e";
            case 6:
                letterStartCol = "f";
            case 7:
                letterStartCol = "g";
            case 8:
                letterStartCol = "h";
        }
        switch (endPos.Col){
            case 1:
                letterEndCol = "a";
            case 2:
                letterEndCol = "b";
            case 3:
                letterEndCol = "c";
            case 4:
                letterEndCol = "d";
            case 5:
                letterEndCol = "e";
            case 6:
                letterEndCol = "f";
            case 7:
                letterEndCol = "g";
            case 8:
                letterEndCol = "h";
        }
        wordMove += letterStartCol + startPos.row + " to " + letterEndCol + endPos.row;
        return wordMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMove chessMove=(ChessMove) o;
        return startPos.equals(chessMove.startPos) && endPos.equals(chessMove.endPos) && chessMove.promotion==promotion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPos, endPos, promotion);
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return endPos;
        //throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        return "ChessMove{" +
                "startPos=" + startPos +
                ", endPos=" + endPos +
                ", promotion=" + promotion +
                '}';
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return promotion;
        //throw new RuntimeException("Not implemented");
    }
}
