package Play;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static ui.EscapeSequences.*;
public class DrawChessGame {
  ChessGame game;
  private static final int BOARD_SIZE_IN_SQUARES=8;
  private static final int SQUARE_SIZE_IN_CHARS=1;
  private static final int LINE_WIDTH_IN_CHARS=1;

  public Collection<ChessPosition> validPositions;
  public DrawChessGame(ChessGame gamee) {
    game=gamee;}
  public void updateGame(ChessGame gamee) {
    game=gamee;
  }
  public void main(boolean blackTop, boolean highlight) {
    var out=new PrintStream(System.out, true, StandardCharsets.UTF_8);
    drawBoard(out, blackTop, highlight);
    out.print(SET_BG_COLOR_DARK_GREY);}
  private void drawBoard(PrintStream out, boolean blackTop, boolean highlight) {
    if(blackTop){
    boolean white = false;
    for (int boardRow=9; boardRow > -1; boardRow--) {
      drawRowOfSquares(out, boardRow, white, blackTop, highlight);
      white = !white;
      out.print(SET_BG_COLOR_LIGHT_GREY);}}
    else{
      boolean white = false;
      for (int boardRow=0; boardRow < 10; boardRow++) {
        drawRowOfSquares(out, boardRow, white, blackTop, highlight);
        white = !white;
        out.print(SET_BG_COLOR_LIGHT_GREY);}}}
  private void printCol(PrintStream out, int col, boolean blackTop){
    //if(blackTop){
      out.print(SET_BG_COLOR_DARK_GREY);
      out.print(SET_TEXT_COLOR_WHITE);
      switch (col){
        case 1:
          out.print(" 1.");
          break;
        case 2:
          out.print(" 2.");
          break;
        case 3:
          out.print(" 3.");
          break;
        case 4:
          out.print(" 4.");
          break;
        case 5:
          out.print(" 5.");
          break;
        case 6:
          out.print(" 6.");
          break;
        case 7:
          out.print(" 7.");
          break;
        case 8:
          out.print(" 8.");
          break;
      }}
  private void printRow(PrintStream out, int row, int col, boolean blackTop){
    if(blackTop){
      out.print(SET_BG_COLOR_DARK_GREY);
      out.print(SET_TEXT_COLOR_WHITE);
      switch (col){
        case 1:
          out.print("a. ");
          break;
        case 2:
          out.print(" b.");
          break;
        case 3:
          out.print(" c. ");
          break;
        case 4:
          out.print(" d.");
          break;
        case 5:
          out.print("  e. ");
          break;
        case 6:
          out.print(" f.");
          break;
        case 7:
          out.print(" g. ");
          break;
        case 8:
          out.print(" h.");
          break;
      }}
    else{
      out.print(SET_BG_COLOR_DARK_GREY);
      out.print(SET_TEXT_COLOR_WHITE);
      switch (col){
        case 1:
          out.print("h.");
          break;
        case 2:
          out.print(" g. ");
          break;
        case 3:
          out.print(" f.");
          break;
        case 4:
          out.print("  e. ");
          break;
        case 5:
          out.print(" d.");
          break;
        case 6:
          out.print(" c. ");
          break;
        case 7:
          out.print(" b.");
          break;
        case 8:
          out.print(" a.");
          break;
      }}
  }
  private void drawRowHelper(int squareRow, PrintStream out, int row, int boardCol, boolean blackTop){
    if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
      int prefixLength=SQUARE_SIZE_IN_CHARS / 2;
      int suffixLength=SQUARE_SIZE_IN_CHARS - prefixLength - 1;
      out.print(EMPTY.repeat(prefixLength));
      printRow(out, row, boardCol, blackTop);
      out.print(EMPTY.repeat(suffixLength));
    } else {
      out.print(EMPTY.repeat(SQUARE_SIZE_IN_CHARS));
    }
  }
  private void drawRowOfSquares(PrintStream out, int row, boolean whitee, boolean blackTop, boolean highlight) {
    if(row == 0 || row ==9){
      out.print(SET_BG_COLOR_DARK_GREY);
      out.print(EMPTY);
      for (int squareRow=0; squareRow < 1; squareRow++) {
        for (int boardCol=0; boardCol < 10; ++boardCol) {
          out.print(SET_BG_COLOR_DARK_GREY);
          out.print(SET_TEXT_COLOR_BLACK);
          drawRowHelper(squareRow, out, row, boardCol, blackTop);}}
    out.println();}
    if(row < 9 && row > 0){
    if(blackTop){
    boolean white=whitee;
    boolean whitewhite=true;
    for (int squareRow=0; squareRow < 1; squareRow++) {
      for (int boardCol=0; boardCol < 10; ++boardCol) {
        if(boardCol == 0 || boardCol == 9){
          printCol(out, row, blackTop);}
        if(boardCol > 0 && boardCol < 9) {
          whitewhite=white;
          if (white) {
            ChessPosition tempPosition = new ChessPosition(row, boardCol);
            if(highlight && validPositions.contains(tempPosition)){
              out.print(SET_BG_COLOR_YELLOW);}
            else{out.print(SET_BG_COLOR_WHITE);}
            out.print(SET_TEXT_COLOR_WHITE);
            if (boardCol != 8) {
              white=false;}
          } else {
            ChessPosition tempPosition = new ChessPosition(row, boardCol);
            if(highlight && validPositions.contains(tempPosition)){
              out.print(SET_BG_COLOR_GREEN);}
            else{out.print(SET_BG_COLOR_BLACK);}
            out.print(SET_TEXT_COLOR_BLACK);
            if (boardCol != 8) {
              white=true;}}
          drawRowHelper(squareRow, out, row, boardCol, blackTop);
          out.print(SET_BG_COLOR_DARK_GREY);
          out.print(SET_TEXT_COLOR_DARK_GREY);}
    }out.println();}}
    else{
      boolean white=whitee;
      boolean whitewhite=true;
      for (int squareRow=0; squareRow < 1; squareRow++) {
        for (int boardCol=9; boardCol > -1; --boardCol) {
          if(boardCol == 0 || boardCol == 9){
            printCol(out, row, blackTop);
          }
          if(boardCol > 0 && boardCol < 9) {
          whitewhite=white;
          if (white) {
            ChessPosition tempPosition = new ChessPosition(row, boardCol);
            if(highlight && validPositions.contains(tempPosition)){
              out.print(SET_BG_COLOR_YELLOW);}
            else{
            out.print(SET_BG_COLOR_WHITE);}
            out.print(SET_TEXT_COLOR_WHITE);
            if (boardCol != 1) {
              white=false;}
          } else {
            ChessPosition tempPosition = new ChessPosition(row, boardCol);
            if(highlight && validPositions.contains(tempPosition)){
              out.print(SET_BG_COLOR_GREEN);}
            else{
            out.print(SET_BG_COLOR_BLACK);}
            out.print(SET_TEXT_COLOR_BLACK);
            if (boardCol != 1) {
              white=true;
            }}
          if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
            int prefixLength=SQUARE_SIZE_IN_CHARS / 2;
            int suffixLength=SQUARE_SIZE_IN_CHARS - prefixLength - 1;
            out.print(EMPTY.repeat(prefixLength));
            printPlayer(out, row, boardCol, whitewhite, highlight);
            out.print(EMPTY.repeat(suffixLength));
          } else {
            out.print(EMPTY.repeat(SQUARE_SIZE_IN_CHARS));}
          out.print(SET_BG_COLOR_DARK_GREY);
          out.print(SET_TEXT_COLOR_DARK_GREY);}}
        out.println();}}}
  }
  private void printPlayer(PrintStream out, int row, int col, boolean white, boolean highlight) {
    if (white) {
      ChessPosition tempPosition = new ChessPosition(row, col);
      if(highlight && validPositions.contains(tempPosition)){
        out.print(SET_BG_COLOR_YELLOW);}
      else{out.print(SET_BG_COLOR_WHITE);}
    } else {
      ChessPosition tempPosition = new ChessPosition(row, col);
      if(highlight && validPositions.contains(tempPosition)){
        out.print(SET_BG_COLOR_GREEN);}
      else{out.print(SET_BG_COLOR_BLACK);}
    }
    if (game.getBoard().getPiece(new ChessPosition(row, col)) != null) {
      ChessPiece piece=game.getBoard().getPiece(new ChessPosition(row, col));
      if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
        out.print(SET_TEXT_COLOR_BLUE);
        switch (piece.getPieceType()) {
          case KING:
            out.print(BLACK_KING);
            break;
          case QUEEN:
            out.print(BLACK_QUEEN);
            break;
          case ROOK:
            out.print(BLACK_ROOK);
            break;
          case BISHOP:
            out.print(BLACK_BISHOP);
            break;
          case KNIGHT:
            out.print(BLACK_KNIGHT);
            break;
          case PAWN:
            out.print(BLACK_PAWN);
            break;
        }
      } else {
        out.print(SET_TEXT_COLOR_RED);
        switch (piece.getPieceType()) {
          case KING:
            out.print(WHITE_KING);
            break;
          case QUEEN:
            out.print(WHITE_QUEEN);
            break;
          case ROOK:
            out.print(WHITE_ROOK);
            break;
          case BISHOP:
            out.print(WHITE_BISHOP);
            break;
          case KNIGHT:
            out.print(WHITE_KNIGHT);
            break;
          case PAWN:
            out.print(WHITE_PAWN);
            break;
        }}
    } else {
      out.print(EMPTY);}
      if(white) {
        ChessPosition tempPosition = new ChessPosition(row, col);
        if(highlight && validPositions.contains(tempPosition)){
          out.print(SET_BG_COLOR_YELLOW);}
        else{out.print(SET_BG_COLOR_WHITE);}
      } else {
        ChessPosition tempPosition = new ChessPosition(row, col);
        if(highlight && validPositions.contains(tempPosition)){
          out.print(SET_BG_COLOR_GREEN);}
        else{out.print(SET_BG_COLOR_BLACK);}
      }}
  public void updateValidPositions(Collection<ChessPosition> validMovess){
    validPositions = validMovess;
  }
}
