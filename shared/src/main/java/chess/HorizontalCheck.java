package chess;

public class HorizontalCheck {
    public boolean checkDiagonal(ChessPosition KingPosition, ChessBoard gameBoard, ChessGame.TeamColor teamColor) {
        for (int i=4; i < 8; i++) {
            int tempRow=KingPosition.row;
            int tempCol=KingPosition.Col;
            boolean firstDiagonal=true;
        if(i ==4){
            firstDiagonal = true;
            tempRow = KingPosition.row;
            tempCol = KingPosition.Col+1;
            while (tempCol < 9){
                if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                    tempCol ++;
                    firstDiagonal = false;
                }
                else{
                    if (checkHorizPiece(gameBoard, tempRow, tempCol, teamColor, firstDiagonal)){
                        return true;
                    }
                    else{
                        break;
                    }}}}
        //check --col, left horizontally
        tempRow = KingPosition.row;
        tempCol = KingPosition.Col-1;
        if(i==5){
            firstDiagonal = true;
            while (tempCol > 0){
                if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                    tempCol --;
                    firstDiagonal = false;
                }
                else{
                    if (checkHorizPiece(gameBoard, tempRow, tempCol, teamColor, firstDiagonal)){
                        return true;
                    }
                    else{
                        break;
                    }}}}
        //check ++row, check up vertically
        tempRow = KingPosition.row+1;
        tempCol = KingPosition.Col;
        if(i==6){
            firstDiagonal = true;
            while (tempRow < 9 && tempCol < 9){
                if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                    tempRow ++;
                    firstDiagonal = false;
                }
                else{
                    if (checkHorizPiece(gameBoard, tempRow, tempCol, teamColor, firstDiagonal)){
                        return true;
                    }
                    else{
                        break;
                    }}}}
        // row -- down vertically
        tempRow = KingPosition.row-1;
        tempCol = KingPosition.Col;
        if(i==7){
            firstDiagonal = true;
            while (tempRow >0 && tempCol < 9){
                if(gameBoard.getPiece(new ChessPosition(tempRow, tempCol)) == null){
                    tempRow --;
                    firstDiagonal = false;
                }
                else{
                    if (checkHorizPiece(gameBoard, tempRow, tempCol, teamColor, firstDiagonal)){
                        return true;
                    }
                    else{
                        break;
                    }}}
        }

    }return false;
}
private boolean checkHorizPiece(ChessBoard gameBoard, int tempRow, int tempCol, ChessGame.TeamColor teamColor, boolean firstDiagonal){
    if (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).getTeamColor() != teamColor
            && (gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.QUEEN ||
            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.ROOK)|| (firstDiagonal == true &&
            gameBoard.getPiece(new ChessPosition(tempRow, tempCol)).pieceType == ChessPiece.PieceType.KING)){
        return true;
}return false;}
}
