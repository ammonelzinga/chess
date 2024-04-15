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
    boolean gameOver = false;
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

    ChessPosition whitePawnAdvance2Now;
    ChessPosition blackPawnAdvance2Now;

    boolean whiteKingFound;
    boolean blackKingFound;
    public ChessGame() {
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
        whitePawnAdvance2Now = null;
        blackPawnAdvance2Now = null;
    }
    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return playerTurn;
    }
    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {

        playerTurn = team;
    }
    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }
    public boolean canCastle (TeamColor color, boolean queenSide){
        boolean canCastle = false;
        if(color == TeamColor.BLACK){
            if(queenSide){
                if(blackKingMoved == false && blackRookMoved == false){

                    if((gameBoard.getPiece(new ChessPosition(8, 1)) != null && gameBoard.getPiece(new ChessPosition(8, 5)) != null)){
                    if(gameBoard.getPiece(new ChessPosition(8, 1)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(8, 1)).thisPieceColor == TeamColor.BLACK &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).thisPieceColor == TeamColor.BLACK
                        && gameBoard.getPiece(new ChessPosition(8, 4)) == null && gameBoard.getPiece(new ChessPosition(8, 3)) == null &&
                    gameBoard.getPiece(new ChessPosition(8, 2)) == null){
                        gameBoard.chessBoardArray[7][4] = null;
                        gameBoard.addPiece(new ChessPosition(8, 4 ), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                        blackKingPosition.Col = 4;
                        if(isInCheck(TeamColor.BLACK) == false){
                            gameBoard.chessBoardArray[7][3] = null;
                            gameBoard.addPiece(new ChessPosition(8, 3), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                            blackKingPosition.Col = 3;
                            if(isInCheck(TeamColor.BLACK) == false){
                                canCastle = true;}}
                            gameBoard.addPiece(new ChessPosition(8, 5),new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                            gameBoard.chessBoardArray[7][3] = null;
                            gameBoard.chessBoardArray[7][2] = null;
                            blackKingPosition.Col = 5;}}}}
            else{
                if(blackKingMoved == false && blackRook8Moved == false){

                    if((gameBoard.getPiece(new ChessPosition(8, 8)) != null && gameBoard.getPiece(new ChessPosition(8, 5)) != null)){
                    if(gameBoard.getPiece(new ChessPosition(8, 8)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(8, 8)).thisPieceColor == TeamColor.BLACK &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(8, 5)).thisPieceColor == TeamColor.BLACK
                            && gameBoard.getPiece(new ChessPosition(8, 6)) == null && gameBoard.getPiece(new ChessPosition(8, 7)) == null){
                        gameBoard.chessBoardArray[7][4] = null;
                        gameBoard.addPiece(new ChessPosition(8, 6 ), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                        blackKingPosition.Col = 6;
                        if(isInCheck(TeamColor.BLACK) == false){
                            gameBoard.chessBoardArray[7][5] = null;
                            gameBoard.addPiece(new ChessPosition(8, 7), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                            blackKingPosition.Col = 7;
                            if(isInCheck(TeamColor.BLACK) == false){
                                canCastle = true;}}
                        gameBoard.addPiece(new ChessPosition(8, 5),new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.KING));
                        gameBoard.chessBoardArray[7][5] = null;
                        gameBoard.chessBoardArray[7][6] = null;
                        blackKingPosition.Col = 5;}}}}}
        else{
            if(queenSide){
                if(whiteKingMoved == false && whiteRookMoved == false){
                    if((gameBoard.getPiece(new ChessPosition(1, 1)) != null && gameBoard.getPiece(new ChessPosition(1, 5)) != null)){

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
                                canCastle = true;}}
                        gameBoard.addPiece(new ChessPosition(1, 5),new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        gameBoard.chessBoardArray[0][3] = null;
                        gameBoard.chessBoardArray[0][2] = null;
                        whiteKingPosition.Col = 5;
                    }}}}
            else{
                if(whiteKingMoved == false && whiteRook8Moved == false){
                    if((gameBoard.getPiece(new ChessPosition(1, 8)) != null && gameBoard.getPiece(new ChessPosition(1, 5)) != null)){

                    if(gameBoard.getPiece(new ChessPosition(1, 8)).pieceType == ChessPiece.PieceType.ROOK &&
                            gameBoard.getPiece(new ChessPosition(1, 8)).thisPieceColor == TeamColor.WHITE &&
                            gameBoard.getPiece(new ChessPosition(1, 5)).pieceType == ChessPiece.PieceType.KING &&
                            gameBoard.getPiece(new ChessPosition(1, 5)).thisPieceColor == TeamColor.WHITE
                            && gameBoard.getPiece(new ChessPosition(1, 6)) == null && gameBoard.getPiece(new ChessPosition(1, 7)) == null){
                        gameBoard.chessBoardArray[0][4] = null;
                        gameBoard.addPiece(new ChessPosition(1, 6 ), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        whiteKingPosition.Col = 6;
                        if(isInCheck(TeamColor.WHITE) == false){
                            gameBoard.chessBoardArray[0][5] = null;
                            gameBoard.addPiece(new ChessPosition(1, 7), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                            whiteKingPosition.Col = 7;
                            if(isInCheck(TeamColor.WHITE) == false){
                                canCastle = true;}}
                        gameBoard.addPiece(new ChessPosition(1, 5),new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.KING));
                        gameBoard.chessBoardArray[0][6] = null;
                        gameBoard.chessBoardArray[0][5] = null;
                        whiteKingPosition.Col = 5;
                    }}}}}
        return canCastle;}

    public boolean canEnPass(ChessMove move, TeamColor color){

        boolean canEnPass = false;
        int startRow = move.getStartPosition().row;
        int startCol = move.getStartPosition().Col;
        int endRow = move.getEndPosition().row;
        int endCol = move.getEndPosition().Col;

        if(color == TeamColor.BLACK){
            if(whitePawnAdvance2Now != null){
            if(whitePawnAdvance2Now.row == 4 && startRow == 4 && ((startCol - whitePawnAdvance2Now.Col == 1) ||
                    startCol - whitePawnAdvance2Now.Col == -1)){
                System.out.print("initals spacing check good");
                if(endCol == whitePawnAdvance2Now.Col && endRow == 3){

                System.out.print("okay spacing's good for black to en passant");
                gameBoard.chessBoardArray[startRow -1][startCol -1] = null;
                gameBoard.chessBoardArray[whitePawnAdvance2Now.row -1][whitePawnAdvance2Now.Col -1] = null;
                gameBoard.addPiece(new ChessPosition(endRow, endCol), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.PAWN));
                if(isInCheck(TeamColor.BLACK)==false){
                    canEnPass = true;
                }
                gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                gameBoard.addPiece(new ChessPosition(startRow, startCol), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.PAWN));
                gameBoard.addPiece(new ChessPosition(whitePawnAdvance2Now.row, whitePawnAdvance2Now.Col), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.PAWN));

            }}
        }}
        else{
            if(blackPawnAdvance2Now != null){
                if(blackPawnAdvance2Now.row == 5 && startRow == 5 && ((startCol - blackPawnAdvance2Now.Col == 1) ||
                        startCol - blackPawnAdvance2Now.Col == -1) && endCol == blackPawnAdvance2Now.Col && endRow == 6){
                    gameBoard.chessBoardArray[startRow -1][startCol -1] = null;
                    gameBoard.chessBoardArray[blackPawnAdvance2Now.row -1][blackPawnAdvance2Now.Col -1] = null;
                    gameBoard.addPiece(new ChessPosition(endRow, endCol), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.PAWN));
                    if(isInCheck(TeamColor.WHITE)==false){
                        canEnPass = true;
                    }
                    gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                    gameBoard.addPiece(new ChessPosition(startRow, startCol), new ChessPiece(TeamColor.WHITE, ChessPiece.PieceType.PAWN));
                    gameBoard.addPiece(new ChessPosition(blackPawnAdvance2Now.row, blackPawnAdvance2Now.Col), new ChessPiece(TeamColor.BLACK, ChessPiece.PieceType.PAWN));

                }
            }

        }

    return canEnPass;
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */


    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(gameBoard.getPiece(startPosition) == null){
            return null;
        }

        else{
            Collection<ChessMove> validMoves = new HashSet<ChessMove>();

            if(gameBoard.getPiece(startPosition).pieceType == ChessPiece.PieceType.KING) {
                    if(gameBoard.getPiece(startPosition).thisPieceColor == TeamColor.BLACK) {
                        if(startPosition.row == 8 && startPosition.Col == 5){
                        if (validMove((new ChessMove(startPosition, new ChessPosition(8, 7), null)))) {
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(8, 7), null));}
                        if (validMove(new ChessMove(startPosition, new ChessPosition(8, 3), null))) {
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(8, 3), null));}}}
                    else{
                        if(startPosition.row == 1 && startPosition.Col == 5){
                        if (validMove(new ChessMove(startPosition, new ChessPosition(1, 7), null))) {
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(1, 7), null));}
                        if (validMove(new ChessMove(startPosition, new ChessPosition(1, 3), null))) {
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(1, 3), null));}}
                }}

            if(gameBoard.getPiece(startPosition).pieceType == ChessPiece.PieceType.PAWN){
                if(gameBoard.getPiece(startPosition).thisPieceColor == TeamColor.BLACK) {
                    if(startPosition.row == 4 && whitePawnAdvance2Now != null){
                        if(canEnPass(new ChessMove(startPosition, new ChessPosition(whitePawnAdvance2Now.row-1, whitePawnAdvance2Now.Col), null), TeamColor.BLACK)){
                            System.out.print("black can en pass");
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(whitePawnAdvance2Now.row-1, whitePawnAdvance2Now.Col), null));
                        }
                    }
                }
                else{
                    if(startPosition.row == 5 && blackPawnAdvance2Now != null){
                        if(canEnPass(new ChessMove(startPosition, new ChessPosition(blackPawnAdvance2Now.row+1, blackPawnAdvance2Now.Col), null), TeamColor.WHITE)){
                            System.out.print("white can en pass");
                            validMoves.add(new ChessMove(startPosition, new ChessPosition(blackPawnAdvance2Now.row+1, blackPawnAdvance2Now.Col), null));
                        }
                    }
                }

            }
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
        return validMoves;}
    }
    public boolean validMove(ChessMove move){
        if(move.getStartPosition().row < 1 || move.getStartPosition().row > 8 ||
                move.getEndPosition().row < 1 || move.getEndPosition().row > 8
                || move.getStartPosition().Col < 1 || move.getStartPosition().Col > 8 ||
                move.getEndPosition().Col < 1 || move.getEndPosition().Col > 8) {
            return false;
        }
        else{
            boolean hasEndPiece = false;
            if(gameBoard.getPiece(move.getStartPosition()) != null){

                if(gameBoard.getPiece(move.getStartPosition()).pieceType == ChessPiece.PieceType.KING) {
                    if (move.getEndPosition().Col - move.getStartPosition().Col == 2) {
                        if(gameBoard.getPiece(move.getStartPosition()).thisPieceColor == TeamColor.BLACK) {
                            if (canCastle(TeamColor.BLACK, false)) {
                                return true;
                            }else{
                                return false;}
                        }
                        else{
                            if (canCastle(TeamColor.WHITE, false)) {
                                return true;}
                            else{
                                return false;}
                        }
                        }
                        else if (move.getEndPosition().Col - move.getStartPosition().Col == -2) {
                        if(gameBoard.getPiece(move.getStartPosition()).thisPieceColor == TeamColor.BLACK) {
                            if (canCastle(TeamColor.BLACK, true)) {
                                return true;
                            }else{
                                return false;}
                        }
                        else{
                            if (canCastle(TeamColor.WHITE, true)) {
                                return true;}
                            else{
                                return false;}
                        }}}

                ChessPiece currentPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);

                        int startRow = move.getStartPosition().row;
                        int startCol = move.getStartPosition().Col;
                        int endRow = move.getEndPosition().row;
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
                                whiteKingPosition.row = endRow;
                                whiteKingPosition.Col = endCol;}
                            else{
                                blackKingPosition.row = endRow;
                                blackKingPosition.Col = endCol;
                            }}
                        if(this.isInCheck(gameBoard.getPiece(move.getEndPosition()).thisPieceColor)){
                            gameBoard.addPiece(move.getStartPosition(), currentPiece);
                            if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                                if(currentPiece.thisPieceColor==TeamColor.WHITE){
                                    whiteKingPosition.row = startRow;
                                    whiteKingPosition.Col = startCol;}
                                else{
                                    blackKingPosition.row = startRow;
                                    blackKingPosition.Col = startCol;
                                }}
                            gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                            if(hasEndPiece == true){
                                gameBoard.addPiece(move.getEndPosition(), endPiece);
                            }
                            return false;
                        }
                        else{
                            gameBoard.addPiece(move.getStartPosition(), currentPiece);
                            if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                                if(currentPiece.thisPieceColor==TeamColor.WHITE){
                                    whiteKingPosition.row = startRow;
                                    whiteKingPosition.Col = startCol;}
                                else{
                                    blackKingPosition.row = startRow;
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
        if(gameOver || move.getStartPosition().row < 1 || move.getStartPosition().row > 8 || move.getEndPosition().row < 1 || move.getEndPosition().row > 8
        || move.getStartPosition().Col < 1 || move.getStartPosition().Col > 8 || move.getEndPosition().Col < 1 || move.getEndPosition().Col > 8) {
            InvalidMoveException exception = new InvalidMoveException("Move not on board");
            throw exception;
        }
        else{
            MakeMovePawn pawnLogic = new MakeMovePawn();
        if(gameBoard.getPiece(move.getStartPosition()) != null){
        ChessPiece currentPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
        if (playerTurn == currentPiece.thisPieceColor){
            Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        if(validMoves.contains(move)){
            int startRow = move.getStartPosition().row;
            int startCol = move.getStartPosition().Col;
            int endRow = move.getEndPosition().row;
            int endCol = move.getEndPosition().Col;
            ChessPiece endPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
            gameBoard.chessBoardArray[startRow-1][startCol-1] = null;
            gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
            gameBoard.addPiece(move.getEndPosition(), currentPiece);
            if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                if(currentPiece.thisPieceColor==TeamColor.WHITE){
                    whiteKingMoved = true;
                    whiteKingPosition.row = endRow;
                    whiteKingPosition.Col = endCol;}
                else{
                    blackKingMoved = true;
                    blackKingPosition.row = endRow;
                    blackKingPosition.Col = endCol;
                }}

                if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                if(move.getEndPosition().Col-move.getStartPosition().Col == 2){
                    gameBoard.addPiece(new ChessPosition(startRow, endCol -1), new ChessPiece(currentPiece.thisPieceColor, ChessPiece.PieceType.ROOK));
                    if(currentPiece.thisPieceColor == TeamColor.BLACK){
                        gameBoard.chessBoardArray[7][7] = null;
                        blackRook8Moved = true;}
                    else{
                        gameBoard.chessBoardArray[0][7] = null;
                        whiteRook8Moved = true;}
                } else if (move.getEndPosition().Col-move.getStartPosition().Col == -2) {
                    gameBoard.addPiece(new ChessPosition(startRow, endCol +1), new ChessPiece(currentPiece.thisPieceColor, ChessPiece.PieceType.ROOK));
                    if(currentPiece.thisPieceColor == TeamColor.BLACK){
                        gameBoard.chessBoardArray[7][0] = null;
                        blackRookMoved = true;}
                    else{
                        gameBoard.chessBoardArray[0][0] = null;
                        whiteRookMoved = true;}
                }}
            if(currentPiece.pieceType == ChessPiece.PieceType.PAWN){
                pawnLogic.pawnLogic(currentPiece, gameBoard, startRow, startCol, endRow, endCol, whitePawnAdvance2Now, blackPawnAdvance2Now);
            }
                if(startRow == 1 && startCol == 1 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                    whiteRookMoved = true;}
                if(startRow == 1 && startCol == 8 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                        whiteRook8Moved = true;}
                if(startRow == 8 && startCol == 1 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                        blackRookMoved = true;}
                if(startRow == 8 && startCol == 8 && currentPiece.pieceType == ChessPiece.PieceType.ROOK){
                        blackRook8Moved = true;}
            if(move.getPromotionPiece() != null){
                    gameBoard.getPiece(move.getEndPosition()).promotePiece(move.getPromotionPiece());}
                if(playerTurn == TeamColor.WHITE){
                    whitePawnAdvance2Now = null;
                    playerTurn = TeamColor.BLACK;
                    if(currentPiece.pieceType == ChessPiece.PieceType.PAWN){
                        if(endRow - startRow == 2){
                            System.out.print("white pawn advance 2 = true");
                            whitePawnAdvance2Now = new ChessPosition(endRow, endCol);}}
                }
                else{
                    playerTurn = TeamColor.WHITE;
                    blackPawnAdvance2Now = null;
                    if(currentPiece.pieceType == ChessPiece.PieceType.PAWN){
                        if(endRow - startRow == -2){
                            System.out.print("black pawn advance 2 = true");
                            blackPawnAdvance2Now = new ChessPosition(endRow, endCol);}}}
        }
        else{
            InvalidMoveException exception = new InvalidMoveException("Invalid move!");
            throw exception;}}
        else{
            InvalidMoveException exception = new InvalidMoveException("It is not this player's turn");
            throw exception;
        }}
        else{
            InvalidMoveException exception = new InvalidMoveException("No piece there to move");
            throw exception;}}
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        DiagonalCheck diagonalCheck = new DiagonalCheck();
        HorizontalCheck horizontalCheck = new HorizontalCheck();
        ChessPosition kingPosition = new ChessPosition(1, 1);
        if(teamColor == TeamColor.WHITE){
            if(whiteKingFound == false){
                return false;
            }
            else{
                kingPosition.updateRow(this.whiteKingPosition.row);
                kingPosition.updateCol(this.whiteKingPosition.Col);
            }}
        else{
            if(blackKingFound == false){
                return false;
            }
            else{
                kingPosition.updateRow(this.blackKingPosition.row);
                kingPosition.updateCol(this.blackKingPosition.Col);
            }
        }
        boolean inCheck = false;

        for(int spotChecker = 0; spotChecker < 8; spotChecker++){
            int currentRow = kingPosition.row;
            int currentColumn = kingPosition.Col;
            ChessPosition tempPosition = new ChessPosition(currentRow, currentColumn);
            if(spotChecker == 0){
                tempPosition.updateRow(currentRow + 2);
                tempPosition.updateCol(currentColumn + 1);
            }
            if(spotChecker == 1){
                tempPosition.updateCol(currentColumn-1);
                tempPosition.updateRow((currentRow+2));
            }

            if(spotChecker == 2){
                tempPosition.updateCol(currentColumn-1);
                tempPosition.updateRow((currentRow-2));
            }
            if(spotChecker == 3){
                tempPosition.updateCol(currentColumn+1);
                tempPosition.updateRow((currentRow-2));
            }

            if(spotChecker == 4){
                tempPosition.updateCol(currentColumn+2);
                tempPosition.updateRow((currentRow+1));
            }
            if(spotChecker == 5){
                tempPosition.updateCol(currentColumn+2);
                tempPosition.updateRow((currentRow-1));
            }

            if(spotChecker == 6){
                tempPosition.updateCol(currentColumn-2);
                tempPosition.updateRow(currentRow+1);
            }
            if(spotChecker == 7){
                tempPosition.updateCol(currentColumn-2);
                tempPosition.updateRow((currentRow-1));
            }
            if(tempPosition.row < 9 && tempPosition.row > 0 && tempPosition.Col < 9 && tempPosition.Col > 0){
                if(gameBoard.getPiece(tempPosition) == null ){
                }
                else{
                    if(gameBoard.getPiece(tempPosition).getTeamColor() != teamColor &&
                            gameBoard.getPiece(tempPosition).pieceType == ChessPiece.PieceType.KNIGHT){
                        return true;}}}
        }
        if(diagonalCheck.checkDiagonal(kingPosition, gameBoard, teamColor)){
            return true;
        }
        if(horizontalCheck.checkDiagonal(kingPosition, gameBoard, teamColor)){
            return true;
        }
        return inCheck;

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

    }
    public void updateGameOver(boolean isOver){
        if(isOver){
            gameOver = true;
        }
        else{
            gameOver = false;
        }
    }
    public boolean getGameOverStatus(){
        return gameOver;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        whitePawnAdvance2Now = null;
        blackPawnAdvance2Now = null;
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
                            blackKingPosition.row = i;
                            blackKingPosition.Col = j;
                            blackKingFound = true;
                        }
                        else{
                            whiteKingPosition.row = i;
                            whiteKingPosition.Col = j;
                            whiteKingFound = true;
                        }}}}

        }

    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return gameBoard;
    }
}
