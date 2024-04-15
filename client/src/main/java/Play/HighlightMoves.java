package Play;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class HighlightMoves {
  public DrawChessGame artist;
  public ChessPosition chessPosition;
  public ParseUserMove parserUserMove = new ParseUserMove();
  private ChessGame game;
  public Collection<ChessMove> validMoves;
  public Collection<ChessPosition> validPositions = new HashSet<>();
  boolean blackTop;

  public HighlightMoves(boolean blackTopp, DrawChessGame artistt, ChessGame gamee){
    artist = artistt;
    game = gamee;
    blackTop = blackTopp;
  }

  public void run(){
    System.out.println("Please enter the square for the piece you'd like to see valid moves for");
    chessPosition = parserUserMove.getStartPos();
    validMoves = game.validMoves(chessPosition);
    int i = 0;
    for (ChessMove move: validMoves){
      if(i ==0 ) {
        validPositions.add(move.getStartPosition());
        i = 1;
      }
      validPositions.add(move.getEndPosition());
      }

    artist.updateValidPositions(validPositions);
    artist.main(blackTop, true);
  }
}
