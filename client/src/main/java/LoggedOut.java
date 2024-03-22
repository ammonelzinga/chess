import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import server.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoggedOut {
  ServerFacade serverFacade;
  String url;
  String auth;
  AuthData authData;

  String stage;
  boolean continueChess;
  Scanner scanner;
  public LoggedOut(String URL){
    serverFacade = new ServerFacade();
    url = URL;
    stage = "loggedOut";
    continueChess = true;
    scanner = new Scanner(System.in);
  }

  public void main(String[] args) throws Exception {
    while (continueChess) {
      if(stage == "loggedOut"){
      System.out.println("Welcome to 240 chess. Type Help to get started");
      runLoggedOut();
      System.out.print("finished runloggedout");}
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
          System.out.print("end login");
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
    System.out.println("Welcome back ");
    System.out.print(authData.username());
    switch (line) {
      case "logout" -> Logout();
      case "create" -> createGame();
      case "list"   -> listGames();
      case "join"   -> joinGame();
      //case "observe" -> observeGame();
      default -> helpPost();
    }
  }

  public void helpPost() throws Exception {
    System.out.println("logout -- sign out of your account");
    System.out.println("list -- to view existing chess games");
    System.out.println("create -- a new chess game");
    System.out.println("join -- an existing chess game");
    System.out.println("observe -- watch a chess game");
    System.out.println("help -- to view possible commands");
    //runPostLogin();
  }
  public void Help() throws Exception {
      System.out.println("Register -- to create a new account");
      System.out.println("Login -- to play chess");
      System.out.println("Quit -- to exit chess");
      System.out.println("Help -- to view possible commands");
      //runLoggedOut();
  }

  public void Quit() throws Exception {
      System.out.print("Good bye");
      continueChess = false;
  }

  public void Login() throws Exception {
    String username;
    String password;
    System.out.println("Enter your username: ");
    username = scanner.nextLine();
    System.out.println("Enter your password: ");
    password = scanner.nextLine();
    System.out.print("Username entered: ");
    System.out.println(username);
    System.out.print("Password enetered: ");
    System.out.print(password);
    UserData userData = new UserData(username, password, "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
    String tempAuth = new Gson().toJson(objAuth);
    authData = new Gson().fromJson(tempAuth, AuthData.class);
    auth =authData.authToken();
    stage = "loggedIn";}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    return;
    //scanner.close();
  }
  public void Register() throws Exception {
    String username;
    String password;
    String email;
    System.out.println("Create your username: ");
    Scanner scanner = new Scanner(System.in);
    username = scanner.nextLine();
    System.out.println("Create your password: ");
    password = scanner.nextLine();
    System.out.println("Enter your email: ");
    email = scanner.nextLine();
    UserData userData = new UserData(username, password, email);
    String sessionUrl = url + "/user";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      stage = "loggedIn";}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }

  public void Logout() throws Exception {
    String sessionUrl = url + "/session";
    try{serverFacade.run(sessionUrl, "DELETE", true, new Gson().toJson(authData), AuthData.class, true, auth);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    System.out.println("Good bye ");
    System.out.print(authData.username());
    auth = "";
    authData = null;
    stage = "loggedOut";
  }

  public void joinGame() throws Exception {
    String sessionUrl = url + "/game";
    String stringGameID;
    System.out.println("Enter the GameID for the game you'd like to join: ");
    Scanner scanner = new Scanner(System.in);
    stringGameID = scanner.nextLine();
    int gameID = Integer.parseInt(stringGameID);
    String color;
    System.out.println("Which color would you like to play as?");
    color = scanner.nextLine();
    color = color.toUpperCase();
    JoinGameRecord JoinGameRecord = new JoinGameRecord(color, gameID);
    try{serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth);
      System.out.println("Game join successful");}
    catch(Exception e){
      System.out.println(e.getMessage());
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
      System.out.println("Game Creation Successful. Your Game ID for " + gameName + " is " + gameID);
    }
    catch(Exception e){
      System.out.print(e.getMessage());
    }

  }

  public void listGames() throws Exception {
    String sessionUrl = url + "/game";
    int gameCount = 1;
    try{var objGame = serverFacade.run(sessionUrl, "GET", false, new Gson().toJson(authData), Map.class, true, auth);
      String tempGame = new Gson().toJson(objGame);
      var mapGameData = new Gson().fromJson(tempGame, Map.class);
      //System.out.print(mapGameData);
      System.out.println("");
      System.out.println("Here are the current games: ");
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
            System.out.println(gameCount + ") " + "GameID: " + GameData.gameID() + ", Game Name: " + GameData.gameName()
              + ", WhiteUsername: " + GameData.whiteUsername() + ", BlackUsername: " + GameData.blackUsername());
            gameCount++;
          }
        }
      }
    }
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }

}
