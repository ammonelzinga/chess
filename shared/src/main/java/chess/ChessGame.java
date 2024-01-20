package chess;

import java.util.Collection;

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
    int numWhitePieces;
    int numBlackPieces;
    public ChessGame() {
        //gameBoard.resetBoard();
        playerTurn = TeamColor.WHITE;
        whiteKingPosition = new ChessPosition(1, 5);
        blackKingPosition = new ChessPosition(8, 5);
        numBlackPieces = 16;
        numWhitePieces = 16;
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

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {

        throw new RuntimeException("Not implemented");
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        System.out.print(gameBoard);
        System.out.print("\n");
        System.out.print(gameBoard.getPiece(new ChessPosition(3, 1)));
        ChessPosition KingPosition = new ChessPosition(this.whiteKingPosition.Row, this.whiteKingPosition.Col);
        if (teamColor == TeamColor.BLACK){
            KingPosition.updateRow(this.blackKingPosition.Row);
            KingPosition.updateCol(this.blackKingPosition.Col);
        }
        int numPiecesCheck = 16;
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
                    break;
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
                while (tempRow < 8 && tempCol < 8){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow ++;
                        tempCol ++;
                        firstDiagonal = false;
                    }
                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN))){
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
                while (tempRow < 8 && tempCol > 0){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow ++;
                        tempCol --;
                        firstDiagonal = false;
                    }
                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP) || (firstDiagonal == true &&
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN))){
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
                while (tempRow > 0 && tempCol < 8){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow --;
                        tempCol ++;
                        firstDiagonal = false;
                    }
                    else{
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP)|| (firstDiagonal == true &&
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN))){
                            return true;
                        }
                        else{
                            break;
                        }}}}
            // row--, col -- diagonal queen/bishop
            tempRow = KingPosition.Row-1;
            tempCol = KingPosition.Col-1;
            System.out.print(tempRow);
            System.out.print(tempCol);
            if(i==3){
                firstDiagonal = true;
                while (tempRow > 0 && tempCol >0 ){
                    if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                        tempRow --;
                        tempCol --;
                        firstDiagonal = false;
                        System.out.print(tempRow);
                        System.out.print(tempCol);
                        System.out.print("\n");
                    }

                    else{
                        System.out.print(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor());
                        System.out.print(teamColor);
                        if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                && ((gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.BISHOP)|| (firstDiagonal == true &&
                                gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.PAWN))){
                            return true;
                        }
                        else{
                            break;
                        }}}}
                //check + COL, check to the right horizontally
                if(i ==4){
                    tempRow = KingPosition.Row;
                    tempCol = KingPosition.Col+1;
                    while (tempCol < 8){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempCol ++;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)){
                                return true;
                            }
                            else{
                                break;
                            }}}}
                //check --col, left horizontally
                tempRow = KingPosition.Row;
                tempCol = KingPosition.Col-1;
                if(i==5){
                    while (tempCol > 0){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempCol --;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)){
                                return true;
                            }
                            else{
                                break;
                            }}}}
                //check ++row, check up vertically
                tempRow = KingPosition.Row+1;
                tempCol = KingPosition.Col;
                if(i==6){
                    while (tempRow < 8 && tempCol < 8){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempRow ++;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)){
                                return true;
                            }
                            else{
                                break;
                            }}}}
                // row -- down vertically
                tempRow = KingPosition.Row-1;
                tempCol = KingPosition.Col;
                if(i==7){
                    while (tempRow >0 && tempCol < 8){
                        if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                            tempRow --;
                        }
                        else{
                            if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
                                    && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
                                    gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)){
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
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        gameBoard = board;
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                if(gameBoard.getPiece(new ChessPosition(i, j)) != null){
                    if(gameBoard.getPiece(new ChessPosition(i, j)).pieceType == ChessPiece.PieceType.KING){
                        if(gameBoard.getPiece(new ChessPosition(i, j)).getTeamColor() == TeamColor.BLACK){
                            blackKingPosition.Row = i;
                            blackKingPosition.Col = j;
                        }
                        else{
                            whiteKingPosition.Row = i;
                            whiteKingPosition.Col = j;
                        }}}}}
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
