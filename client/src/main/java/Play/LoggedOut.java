package Play;

import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import server.*;

import java.util.*;

public class LoggedOut {
  public ServerFacade serverFacade;
  public String url;
  public String auth;
  public AuthData authData;
  HashMap<Integer, Integer> gameNumberIDMap;
  public HashMap<Integer, GameData> gameMap;
  DrawChessGame artist;

  String stage;
  boolean continueChess;
  Scanner scanner;
  public LoggedOut(String URL){
    serverFacade = new ServerFacade();
    url = URL;
    stage = "loggedOut";
    continueChess = true;
    scanner = new Scanner(System.in);
    gameNumberIDMap = new HashMap<>();
    gameMap = new HashMap<>();
    artist = new DrawChessGame(null);
  }

  public void main(String[] args) throws Exception {
    System.out.println("Welcome to 240 chess. Type Help to get started");
    while (continueChess) {
      if(stage == "loggedOut") {
        runLoggedOut();
        //System.out.print("finished runloggedout");}
      }
      if(stage == "loggedIn"){
        //System.out.println("starting logged in");
        runPostLogin();
      }
    }
  }
  public void runLoggedOut() throws Exception {
    System.out.println("");
    String line = scanner.nextLine();
    line = line.toLowerCase();
    switch (line) {
      case "login":
          Login();
          //System.out.print("end login");
          break;
      case "register":
        Register();
        break;
      case "quit":
        Quit();
        break;
      default:
        Help();
        break;
    }
    return;
    //scanner.close();
  }

  public void runPostLogin() throws Exception {
    String line = scanner.nextLine();
    line = line.toLowerCase();
    //System.out.print("Welcome back ");
    //System.out.println(authData.username());
    System.out.println("");
    //helpPost();
    switch (line) {
      case "logout":
        Logout();
        break;
      case "create":
        createGame();
        break;
      case "list":
        listGames(true);
        break;
      case "join":
        joinGame();
        break;
      case "observe":
        observeGame();
        break;
      default:
        helpPost();
        break;
    }
  }


  public void helpPost() throws Exception {
    System.out.println("logout -- sign out of your account");
    System.out.println("list -- to view existing chess games");
    System.out.println("create -- a new chess game");
    System.out.println("join -- an existing chess game");
    System.out.println("observe -- watch a chess game");
    System.out.println("help -- to view possible commands");
    System.out.println("");
    //runPostLogin();
  }
  public void Help() throws Exception {
      System.out.println("Register -- to create a new account");
      System.out.println("Login -- to play chess");
      System.out.println("Quit -- to exit chess");
      System.out.println("Help -- to view possible commands");
      System.out.println("");
      //runLoggedOut();
  }

  public void Quit() throws Exception {
      System.out.print("Good bye");
      continueChess = false;
  }

  public void Login() throws Exception {
    String username;
    String password;
    try{System.out.println("Enter your username: ");
    username = scanner.nextLine();
    System.out.println("Enter your password: ");
    password = scanner.nextLine();
    //System.out.print("Username entered: ");
    //System.out.println(username);
    //System.out.print("Password enetered: ");
    //System.out.print(password);
    UserData userData = new UserData(username, password, "email");
    String sessionUrl = url + "/session";
    var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
    String tempAuth = new Gson().toJson(objAuth);
    authData = new Gson().fromJson(tempAuth, AuthData.class);
    auth =authData.authToken();
    stage = "loggedIn";
    System.out.print("Welcome back ");
    System.out.println(authData.username());
    System.out.println("");
    helpPost();
    listGames(false);}
    catch(Exception e){
      System.out.println("Sorry incorrect login information, please try again.");
      //System.out.print(e.getMessage());
    }
    return;
    //scanner.close();
  }
  public void Register() throws Exception {
    String username;
    String password;
    String email;
    System.out.println("Create your username: ");
    try{Scanner scanner = new Scanner(System.in);
    username = scanner.nextLine();
    System.out.println("Create your password: ");
    password = scanner.nextLine();
    System.out.println("Enter your email: ");
    email = scanner.nextLine();
    UserData userData = new UserData(username, password, email);
    String sessionUrl = url + "/user";
    var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      stage = "loggedIn";
      System.out.print("Hello ");
      System.out.println(authData.username());
      System.out.println("");
      helpPost();
      listGames(false);}
    catch(Exception e){
      System.out.println("Sorry, username already taken, pick a new one.");
    }
  }

  public void Logout() throws Exception {
    String sessionUrl = url + "/session";
    try{serverFacade.run(sessionUrl, "DELETE", true, new Gson().toJson(authData), AuthData.class, true, auth);}
    catch(Exception e){
      System.out.println("Having trouble logging you out, please try again.");
      //System.out.print(e.getMessage());
    }
    System.out.print("Good bye ");
    System.out.println(authData.username());
    auth = "";
    authData = null;
    stage = "loggedOut";
  }
  public void observeGame() throws Exception {
    String sessionUrl = url + "/game";
    String stringGameID;
    System.out.println("Enter the game number for the game you'd like to join: ");
    try{Scanner scanner = new Scanner(System.in);
    stringGameID = scanner.nextLine();
    int gameNum = Integer.parseInt(stringGameID);
    int gameID = gameNumberIDMap.get(gameNum);
    JoinGameRecord JoinGameRecord = new JoinGameRecord(null, gameID);
    serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth);
      System.out.println("You are now an observer for gameID: " + gameID);
      listGames(false);
      artist.updateGame(gameMap.get(gameID).game());
      System.out.println("Game " + gameMap.get(gameID).gameName());
      System.out.println("White played as " + gameMap.get(gameID).whiteUsername());
      System.out.println("Black played as " + gameMap.get(gameID).blackUsername());
      artist.main(false);
      System.out.println("");
      System.out.println("");
      artist.main(true);}
    catch(Exception e){
      System.out.println("Sorry, not a game. Choose a game number from the game list");
      //System.out.println(e.getMessage());
    }
  }
  public void joinGame() throws Exception {
    String sessionUrl = url + "/game";
    String stringGameID;
    System.out.println("Enter the game number for the game you'd like to join: ");
    try{Scanner scanner = new Scanner(System.in);
    stringGameID = scanner.nextLine();
    int gameNum = Integer.parseInt(stringGameID);
    int gameID = gameNumberIDMap.get(gameNum);
    String color;
    System.out.println("Which color would you like to play as?");
    color = scanner.nextLine();
    color = color.toUpperCase();
    JoinGameRecord JoinGameRecord = new JoinGameRecord(color, gameID);
    serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth);
      System.out.println("Game join successful");
      listGames(false);
      System.out.println("Game " + gameMap.get(gameID).gameName());
      System.out.println("White played as " + gameMap.get(gameID).whiteUsername());
      System.out.println("Black played as " + gameMap.get(gameID).blackUsername());
      artist.updateGame(gameMap.get(gameID).game());
      artist.main(false);
      System.out.println("");
      System.out.println("");
      artist.main(true);
        }
    catch(Exception e){
      System.out.println("Sorry, color already taken, choose another or become an observer");
      //System.out.println(e.getMessage());
    }
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
      //System.out.print(e.getMessage());
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
      //System.out.print(mapGameData);
      System.out.println("");
      if(print){System.out.println("Here are the current games: ");}
      System.out.println("");
      for(Object game : mapGameData.values()){
        //System.out.println("Object....");
        //System.out.print(game);
        //System.out.print(game.getClass());
        if(game.getClass() == ArrayList.class){
          //System.out.println("True");
          var realGameList = ((ArrayList<?>) game);
          for(Object tempGameData : realGameList){
            String newTempGame = new Gson().toJson(tempGameData);
            GameData GameData = new Gson().fromJson(newTempGame, GameData.class);
            if(print){
            System.out.println(gameCount + ") " + "GameID: " + GameData.gameID() + ", Game Name: " + GameData.gameName()
              + ", WhiteUsername: " + GameData.whiteUsername() + ", BlackUsername: " + GameData.blackUsername());}
            gameNumberIDMap.put(gameCount, GameData.gameID());
            gameMap.put(GameData.gameID(), GameData);
            System.out.print(gameMap);
            gameCount++;
          }
        }
      }
    }
    catch(Exception e){
      System.out.println("Having trouble, try again");
      //System.out.print(e.getMessage());
    }
  }


}
