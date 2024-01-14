package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    ChessPiece[][] chessBoardArray = new ChessPiece[9][9];
    //Black Pieces
    ChessPiece blackPawn1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    ChessPiece blackPawn2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    ChessPiece blackPawn3 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    ChessPiece blackPawn4 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);

    ChessPiece blackPawn5 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    ChessPiece blackPawn6 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    ChessPiece blackPawn7 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    ChessPiece blackPawn8 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);

    ChessPiece blackRook1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
    ChessPiece blackRook2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
    ChessPiece blackKnight1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
    ChessPiece blackKnight2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);

    ChessPiece blackBishop1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
    ChessPiece blackBishop2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
    ChessPiece blackQueen1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
    ChessPiece blackKing1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);

    //White Pieces
    ChessPiece whitePawn1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    ChessPiece whitePawn2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    ChessPiece whitePawn3 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    ChessPiece whitePawn4 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

    ChessPiece whitePawn5 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    ChessPiece whitePawn6 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    ChessPiece whitePawn7 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    ChessPiece whitePawn8 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

    ChessPiece whiteRook1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
    ChessPiece whiteRook2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
    ChessPiece whiteKnight1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
    ChessPiece whiteKnight2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);

    ChessPiece whiteBishop1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
    ChessPiece whiteBishop2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
    ChessPiece whiteQueen1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
    ChessPiece whiteKing1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);

    public ChessBoard() {

    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessBoardArray[position.getRow()-1][position.getColumn()-1] = piece;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        //Might need to use if(obj instanceof class)
        if(chessBoardArray[position.getRow()-1][position.getColumn()-1] != null){
            return chessBoardArray[position.getRow()-1][position.getColumn()-1];
        }
        else return null;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //Remember to reset any pawn promotions

        //Black Pieces
        chessBoardArray[6][0] = blackPawn1;
        chessBoardArray[6][1] = blackPawn2;
        chessBoardArray[6][2] = blackPawn3;
        chessBoardArray[6][3] = blackPawn4;

        chessBoardArray[6][4] = blackPawn5;
        chessBoardArray[6][5] = blackPawn6;
        chessBoardArray[6][6] = blackPawn7;
        chessBoardArray[6][7] = blackPawn8;

        chessBoardArray[7][0] = blackRook1;
        chessBoardArray[7][1] = blackKnight1;
        chessBoardArray[7][2] = blackBishop1;
        chessBoardArray[7][3] = blackQueen1;

        chessBoardArray[7][4] = blackKing1;
        chessBoardArray[7][5] = blackBishop2;
        chessBoardArray[7][6] = blackKnight2;
        chessBoardArray[7][7] = blackRook2;

        //white pieces
        chessBoardArray[1][0] = whitePawn1;
        chessBoardArray[1][1] = whitePawn2;
        chessBoardArray[1][2] = whitePawn3;
        chessBoardArray[1][3] = whitePawn4;

        chessBoardArray[1][4] = whitePawn5;
        chessBoardArray[1][5] = whitePawn6;
        chessBoardArray[1][6] = whitePawn7;
        chessBoardArray[1][7] = whitePawn8;

        chessBoardArray[0][0] = whiteRook1;
        chessBoardArray[0][1] = whiteKnight1;
        chessBoardArray[0][2] = whiteBishop1;
        chessBoardArray[0][3] = whiteQueen1;

        chessBoardArray[0][4] = whiteKing1;
        chessBoardArray[0][5] = whiteBishop2;
        chessBoardArray[0][6] = whiteKnight2;
        chessBoardArray[0][7] = whiteRook2;


        //throw new RuntimeException("Not implemented");
    }
}
