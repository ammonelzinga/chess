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
        if(gameBoard.getPiece(startPosition) == null){
            return null;
        }
        else{
            System.out.print(startPosition.Row);
            System.out.print(startPosition.Col);
            System.out.print(gameBoard.getPiece((startPosition)).pieceType);
            System.out.print(gameBoard.getPiece((startPosition)).thisPieceColor);
        Collection<ChessMove> potentialMoves = gameBoard.getPiece(startPosition).pieceMoves(gameBoard, startPosition);
        System.out.print(potentialMoves);
        Collection<ChessMove> validMoves = new HashSet<ChessMove>();
        Iterator<ChessMove> iteratePotentialMoves = potentialMoves.iterator();
        while (iteratePotentialMoves.hasNext()){
            ChessMove tempMove = iteratePotentialMoves.next();
            System.out.print("hasnet");
            /*System.out.print("\n");
            System.out.print(tempMove);
            System.out.print(tempMove);
            System.out.print("next");*/
            if(validMove(tempMove)==true){
                validMoves.add(tempMove);
                /*System.out.print("Valid Moves so far");
                System.out.print(validMoves);
                System.out.print("\n");*/
            }
            else{
                System.out.print("invalid move");
            }
        }
        return validMoves;}//throw new RuntimeException("Not implemented");
    }
    public boolean validMove(ChessMove move){
        if(move.getStartPosition().Row < 1 || move.getStartPosition().Row > 8 ||
                move.getEndPosition().Row < 1 || move.getEndPosition().Row > 8
                || move.getStartPosition().Col < 1 || move.getStartPosition().Col > 8 ||
                move.getEndPosition().Col < 1 || move.getEndPosition().Col > 8) {
            System.out.print("out of range");
            return false;
        }
        else{
            boolean hasEndPiece = false;
            if(gameBoard.getPiece(move.getStartPosition()) != null){
                ChessPiece currentPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
                //if (playerTurn == currentPiece.thisPieceColor){
                    //Collection<ChessMove> potentialMoves = currentPiece.pieceMoves(gameBoard, move.getStartPosition());
                    //if(potentialMoves.contains(move.getEndPosition())){
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
                        //gameBoard.toString();
                        gameBoard.chessBoardArray[endRow-1][endCol-1] = null;
                        gameBoard.addPiece(move.getEndPosition(), currentPiece);
                        if(currentPiece.pieceType == ChessPiece.PieceType.KING){
                            if(currentPiece.thisPieceColor==TeamColor.WHITE){
                                System.out.print("\n");
                                System.out.print("white king");
                                whiteKingPosition.Row = endRow;
                                whiteKingPosition.Col = endCol;}
                            else{
                                System.out.print("\n");
                                System.out.print("black king");
                                blackKingPosition.Row = endRow;
                                blackKingPosition.Col = endCol;
                            }}
                        if(this.isInCheck(gameBoard.getPiece(move.getEndPosition()).thisPieceColor)){
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
                            //System.out.print("in check");
                            if(hasEndPiece == true){
                                gameBoard.addPiece(move.getEndPosition(), endPiece);
                            }
                            System.out.print("in check");
                            return false;
                        }
                        else{
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
                            //System.out.print("in check");
                            if(hasEndPiece == true){
                                gameBoard.addPiece(move.getEndPosition(), endPiece);
                            }
                            return true;
                        }
                    }
                    /*else{
                        System.out.print("not a potential move");
                        return false;
                    }}*/
            else{
                System.out.print("no piece here");
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
        if(move.getStartPosition().Row < 1 || move.getStartPosition().Row > 8 ||
                move.getEndPosition().Row < 1 || move.getEndPosition().Row > 8
        || move.getStartPosition().Col < 1 || move.getStartPosition().Col > 8 ||
                move.getEndPosition().Col < 1 || move.getEndPosition().Col > 8) {
            InvalidMoveException exception = new InvalidMoveException("Move not on board");
            throw exception;
        }
        else{
        boolean hasEndPiece = false;
        if(gameBoard.getPiece(move.getStartPosition()) != null){
        ChessPiece currentPiece = new ChessPiece(gameBoard.getPiece(move.getStartPosition()).thisPieceColor, gameBoard.getPiece(move.getStartPosition()).pieceType);
        if (playerTurn == currentPiece.thisPieceColor){
        Collection<ChessMove> potentialMoves = currentPiece.pieceMoves(gameBoard, move.getStartPosition());
        //System.out.print("TO::::");
        //System.out.print(potentialMoves);
        //System.out.print("STRing");
        if(potentialMoves.contains(move)){
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
            //gameBoard.toString();
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
            if(this.isInCheck(playerTurn)){
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
                //System.out.print("in check");
                if(hasEndPiece == true){
                    gameBoard.addPiece(move.getEndPosition(), endPiece);
                }
                InvalidMoveException exception = new InvalidMoveException("Your king is in check");
                throw exception;
            }
            else{
                //System.out.print("not in check");
                if(move.getPromotionPiece() != null){
                    gameBoard.getPiece(move.getEndPosition()).promotePiece(move.getPromotionPiece());}
                if(playerTurn == TeamColor.WHITE){
                    playerTurn = TeamColor.BLACK;
                }
                else{
                    playerTurn = TeamColor.WHITE;
                }
            }
        }
        else{
            System.out.print("not in potential moves");
            InvalidMoveException exception = new InvalidMoveException("Invalid move!");
            throw exception;
        }}
        else{
            InvalidMoveException exception = new InvalidMoveException("It is not this player's turn");
            throw exception;
        }}
        else{
            InvalidMoveException exception = new InvalidMoveException("No piece there to move");
            throw exception;
        }}


        //throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        System.out.print("checker\n");
        System.out.print(teamColor);
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
        //System.out.print(gameBoard);
        //System.out.print("\n");
        //System.out.print(gameBoard.getPiece(new ChessPosition(3, 1)));
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
                    System.out.print("no knight hereeeeeeeee");
                    System.out.print(spotChecker);
                    System.out.print("  ");
                    System.out.print(currentRow);
                    System.out.print(currentColumn);
                    System.out.print("\n");
                    //break;
                }
                else{
                    if(gameBoard.getPiece(tempPosition).getTeamColor() != teamColor &&
                            gameBoard.getPiece(tempPosition).pieceType == ChessPiece.PieceType.KNIGHT){
                        System.out.print("Th3eres a kniight atttttttt");
                        System.out.print(tempPosition.Row);
                        System.out.print(tempPosition.Col);
                        System.out.print("\n");
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
                                System.out.print(validMoves(new ChessPosition(i, j)));
                                System.out.print("ewor moves lo");
                                return false;
                            }
                        }}
                }
            }
            if(isInCheck(TeamColor.WHITE)){
                System.out.print("in check matey");
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
                                System.out.print("Ewor moves lo");
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
