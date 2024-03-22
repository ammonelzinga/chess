import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import server.Server;

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
        System.out.println("starting logged in");
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
      //case "create" -> createGame();
      case "list"   -> listGames();
      //case "join"   -> joinGame();
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
    serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
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

  public void listGames() throws Exception {
    String sessionUrl = url + "/game";
    try{serverFacade.run(sessionUrl, "GET", false, new Gson().toJson(authData), GameData.class, true, auth);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }
}
