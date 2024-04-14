package Play;


import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import server.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static ui.EscapeSequences.ERASE_SCREEN;
import static ui.EscapeSequences.SET_BG_COLOR_DARK_GREY;

public class GameIn {
  public ServerFacade serverFacade;
  public String url;
  public String auth;
  public AuthData authData;
  HashMap<Integer, Integer> gameNumberIDMap;
  public HashMap<Integer, GameData> gameMap;
  DrawChessGame artist;
  private WebSocketFacade ws;
  private final ServerMessageHandler notificationHandler = new ServerMessageHandler();
  int gameID;
  private ParseUserMove parseUserMove = new ParseUserMove();

  String stage;
  boolean continueChess;
  Scanner scanner;
  ChessGame game;
  ChessGame.TeamColor teamColor;
  public GameIn(String URL, String stagee, ServerFacade serverFac, boolean continueChesss, Scanner scannerr,
                HashMap gameNumMap, HashMap gameMapp, DrawChessGame artistt){
    serverFacade = serverFac;
    url = URL;
    stage = stagee;
    continueChess = continueChesss;
    scanner = scannerr;
    gameNumberIDMap = gameNumMap;
    gameMap = gameMapp;
    artist = artistt;
  }

  public void updateGame(ChessGame gamee, int gaameID){
    game = gamee;
    gameID = gaameID;
  }

  public void helpGame() {
    System.out.println("redraw -- to redraw the chess board");
    System.out.println("leave -- to leave the game");
    System.out.println("move -- to make a chess move");
    System.out.println("resign -- to resign the game");
    System.out.println("highlight-- to see all legal moves for a chess piece");
    System.out.println("help -- to view possible commands");

  }

  public String leave() throws Exception {
    var out=new PrintStream(System.out, true, StandardCharsets.UTF_8);
    out.print(SET_BG_COLOR_DARK_GREY);
    System.out.println("Leaving game");
    ws.leaveGame(authData.authToken(), authData.username(), gameID);
    out.print(ERASE_SCREEN);
    stage = "loggedIn";
    return stage;
  }
  public void joinGamePlayer(){
    if(stage == "gameIn"){
    try{
    ws = new WebSocketFacade(url);
    ws.joinGamePlayer(authData.authToken(), authData.username(),gameID, teamColor);}
    catch(Exception e){}
  }}

  public void joinGameObserver(){
    if(stage == "gameIn"){
      try{
    ws = new WebSocketFacade(url);
    ws.joinGameObserver(authData.authToken(), authData.username(), gameID);
  }
      catch(Exception e){}
    }}

  public void makeMove(){
    try{
      //String sessionUrl = url + "/game";
      ChessMove chessMove = parseUserMove.run(game.getBoard(), teamColor);
      if(chessMove == null){
        System.out.println("Sorry invalid move");
        return;}
      ws.makeMove(auth, authData.username(), gameID, chessMove, teamColor);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }}


}
