package Play;

import chess.ChessGame;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;

import java.util.*;

import static chess.ChessGame.TeamColor.BLACK;

public class ClientSide {
  public ServerFacade serverFacade;
  public String url;
  public String auth;
  public AuthData authData;
  HashMap<Integer, Integer> gameNumberIDMap;
  public HashMap<Integer, GameData> gameMap;
  public HighlightMoves highlightMoves;
  DrawChessGame artist;
  private WebSocketFacade ws;
  private final ServerMessageHandler notificationHandler = new ServerMessageHandler();
  LoggedOut stageLoggedOut;
  LoggedIn stageLoggedIn;
  GameIn stageGame;

  public String stage;
  public boolean continueChess;
  Scanner scanner;
  public ClientSide(String uRL){
    serverFacade = new ServerFacade();
    url = uRL;
    stage = "loggedOut";
    continueChess = true;
    scanner = new Scanner(System.in);
    gameNumberIDMap = new HashMap<>();
    gameMap = new HashMap<>();
    artist = new DrawChessGame(null);
   stageLoggedOut = new LoggedOut(url, this.stage, serverFacade, this.continueChess, scanner,
           gameNumberIDMap, gameMap, artist);
   stageLoggedIn = new LoggedIn(url, this.stage, serverFacade, this.continueChess, scanner,
           gameNumberIDMap, gameMap, artist);
   stageGame = new GameIn(url, this.stage, serverFacade, this.continueChess, scanner,
           gameNumberIDMap, gameMap, artist, stageLoggedIn);
  }

  public void main(String[] args) throws Exception {
    System.out.println("Welcome to 240 chess. Type Help to get started");
    while (continueChess) {
      if(stage == "loggedOut") {
        runLoggedOut();
      }
      if(stage == "loggedIn"){
        runPostLogin();
      }
      if(stage == "gameIn"){
        runGameIn();
      }}}

  public void runGameIn() throws Exception {
    String line = scanner.nextLine();
    line = line.toLowerCase();
    switch (line) {
      case "highlight":
          try{
            updateGame();
            if(stageGame.teamColor == BLACK){
            highlightMoves = new HighlightMoves(false, artist, stageGame.game);}
            else{
              highlightMoves = new HighlightMoves(true, artist, stageGame.game);}
            highlightMoves.run();
            }
          catch(Exception e){
            //System.out.println(e.getMessage());
            System.out.println("Sorry try again.");
          }
          break;
      case "resign":
          stageGame.resign();
          break;
      case "move":
        stageGame.makeMove();
        break;
      case "leave":
        stage = stageGame.leave();
        stageLoggedIn.helpPost();
        stageGame.teamColor = null;
        break;
      case "redraw":
        try{updateGame();
          if(stageGame.teamColor == BLACK){
            artist.main(false, false);
          }
          else{
        artist.main(true, false);}}
        catch(Exception e){
          System.out.println("Unable to redraw game, please try again.");
        }
        break;
      default:
        stageGame.helpGame();
    }
  }

  public void runLoggedOut() throws Exception {
    System.out.println("");
    String line = scanner.nextLine();
    line = line.toLowerCase();
    switch (line) {
      case "login":
        stage = stageLoggedOut.login();
        updateStageLoggedIn();
        //System.out.print("end login");
        break;
      case "register":
        stage = stageLoggedOut.register();
        updateStageLoggedIn();
        break;
      case "quit":
        continueChess = stageLoggedOut.quit();
        break;
      default:
        stageLoggedOut.help();
        break;
    }
    return;
    //scanner.close();
  }

  public void runPostLogin() throws Exception {
    String line=scanner.nextLine();
    line=line.toLowerCase();
    System.out.println("");
    //helpPost();
    switch (line) {
      case "logout":
        stage = stageLoggedIn.Logout();
        stageLoggedOut.authData = stageLoggedIn.authData;
        stageLoggedOut.auth = stageLoggedIn.auth;
        break;
      case "create":
        stageLoggedIn.createGame();
        stageLoggedIn.listGames(false);
        updateStageGame();
        break;
      case "list":
        stageLoggedIn.listGames(true);
        updateStageGame();
        break;
      case "join":
        stage = stageLoggedIn.joinGame();
        updateStageGame();
        stageGame.joinGamePlayer();
        if(stage == "gameIn"){
          stageGame.helpGame();
        }
        break;
      case "observe":
        stage = stageLoggedIn.observeGame();
        updateStageGame();
        stageGame.joinGameObserver();
        if(stage == "gameIn"){
          stageGame.helpGame();
        }
        break;
      default:
        stageLoggedIn.helpPost();
        break;
    }
  }

  void updateGame() throws Exception {
    try {
      stageLoggedIn.listGames(false);
      stageGame.game = stageLoggedIn.gameMap.get(stageGame.gameID).game();
      artist.updateGame(stageGame.game);
    }
    catch(Exception e){
      throw e;
    }
  }

  void updateStageGame() {
    stageGame.authData = stageLoggedIn.authData;
    stageGame.auth = stageLoggedIn.auth;
    stageGame.gameNumberIDMap = stageLoggedIn.gameNumberIDMap;
    stageGame.gameMap= stageLoggedIn.gameMap;
    stageGame.stage = stage;
    stageGame.url = stageLoggedIn.url;
    stageGame.gameID =stageLoggedIn.gameID;
    stageGame.game = stageLoggedIn.getGame();
    stageGame.teamColor = stageLoggedIn.getColor();
  }

  void updateStageLoggedIn() {
    stageLoggedIn.authData = stageLoggedOut.authData;
    stageLoggedIn.auth = stageLoggedOut.auth;
    stageLoggedIn.stage = "loggedIn";
  }
}
