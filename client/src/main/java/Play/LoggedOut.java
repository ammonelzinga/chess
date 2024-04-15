package Play;

import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;

import java.util.*;

public class LoggedOut {
  public ServerFacade serverFacade;
  public String url;
  public String auth;
  public AuthData authData;
  HashMap<Integer, Integer> gameNumberIDMap;
  public HashMap<Integer, GameData> gameMap;
  DrawChessGame artist;
  private WebSocketFacade ws;
  private final ServerMessageHandler notificationHandler = new ServerMessageHandler();

  String stage;
  boolean continueChess;
  Scanner scanner;
  public LoggedOut(String uRL, String stagee, ServerFacade serverFac, boolean continueChesss, Scanner scannerr,
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

  public void help() throws Exception {
      System.out.println("Register -- to create a new account");
      System.out.println("Login -- to play chess");
      System.out.println("Quit -- to exit chess");
      System.out.println("Help -- to view possible commands");
      System.out.println("");
      //runLoggedOut();
  }

  public boolean quit() throws Exception {
      System.out.print("Good bye");
      continueChess = false;
      return continueChess;
  }

  public String login() throws Exception {
    String username;
    String password;
    try{System.out.println("Enter your username: ");
    username = scanner.nextLine();
    System.out.println("Enter your password: ");
    password = scanner.nextLine();
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
    }
    catch(Exception e){
      System.out.println("Sorry incorrect login information, please try again.");
    }
    return stage;
  }
  public String register() throws Exception {
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
    }
    catch(Exception e){
      System.out.println("Sorry, username already taken, pick a new one.");
    }
    return stage;
  }

}
