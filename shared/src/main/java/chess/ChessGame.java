package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    ChessBoard gameBoard = new ChessBoard();
    TeamColor playerTurn;
    ChessPosition whiteKingPosition;
    ChessPosition blackKingPosition;
    boolean blackKingMoved;
    boolean whiteKingMoved;
    boolean blackRook8Moved;
    boolean blackRookMoved;
    boolean whiteRook8Moved;
    boolean whiteRookMoved;
    int numWhitePieces;
    int numBlackPieces;

    boolean whiteKingFound;
    boolean blackKingFound;
    public ChessGame() {
        //gameBoard.resetBoard();
        playerTurn = TeamColor.WHITE;
        whiteKingPosition = new ChessPosition(1, 5);
        blackKingPosition = new ChessPosition(8, 5);
        numBlackPieces = 16;
        numWhitePieces = 16;
        blackKingMoved = false;
        whiteKingMoved = false;
        blackRook8Moved = false;
        blackRookMoved = false;
        whiteRook8Moved = false;
        whiteRookMoved = false;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return playerTurn;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {

        playerTurn = team;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    public boolean canCastle (TeamColor color, boolean queenSide){
        System.out.print("\n");
        System.out.print("STarting canCastle");
        System.out.print(gameBoard.chessBoardArray[0][4]);
        System.out.print(gameBoard.chessBoardArray[7][4]);
        boolean canCastle = false;
        if(color == TeamColor.BLACK){
            if(queenSide){
                System.out.print("queenSide");
                if(blackKingMoved == false && blackRookMoved == false){
                    //now check if ending spots are available
                    System.out.print("pieces haven't moved so might be okay");
                    if((gameBoard.getPiece(new ChessPosition(8, 1)) != null && gameBoard.getPiece(new ChessPosition(8, 5)) != null)){
                    if(gameBoard.getPiece(new ChessPosition(8, 1)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(8, 1)).thisPieceColor == TeamColor.BLACK &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).thisPieceColor == TeamColor.BLACK
                        && gameBoard.getPiece(new ChessPosition(8, 4)) == null && gameBoard.getPiece(new ChessPosition(8, 3)) == null &&
                    gameBoard.getPiece(new ChessPosition(8, 2)) == null){
                        System.out.print("\n");
                        System.out.print("row is clear");
                        gameBoard.chessBoardArray[7][4] = null;
                        gameBoard.addPiece(new ChessPosition(8, 4 ), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                        blackKingPosition.Col = 4;
                        if(isInCheck(TeamColor.BLACK) == false){
                            gameBoard.chessBoardArray[7][3] = null;
                            gameBoard.addPiece(new ChessPosition(8, 3), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                            blackKingPosition.Col = 3;
                            if(isInCheck(TeamColor.BLACK) == false){
                                System.out.print("moved King twice");
                                canCastle = true;}
                        else{
                            System.out.print("In Check 2nd move");
                            }}
                        else{
                            System.out.print("In Check 1st move");
                        }
                            gameBoard.addPiece(new ChessPosition(8, 5),new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                            gameBoard.chessBoardArray[7][3] = null;
                            gameBoard.chessBoardArray[7][2] = null;
                            blackKingPosition.Col = 5;}
                        System.out.print("\n");
                        System.out.print("Black QueenSide");
                        System.out.print(gameBoard.chessBoardArray[0][4]);
                        System.out.print(gameBoard.chessBoardArray[7][4]);
                    }}}
            else{
                System.out.print("black King MOved = ");
                System.out.print(blackKingMoved);
                System.out.print("black rook8moved = ");
                System.out.print(blackRook8Moved);
                if(blackKingMoved == false && blackRook8Moved == false){
                    //now check if ending spots are available
                    if((gameBoard.getPiece(new ChessPosition(8, 8)) != null && gameBoard.getPiece(new ChessPosition(8, 5)) != null)){
                    if(gameBoard.getPiece(new ChessPosition(8, 8)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(8, 8)).thisPieceColor == TeamColor.BLACK &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).thisPieceColor == TeamColor.BLACK
                            && gameBoard.getPiece(new ChessPosition(8, 6)) == null && gameBoard.getPiece(new ChessPosition(8, 7)) == null){
                        System.out.print("rows are clear for black king side");
                        gameBoard.chessBoardArray[7][4] = null;
                        gameBoard.addPiece(new ChessPosition(8, 6 ), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                        blackKingPosition.Col = 6;
                        if(isInCheck(TeamColor.BLACK) == false){
                            System.out.print("not in check after first black king move");
                            gameBoard.chessBoardArray[7][5] = null;
                            gameBoard.addPiece(new ChessPosition(8, 7), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                            blackKingPosition.Col = 7;
                            if(isInCheck(TeamColor.BLACK) == false){
                                System.out.print("moved King twice");
                                canCastle = true;}}
                        gameBoard.addPiece(new ChessPosition(8, 5),new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                        gameBoard.chessBoardArray[7][5] = null;
                        gameBoard.chessBoardArray[7][6] = null;
                        blackKingPosition.Col = 5;
                        System.out.print("\n");
                        System.out.print("Black King Side");
                        System.out.print(gameBoard.chessBoardArray[0][4]);
                        System.out.print(gameBoard.chessBoardArray[7][4]);
                    }
                    }}}
        }
        else{
            if(queenSide){
                if(whiteKingMoved == false && whiteRookMoved == false){
                    if((gameBoard.getPiece(new ChessPosition(1, 1)) != null && gameBoard.getPiece(new ChessPosition(1, 5)) != null)){
                    //now check if ending spots are available
                    if(gameBoard.getPiece(new ChessPosition(1, 1)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(1, 1)).thisPieceColor == TeamColor.WHITE &&
                            gameBoard.getPiece(new ChessPosition(1, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(1, 5)).thisPieceColor == TeamColor.WHITE
                            && gameBoard.getPiece(new ChessPosition(1, 4)) == null && gameBoard.getPiece(new ChessPosition(1, 3)) == null &&
                            gameBoard.getPiece(new ChessPosition(1, 2)) == null){
                        gameBoard.chessBoardArray[0][4] = null;
                        gameBoard.addPiece(new ChessPosition(1, 4 ), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        whiteKingPosition.Col = 4;
                        if(isInCheck(TeamColor.WHITE) == false){
                            gameBoard.chessBoardArray[0][3] = null;
                            gameBoard.addPiece(new ChessPosition(1, 3), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                            whiteKingPosition.Col = 3;
                            if(isInCheck(TeamColor.WHITE) == false){
                                System.out.print("moved King twice");
                                canCastle = true;}}
                        gameBoard.addPiece(new ChessPosition(1, 5),new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        gameBoard.chessBoardArray[0][3] = null;
                        gameBoard.chessBoardArray[0][2] = null;
                        whiteKingPosition.Col = 5;
                        System.out.print("\n");
                        System.out.print("White QueenSide");
                        System.out.print(gameBoard.chessBoardArray[0][4]);
                        System.out.print(gameBoard.chessBoardArray[7][4]);
                    }}}}
            else{
                if(whiteKingMoved == false && whiteRook8Moved == false){
                    if((gameBoard.getPiece(new ChessPosition(1, 8)) != null && gameBoard.getPiece(new ChessPosition(1, 5)) != null)){
                    //now check if ending spots are available
                    if(gameBoard.getPiece(new ChessPosition(1, 8)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(1, 8)).thisPieceColor == TeamColor.WHITE &&
                            gameBoard.getPiece(new ChessPosition(1, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(1, 5)).thisPieceColor == TeamColor.WHITE
                            && gameBoard.getPiece(new ChessPosition(1, 6)) == null && gameBoard.getPiece(new ChessPosition(1, 7)) == null){
                        System.out.print("\n");
                        System.out.print("in the clear for white king castle");
                        gameBoard.chessBoardArray[0][4] = null;
                        gameBoard.addPiece(new ChessPosition(1, 6 ), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        whiteKingPosition.Col = 6;
                        if(isInCheck(TeamColor.WHITE) == false){
                            gameBoard.chessBoardArray[0][5] = null;
                            gameBoard.addPiece(new ChessPosition(1, 7), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                            whiteKingPosition.Col = 7;
                            if(isInCheck(TeamColor.WHITE) == false){
                                System.out.print("moved King twice");
                                canCastle = true;}}
                        gameBoard.addPiece(new ChessPosition(1, 5),new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        gameBoard.chessBoardArray[0][6] = null;
                        gameBoard.chessBoardArray[0][5] = null;
                        whiteKingPosition.Col = 5;
                        System.out.print("\n");
                        System.out.print("White kingSide");
                        System.out.print(gameBoard.chessBoardArray[0][4]);
                        System.out.print(gameBoard.chessBoardArray[7][4]);
                    }}}}
        }
        return canCastle;
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */


    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        System.out.print("Player turn:");
        System.out.print(playerTurn);
        System.out.print(startPosition);
        System.out.print("\n");
        System.out.print("validMOVESS");
        //System.out.print(startPosition);
        if(gameBoard.getPiece(startPosition) == null){
            return null;
        }

        else{
            System.out.print(gameBoard.getPiece(startPosition).pieceType);
            System.out.print(gameBoard.getPiece(startPosition).thisPieceColor);
            Collection<ChessMove> validMoves = new HashSet<ChessMove>();
            //CASTLING
            if(gameBoard.getPiece(startPosition).pieceType == ChessPiece.PieceType.KING) {
                    if(gameBoard.getPiece(startPosition).thisPieceColor == TeamColor.BLACK) {
                        if(startPosition.Row == 8 && startPosition.Col == 5){
                        System.out.print("\n");
                        System.out.print("going to check black  castle");
                        if (validMove((new ChessMove(startPosition, new ChessPosition(8, 7), null)))) {
                            System.out.print("\n");
                            System.out.print("Can Black King Side Castleeeeeeeeeeeeeeeeeeeeeeeeee");
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(8, 7), null));}
                        if (validMove(new ChessMove(startPosition, new ChessPosition(8, 3), null))) {
                            System.out.print("\n");
                            System.out.print("Can black queen Side Castleeeeeeeeeeeeeeeeeeeeeeeeeee");
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(8, 3), null));}}}
                    else{
                        if(startPosition.Row == 1 && startPosition.Col == 5){
                        System.out.print("\n");
                        System.out.print("going to check white castle");
                        if (validMove(new ChessMove(startPosition, new ChessPosition(1, 7), null))) {
                            System.out.print("\n");
                            System.out.print("Can white king Side Castleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(1, 7), null));}
                        if (validMove(new ChessMove(startPosition, new ChessPosition(1, 3), null))) {
                            System.out.print("\n");
                            System.out.print("Can white queen Side Castleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(1, 3), null));}}
                }}
            //System.out.print(startPosition);
        Collection<ChessMove> potentialMoves = gameBoard.getPiece(startPosition).pieceMoves(gameBoard, startPosition);

        Iterator<ChessMove> iteratePotentialMoves = potentialMoves.iterator();
        while (iteratePotentialMoves.hasNext()){
            ChessMove tempMove = iteratePotentialMoves.next();
            if(validMove(tempMove)==true){
                validMoves.add(tempMove);
            }
            else{
            }
        }
        System.out.print(validMoves);
        return validMoves;}
    }
    public boolean validMove(ChessMove move){

        System.out.print("\n");
        System.out.print("doin validMove");
        System.out.print("Player turn:");
        System.out.print(playerTurn);

        if(move.getStartPosition().Row < 1 || move.getStartPosition().Row > 8 ||
                move.getEndPosition().Row < 1 || move.getEndPosition().Row > 8
                || move.getStartPosition().Col < 1 || move.getStartPosition().Col > 8 ||
                move.getEndPosition().Col < 1 || move.getEndPosition().Col > 8) {
            return false;
        }
        else{
            boolean hasEndPiece = false;
            if(gameBoard.getPiece(move.getStartPosition()) != null){
                //CASTLING
                if(gameBoard.getPiece(move.getStartPosition()).pieceType == ChessPiece.PieceType.KING) {
                    if (move.getEndPosition().Col - move.getStartPosition().Col == 2) {
                        if(gameBoard.getPiece(move.getStartPosition()).thisPieceColor == TeamColor.BLACK) {
                            System.out.print("\n");
                            System.out.print("going to check black king side castle");
                            if (canCastle(TeamColor.BLACK, false)) {
                                System.out.print("valid move says can castle");
                                return true;
                            }else{
                                System.out.print("valid move says cant castle");
                                return false;}
                        }
                        else{
                            System.out.print("\n");
                            System.out.print("going to check WHITE KING side castle");
                            if (canCastle(TeamColor.WHITE, false)) {
                                System.out.print("valid move says can castle");
                                return true;}
                            else{
                                System.out.print("valid move says cant castle");
                                return false;}
                        }
                        }
                        else if (move.getEndPosition().Col - move.getStartPosition().Col == -2) {
                        if(gameBoard.getPiece(move.getStartPosition()).thisPieceColor == TeamColor.BLACK) {
                            if (canCastle(TeamColor.BLACK, true)) {
                                System.out.print("valid move says can castle");
                                return true;
                            }else{
                                System.out.print("valid move says cant castle");
                                return false;}
                        }
                        else{
                            System.out.print("try white queen side caslte");
                            if (canCastle(TeamColor.WHITE, true)) {
                                System.out.print("valid move says can castle");
                                return true;}
                            else{
                                System.out.print("valid move says cant castle");
                                return false;}
                        }}}

                ChessPiece currentPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);

                        int startRow = move.getStartPosition().Row;
                        int startCol = move.getStartPosition().Col;
                        int endRow = move.getEndPosition().Row;
                        int endCol = move.getEndPosition().Col;
                        ChessPiece endPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
                        if(gameBoard.getPiece((move.getEndPosition()))!=null){
                            endPiece = new ChessPiece(gameBoard.getPiece(move.getEndPosition()).thisPieceColor, gameBoard.getPiece(move.getEndPosition()).pieceType);
                            hasEndPiece = true;
                        }
                        gameBoard.chessBoardArray[startRow-1][startCol-1] = null;
                        gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                        gameBoard.addPiece(move.getEndPosition(), currentPiece);
                        if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                            if(currentPiece.thisPieceColor==TeamColor.WHITE){
                                whiteKingPosition.Row = endRow;
                                whiteKingPosition.Col = endCol;}
                            else{
                                blackKingPosition.Row = endRow;
                                blackKingPosition.Col = endCol;
                            }}
                        if(this.isInCheck(gameBoard.getPiece(move.getEndPosition()).thisPieceColor)){
                            System.out.print("\n");
                            System.out.print("will be in check lo");
                            gameBoard.addPiece(move.getStartPosition(), currentPiece);
                            if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                                if(currentPiece.thisPieceColor==TeamColor.WHITE){
                                    whiteKingPosition.Row = startRow;
                                    whiteKingPosition.Col = startCol;}
                                else{
                                    blackKingPosition.Row = startRow;
                                    blackKingPosition.Col = startCol;
                                }}
                            gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                            if(hasEndPiece == true){
                                gameBoard.addPiece(move.getEndPosition(), endPiece);
                            }
                            return false;
                        }
                        else{
                            System.out.print("\n");
                            System.out.print("yep can move there");
                            gameBoard.addPiece(move.getStartPosition(), currentPiece);
                            if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                                if(currentPiece.thisPieceColor==TeamColor.WHITE){
                                    whiteKingPosition.Row = startRow;
                                    whiteKingPosition.Col = startCol;}
                                else{
                                    blackKingPosition.Row = startRow;
                                    blackKingPosition.Col = startCol;
                                }}
                            gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                            if(hasEndPiece == true){
                                gameBoard.addPiece(move.getEndPosition(), endPiece);
                            }
                            return true;
                        }
                    }
            else{
                return false;
            }}
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        System.out.print("\n");
        System.out.print("makeMoveeee");
        System.out.print("Player turn:");
        System.out.print(playerTurn);

        if(move.getStartPosition().Row < 1 || move.getStartPosition().Row > 8 ||
                move.getEndPosition().Row < 1 || move.getEndPosition().Row > 8
        || move.getStartPosition().Col < 1 || move.getStartPosition().Col > 8 ||
                move.getEndPosition().Col < 1 || move.getEndPosition().Col > 8) {
            InvalidMoveException exception = new InvalidMoveException("Move not on board");
            throw exception;
        }
        else{
            System.out.print("okay make movee");
        //boolean hasEndPiece = false;
        if(gameBoard.getPiece(move.getStartPosition()) != null){
            System.out.print("okay make movee 449");
        ChessPiece currentPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
        if (playerTurn == currentPiece.thisPieceColor){
        //Collection<ChessMove> potentialMoves = currentPiece.pieceMoves(gameBoard, move.getStartPosition());
            System.out.print("checking for valid moves");
            Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
            System.out.print("\n");
            System.out.print("now have validMoves collectionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            System.out.print("\n");
            System.out.print(validMoves);
        if(validMoves.contains(move)){
            int startRow = move.getStartPosition().Row;
            int startCol = move.getStartPosition().Col;
            int endRow = move.getEndPosition().Row;
            int endCol = move.getEndPosition().Col;
            ChessPiece endPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
            /*if(gameBoard.getPiece((move.getEndPosition()))!=null){
                endPiece = new ChessPiece(gameBoard.getPiece(move.getEndPosition()).thisPieceColor, gameBoard.getPiece(move.getEndPosition()).pieceType);
                hasEndPiece = true;
            }*/
            gameBoard.chessBoardArray[startRow-1][startCol-1] = null;
            gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
            gameBoard.addPiece(move.getEndPosition(), currentPiece);
            if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                if(currentPiece.thisPieceColor==TeamColor.WHITE){
                    whiteKingMoved = true;
                    whiteKingPosition.Row = endRow;
                    whiteKingPosition.Col = endCol;}
                else{
                    System.out.print("MOving Black King so now you can't castle black king no more");
                    blackKingMoved = true;
                    blackKingPosition.Row = endRow;
                    blackKingPosition.Col = endCol;
                }}
            /*if(this.isInCheck(playerTurn)){
                gameBoard.addPiece(move.getStartPosition(), currentPiece);
                if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                    if(currentPiece.thisPieceColor==TeamColor.WHITE){
                        whiteKingPosition.Row = startRow;
                        whiteKingPosition.Col = startCol;}
                    else{
                        blackKingPosition.Row = startRow;
                        blackKingPosition.Col = startCol;
                    }}
                gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                if(hasEndPiece == true){
                    gameBoard.addPiece(move.getEndPosition(), endPiece);
                }
                InvalidMoveException exception = new InvalidMoveException("Your king is in check");
                throw exception;
            }*/
            //else{
                if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                if(move.getEndPosition().Col-move.getStartPosition().Col == 2){
                    System.out.print("\n");
                    System.out.print("makeMove for kingside castling");
                    gameBoard.addPiece(new ChessPosition(startRow, endCol -1), new ChessPiece(currentPiece.thisPieceColor, ChessPiece.PieceType.ROOK));
                    if(currentPiece.thisPieceColor == TeamColor.BLACK){
                        gameBoard.chessBoardArray[7][7] = null;
                        blackRook8Moved = true;
                    }
                    else{
                        gameBoard.chessBoardArray[0][7] = null;
                        whiteRook8Moved = true;
                    }


                } else if (move.getEndPosition().Col-move.getStartPosition().Col == -2) {
                    System.out.print("\n");
                    System.out.print("makeMove for queenside castling");
                    gameBoard.addPiece(new ChessPosition(startRow, endCol +1), new ChessPiece(currentPiece.thisPieceColor, ChessPiece.PieceType.ROOK));
                    if(currentPiece.thisPieceColor == TeamColor.BLACK){
                        gameBoard.chessBoardArray[7][0] = null;
                        blackRookMoved = true;
                    }
                    else{
                        gameBoard.chessBoardArray[0][0] = null;
                        whiteRookMoved = true;
                    }
                }}
                if(startRow == 1 && startCol == 1 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                    whiteRookMoved = true;
                }
                if(startRow == 1 && startCol == 8 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                        whiteRook8Moved = true;
                    }
                if(startRow == 8 && startCol == 1 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                        blackRookMoved = true;
                    }
                if(startRow == 8 && startCol == 8 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                        blackRook8Moved = true;
                    }

            if(move.getPromotionPiece() != null){
                    gameBoard.getPiece(move.getEndPosition()).promotePiece(move.getPromotionPiece());}
                if(playerTurn == TeamColor.WHITE){
                    playerTurn = TeamColor.BLACK;
                }
                else{
                    playerTurn = TeamColor.WHITE;
                }
           // }
        }
        else{
            if(gameBoard.getPiece(move.getStartPosition()).pieceType == ChessPiece.PieceType.KING) {
                {

                }
            }
            InvalidMoveException exception = new InvalidMoveException("Invalid move!");
            throw exception;
        }}
        else{
            System.out.print("not this players turn");
            InvalidMoveException exception = new InvalidMoveException("It is not this player's turn");
            throw exception;
        }}
        else{
            InvalidMoveException exception = new InvalidMoveException("No piece there to move");
            throw exception;
        }}
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition KingPosition = new ChessPosition(1, 1);
        if(teamColor == TeamColor.WHITE){
            if(whiteKingFound == false){
                return false;
            }
            else{
                KingPosition.updateRow(this.whiteKingPosition.Row);
                KingPosition.updateCol(this.whiteKingPosition.Col);
            }}
        else{
            if(blackKingFound == false){
                return false;
            }
            else{
                KingPosition.updateRow(this.blackKingPosition.Row);
                KingPosition.updateCol(this.blackKingPosition.Col);
            }
        }
        boolean inCheck = false;
        //first check potential check from knights
        for(int spotChecker = 0; spotChecker < 8; spotChecker++){
            int currentRow = KingPosition.Row;
            int currentColumn = KingPosition.Col;
            ChessPosition tempPosition = new ChessPosition(currentRow, currentColumn);
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
                if(gameBoard.getPiece(tempPosition) == null ){
                }
                else{
                    if(gameBoard.getPiece(tempPosition).getTeamColor() != teamColor &&
                            gameBoard.getPiece(tempPosition).pieceType == ChessPiece.PieceType.KNIGHT){
                        return true;}}}
        }

        //potential check from diagonal
        for(int i = 0; i < 8; i ++){
            int tempRow = KingPosition.Row;
            int tempCol = KingPosition.Col;
            boolean firstDiagonal = true;
            //check ++ row, ++ col Diagonal, queen/bishop
            if(i ==0){
                tempRow = KingPosition.Row +1;
                tempCol = KingPosition.Col + 1;
                while (tempRow < 9 && tempCol < 9){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow ++;
                        tempCol ++;
                        firstDiagonal = false;
                    }
                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                                (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                                        gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))){
                            return true;
                        }
                        else{
                            break;
                        }}}}
            //check ++ row, -- col Diagonal, queen/bishop
            tempRow = KingPosition.Row+1;
            tempCol = KingPosition.Col-1;
            if(i==1){
                firstDiagonal = true;
                while (tempRow < 9 && tempCol > 0){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow ++;
                        tempCol --;
                        firstDiagonal = false;
                    }
                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                                (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                                        gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))){
                            return true;
                        }
                        else{
                            break;
                        }}}}
            //check -- row, ++ col diagonal, queen/bishop
            tempRow = KingPosition.Row-1;
            tempCol = KingPosition.Col+1;
            if(i==2){
                firstDiagonal = true;
                while (tempRow > 0 && tempCol < 9){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow --;
                        tempCol ++;
                        firstDiagonal = false;
                    }
                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP)|| (firstDiagonal == true &&
                                (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                                        gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))){
                            return true;
                        }
                        else{
                            break;
                        }}}}
            // row--, col -- diagonal queen/bishop
            tempRow = KingPosition.Row-1;
            tempCol = KingPosition.Col-1;
            if(i==3){
                firstDiagonal = true;
                while (tempRow > 0 && tempCol >0 ){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow --;
                        tempCol --;
                        firstDiagonal = false;
                    }

                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP)|| (firstDiagonal == true &&
                                (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN ||
                        gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)))){
                            return true;
                        }
                        else{
                            break;
                        }}}}
                //check + COL, check to the right horizontally
                if(i ==4){
                    firstDiagonal = true;
                    tempRow = KingPosition.Row;
                    tempCol = KingPosition.Col+1;
                    while (tempCol < 9){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempCol ++;
                            firstDiagonal = false;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)|| (firstDiagonal == true &&
                                            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)){
                                return true;
                            }
                            else{
                                break;
                            }}}}
                //check --col, left horizontally
                tempRow = KingPosition.Row;
                tempCol = KingPosition.Col-1;
                if(i==5){
                    firstDiagonal = true;
                    while (tempCol > 0){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempCol --;
                            firstDiagonal = false;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)|| (firstDiagonal == true &&
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)){
                                return true;
                            }
                            else{
                                break;
                            }}}}
                //check ++row, check up vertically
                tempRow = KingPosition.Row+1;
                tempCol = KingPosition.Col;
                if(i==6){
                    firstDiagonal = true;
                    while (tempRow < 9 && tempCol < 9){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempRow ++;
                            firstDiagonal = false;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)|| (firstDiagonal == true &&
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)){
                                return true;
                            }
                            else{
                                break;
                            }}}}
                // row -- down vertically
                tempRow = KingPosition.Row-1;
                tempCol = KingPosition.Col;
                if(i==7){
                    firstDiagonal = true;
                    while (tempRow >0 && tempCol < 9){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempRow --;
                            firstDiagonal = false;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)|| (firstDiagonal == true &&
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)){
                                return true;
                            }
                            else{
                                break;
                            }}}
            }

        }
        return inCheck;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(teamColor == TeamColor.WHITE){
            for(int i = 1; i < 9; i ++){
                for(int j = 1; j < 9; j ++){
                    if(gameBoard.getPiece((new ChessPosition(i,j))) != null){
                    if(gameBoard.getPiece(new ChessPosition(i, j)).thisPieceColor == TeamColor.WHITE){
                        if(validMoves(new ChessPosition(i, j)).isEmpty() == false){
                            return false;
                        }
                    }}
                }
            }
            return isInCheck(TeamColor.WHITE);
        }
        else{
            for(int i = 1; i < 9; i ++){
                for(int j = 1; j < 9; j ++){
                    if(gameBoard.getPiece((new ChessPosition(i,j))) != null){
                    if(gameBoard.getPiece(new ChessPosition(i, j)).thisPieceColor == TeamColor.BLACK){
                        if(validMoves(new ChessPosition(i, j)).isEmpty() == false){
                            return false;}}}}}
            return isInCheck(TeamColor.BLACK);
        }
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if(teamColor == TeamColor.WHITE){
            for(int i = 1; i < 9; i ++){
                for(int j = 1; j < 9; j ++){
                    if(gameBoard.getPiece((new ChessPosition(i,j))) != null){
                        if(gameBoard.getPiece(new ChessPosition(i, j)).thisPieceColor == TeamColor.WHITE){
                            if(validMoves(new ChessPosition(i, j)).isEmpty() == false){
                                return false;
                            }
                        }}
                }
            }
            if(isInCheck(TeamColor.WHITE)){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            for(int i = 1; i < 9; i ++){
                for(int j = 1; j < 9; j ++){
                    if(gameBoard.getPiece((new ChessPosition(i,j))) != null){
                        if(gameBoard.getPiece(new ChessPosition(i, j)).thisPieceColor == TeamColor.BLACK){
                            if(validMoves(new ChessPosition(i, j)).isEmpty() == false){
                                return false;}}}}}
            if(isInCheck(TeamColor.BLACK)){
                return false;
            }
            else{
                return true;
            }
        }
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        numBlackPieces = 16;
        numWhitePieces = 16;
        blackKingMoved = false;
        whiteKingMoved = false;
        blackRook8Moved = false;
        blackRookMoved = false;
        whiteRook8Moved = false;
        whiteRookMoved = false;
        gameBoard = board;
        whiteKingFound = false;
        blackKingFound = false;
        for(int i = 1; i < 9; i++){
            if(whiteKingFound && blackKingFound){
                break;
            }
            for(int j = 1; j < 9; j++){
                if(whiteKingFound && blackKingFound){
                    break;
                }
                if(gameBoard.getPiece(new ChessPosition(i, j)) != null){
                    if(gameBoard.getPiece(new ChessPosition(i, j)).pieceType == ChessPiece.PieceType.KING){
                        if(gameBoard.getPiece(new ChessPosition(i, j)).getTeamColor() == TeamColor.BLACK){
                            blackKingPosition.Row = i;
                            blackKingPosition.Col = j;
                            blackKingFound = true;
                        }
                        else{
                            whiteKingPosition.Row = i;
                            whiteKingPosition.Col = j;
                            whiteKingFound = true;
                        }}}}

        }
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return gameBoard;
        //throw new RuntimeException("Not implemented");
    }
}
