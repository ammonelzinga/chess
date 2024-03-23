import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static ui.EscapeSequences.*;

public class DrawChessGame {
  ChessGame game;
  private static final int BOARD_SIZE_IN_SQUARES=8;
  private static final int SQUARE_SIZE_IN_CHARS=1;
  private static final int LINE_WIDTH_IN_CHARS=1;

  public DrawChessGame(ChessGame Game) {
    game=Game;

  }

  public void updateGame(ChessGame Game) {
    game=Game;
  }

  public void main() {
    var out=new PrintStream(System.out, true, StandardCharsets.UTF_8);
    drawBoard(out);
  }

  private void drawBoard(PrintStream out) {
    boolean white = true;
    for (int boardRow=8; boardRow > 0; boardRow--) {
      drawRowOfSquares(out, boardRow, white);
      white = !white;
      out.print(SET_BG_COLOR_LIGHT_GREY);
    }

  }

  private void drawRowOfSquares(PrintStream out, int row, boolean White) {
    boolean white=White;
    boolean whitewhite=true;

    for (int squareRow=0; squareRow < 1; squareRow++) {
      for (int boardCol=1; boardCol < 9; ++boardCol) {
        whitewhite=white;
        if (white) {
          out.print(SET_BG_COLOR_WHITE);
          out.print(SET_TEXT_COLOR_WHITE);
          if (boardCol != 8) {
            white=false;
          }
        } else {
          out.print(SET_BG_COLOR_BLACK);
          out.print(SET_TEXT_COLOR_BLACK);
          if (boardCol != 1) {
            white=true;
          }
        }

        if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
          int prefixLength=SQUARE_SIZE_IN_CHARS / 2;
          int suffixLength=SQUARE_SIZE_IN_CHARS - prefixLength - 1;

          out.print(EMPTY.repeat(prefixLength));
          printPlayer(out, row, boardCol, whitewhite);
          out.print(EMPTY.repeat(suffixLength));
        } else {
          out.print(EMPTY.repeat(SQUARE_SIZE_IN_CHARS));
        }
        //out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_DARK_GREY);
      }

      out.println();
    }
  }

  private void printPlayer(PrintStream out, int row, int col, boolean white) {
    if (white) {
      out.print(SET_BG_COLOR_WHITE);
      //out.print(SET_TEXT_COLOR_BLACK);

    } else {
      out.print(SET_BG_COLOR_BLACK);
      //out.print(SET_TEXT_COLOR_WHITE);
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
        out.print(SET_TEXT_COLOR_GREEN);
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
        }
      }
    } else {
      out.print(EMPTY);}
      if (white) {
        out.print(SET_BG_COLOR_WHITE);
        //out.print(SET_TEXT_COLOR_BLACK);
      } else {
        out.print(SET_BG_COLOR_BLACK);
        //out.print(SET_TEXT_COLOR_WHITE);
      }
    }

}
