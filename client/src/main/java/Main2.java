import Play.ClientSide;
import Play.LoggedOut;
import chess.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static ui.EscapeSequences.SET_BG_COLOR_DARK_GREY;

public class Main2 {
  public static void main(String[] args) {
    var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
    System.out.println("♕ 240 Chess Client: " + piece);
    System.out.println("Welcome to 240 chess. Type Help to get started");
    var out=new PrintStream(System.out, true, StandardCharsets.UTF_8);
    out.print(SET_BG_COLOR_DARK_GREY);
    ClientSide client = new ClientSide("http://localhost:8080");
    try{client.main(args);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }
}