package Play;
import chess.ChessGame;
import com.google.gson.Gson;
import model.*;
import java.util.*;

public class LoggedIn {
  public ServerFacade serverFacade;
  public String url;
  public String auth;
  public AuthData authData;
  HashMap<Integer, Integer> gameNumberIDMap;
  public HashMap<Integer, GameData> gameMap;
  DrawChessGame artist;
  private WebSocketFacade ws;
  private final ServerMessageHandler notificationHandler = new ServerMessageHandler();
  private ChessGame.TeamColor teamColor;
  private ChessGame game;
  int gameID;

  String stage;
  boolean continueChess;
  Scanner scanner;
  public LoggedIn(String uRL, String stagee, ServerFacade serverFac, boolean continueChesss, Scanner scannerr,
                  HashMap gameNumMap, HashMap gameMapp, DrawChessGame artistt){
    serverFacade = serverFac;
    url = uRL;
    stage = stagee;
    continueChess = continueChesss;
    scanner = scannerr;
    gameNumberIDMap = gameNumMap;
    gameMap = gameMapp;
    artist = artistt;
  }

  public void helpPost() throws Exception {
    System.out.println("logout -- sign out of your account");
    System.out.println("list -- to view existing chess games");
    System.out.println("create -- a new chess game");
    System.out.println("join -- an existing chess game");
    System.out.println("observe -- watch a chess game");
    System.out.println("help -- to view possible commands");
    System.out.println("");
  }

  public String Logout() throws Exception {
    String sessionUrl = url + "/session";
    try{serverFacade.run(sessionUrl, "DELETE", true, new Gson().toJson(authData), AuthData.class, true, auth);}
    catch(Exception e){
      System.out.println("Having trouble logging you out, please try again.");
    }
    System.out.print("Good bye ");
    System.out.println(authData.username());
    auth = "";
    authData = null;
    stage = "loggedOut";
    return stage;
  }
  public String observeGame() throws Exception {
    String sessionUrl = url + "/game";
    String stringGameID;
    System.out.println("Enter the game number for the game you'd like to join: ");
    try{Scanner scanner = new Scanner(System.in);
      stringGameID = scanner.nextLine();
      int gameNum = Integer.parseInt(stringGameID);
      gameID = gameNumberIDMap.get(gameNum);
      JoinGameRecord joinGameRecord = new JoinGameRecord(null, gameID);
      serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(joinGameRecord), EmptyRecord.class, true, auth);
      System.out.println("You are now an observer for gameID: " + gameID);
      listGames(false);
      artist.updateGame(gameMap.get(gameID).game());
      System.out.println("Game " + gameMap.get(gameID).gameName());
      System.out.println("White played as " + gameMap.get(gameID).whiteUsername());
      System.out.println("Black played as " + gameMap.get(gameID).blackUsername());
      System.out.println("");
      System.out.println("");
      //artist.main(true);
      stage = "gameIn";}
    catch(Exception e){
      System.out.println("Sorry, not a game. Choose a game number from the game list");
      System.out.println(e.getMessage());
    }
    return stage;
  }
  public String joinGame() throws Exception {
    String sessionUrl = url + "/game";
    String stringGameID;
    System.out.println("Enter the game number for the game you'd like to join: ");
    try{Scanner scanner = new Scanner(System.in);
      stringGameID = scanner.nextLine();
      int gameNum = Integer.parseInt(stringGameID);
      gameID = gameNumberIDMap.get(gameNum);
      String color;
      System.out.println("Which color would you like to play as?");
      color = scanner.nextLine();
      color = color.toUpperCase();
      if(color.equals("WHITE") == false && color.equals("BLACK") == false){
        System.out.println("Sorryyyyyy, color already taken, choose another or become an observer");
        return stage;
      }
        JoinGameRecord joinGameRecord = new JoinGameRecord(color, gameID);
        serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(joinGameRecord), EmptyRecord.class, true, auth);
      System.out.println("Game join successful");
      listGames(false);
      game = gameMap.get(gameID).game();
      if(color == "WHITE" || color.equals("WHITE")){
        teamColor = ChessGame.TeamColor.WHITE;
      }
      if(color == "BLACK" || color.equals("BLACK")){
        teamColor = ChessGame.TeamColor.BLACK;
      }
      System.out.println("Game " + gameMap.get(gameID).gameName());
      System.out.println("White played as " + gameMap.get(gameID).whiteUsername());
      System.out.println("Black played as " + gameMap.get(gameID).blackUsername());
      artist.updateGame(gameMap.get(gameID).game());
      //artist.main(false);
      System.out.println("");
      System.out.println("");
      //artist.main(true);
      stage = "gameIn";
    }
    catch(Exception e){
      stage = "loggedIn";
      System.out.println("Sorry, color already taken, choose another or become an observer");
    }
    return stage;
  }
  public void createGame() throws Exception{
    String sessionUrl = url + "/game";
    String gameName;
    System.out.println("Enter your new Game Name: ");
    Scanner scanner = new Scanner(System.in);
    gameName = scanner.nextLine();
    GameNameRecord gameNameRecord = new GameNameRecord(gameName);
    try{var objGameID = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(gameNameRecord), GameIdRecord.class, true, auth);
      String tempGameID = new Gson().toJson(objGameID);
      GameIdRecord gameID = new Gson().fromJson(tempGameID, GameIdRecord.class);
      System.out.println("");
      System.out.println("Game Creation Successful. Your Game ID for " + gameName + " is " + gameID.gameID());
    }
    catch(Exception e){
      System.out.println("Sorry try again");
    }

  }

  public void listGames(boolean print) throws Exception {
    String sessionUrl = url + "/game";
    int gameCount = 1;
    try{var objGame = serverFacade.run(sessionUrl, "GET", false, new Gson().toJson(authData), Map.class, true, auth);
      String tempGame = new Gson().toJson(objGame);
      var mapGameData = new Gson().fromJson(tempGame, Map.class);
      if(gameNumberIDMap!= null){
        gameNumberIDMap.clear();}
      if(gameMap!=null){
        gameMap.clear();
      }
      System.out.println("");
      if(print){System.out.println("Here are the current games: ");}
      System.out.println("");
      for(Object game : mapGameData.values()){
        if(game.getClass() == ArrayList.class){
          var realGameList = ((ArrayList<?>) game);
          for(Object tempGameData : realGameList){
            String newTempGame = new Gson().toJson(tempGameData);
            GameData gameData = new Gson().fromJson(newTempGame, GameData.class);
            if(print){
              System.out.println(gameCount + ") " + "GameID: " + gameData.gameID() + ", Game Name: " + gameData.gameName()
                      + ", WhiteUsername: " + gameData.whiteUsername() + ", BlackUsername: " + gameData.blackUsername());}
            gameNumberIDMap.put(gameCount, gameData.gameID());
            gameMap.put(gameData.gameID(), gameData);
            gameCount++;
          }
        }
      }
    }
    catch(Exception e){
      System.out.println("Having trouble, try again");
    }
  }
  public ChessGame.TeamColor getColor(){
    return teamColor;
  }
  public ChessGame getGame(){
    return game;
  }
  public WebSocketFacade getWs(){
    return ws;
  }


}
