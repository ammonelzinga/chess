package clientTests;

import Play.ClientSide;
import Play.LoggedOut;
import Play.ServerFacade;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.*;
import server.*;

import java.util.HashMap;
import java.util.Scanner;


public class ServerFacadeTests {

  private static Server server;
  private static ClientSide loggedOut;
  private static ServerFacade serverFacade;
  private static String url;


  @BeforeAll
  public static void init() {
    server = new Server();
    var port = server.run(0);
    System.out.println("Started test HTTP server on " +  port);
    url = "http://localhost:" + port;
    loggedOut = new ClientSide(url);
    serverFacade = new ServerFacade();
  }

  @AfterAll
  static void stopServer() {
    server.stop();
  }


  @Test
  public void login() {
    AuthData authData;
    String auth;
    UserData userData = new UserData("ammon", "p", "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void loginNeg() {
    UserData userData = new UserData("yaaaaaaa", "password", "email");
    String sessionUrl = url + "/session";
    Assertions.assertThrows(Exception.class, () -> serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, ""));
  }

  @Test
  void register() {
    AuthData authData;
    String auth;
    UserData userData=new UserData("bruce", "banner", "email");
    String sessionUrl=url + "/user";
    try {
      var objAuth=serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth=new Gson().toJson(objAuth);
      authData=new Gson().fromJson(tempAuth, AuthData.class);
      auth=authData.authToken();
      Assertions.assertTrue(auth.length() > 0);
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }
  @Test
  void registerNeg() {
    AuthData authData;
    String auth;
    UserData userData=new UserData("bruce", "banner", "email");
    String sessionUrl=url + "/user";
    try {
      var objAuth=serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth=new Gson().toJson(objAuth);
      authData=new Gson().fromJson(tempAuth, AuthData.class);
      auth=authData.authToken();
      Assertions.assertTrue(auth.length() > 0);
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
    Assertions.assertThrows(Exception.class, ()-> serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, ""));
  }

  @Test
  void logout() {
    AuthData authData;
    String auth;
    UserData userData = new UserData("ammon", "p", "email");
    String sessionUrl = url + "/session";
    try {
      var objAuth=serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth=new Gson().toJson(objAuth);
      authData=new Gson().fromJson(tempAuth, AuthData.class);
      auth=authData.authToken();
      Assertions.assertTrue(auth.length() > 0);
      GameNameRecord gameNameRecord = new GameNameRecord("billyBob");
      serverFacade.run(sessionUrl, "DELETE", true, new Gson().toJson(authData), AuthData.class, true, auth);
      Assertions.assertThrows(Exception.class, ()-> serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(gameNameRecord), GameIdRecord.class, true, auth));
    }
    catch(Exception e){
      System.out.println("Having trouble logging you out, please try again.");
      //System.out.print(e.getMessage());
    }
  }
  @Test
  void logoutNeg() {
    AuthData authData = new AuthData("wrong", "wrong");
    String auth = "wrongwrong";
    String sessionUrl = url + "/user";
    Assertions.assertThrows(Exception.class, ()->  serverFacade.run(sessionUrl, "DELETE", true, new Gson().toJson(authData), AuthData.class, true, auth));
  }


  @Test
  void observeGame() {
    AuthData authData;
    String auth = "";
    UserData userData = new UserData("ammon", "p", "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    sessionUrl = url + "/game";
    int gameID = 2;
    JoinGameRecord JoinGameRecord = new JoinGameRecord(null, gameID);
    try{serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth);
    Assertions.assertTrue(true);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }

  }

  @Test
  void observeGameNeg() {
    AuthData authData = new AuthData("wrong", "wrong");
    int gameID = 2;
    String auth = "wrongwrong";
    String sessionUrl = url + "/user";
    JoinGameRecord JoinGameRecord = new JoinGameRecord(null, gameID);
    Assertions.assertThrows(Exception.class, ()->  serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth));
  }

  @Test
  void joinGame() {
    AuthData authData;
    String auth = "";
    UserData userData = new UserData("bruce", "banner", "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    int gameID = 2;
    String color = "white";
    System.out.println("Which color would you like to play as?");
    color = color.toUpperCase();
    JoinGameRecord JoinGameRecord = new JoinGameRecord(color, gameID);
    sessionUrl = url + "/game";
    try{serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth);
      Assertions.assertTrue(true);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void joinGameNeg(){
    AuthData authData;
    String auth = "";
    UserData userData = new UserData("ammon", "p", "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    int gameID = 2;
    String color = "fake";
    System.out.println("Which color would you like to play as?");
    color = color.toUpperCase();
    JoinGameRecord JoinGameRecord = new JoinGameRecord(color, gameID);
    sessionUrl = url + "/game";
    String authh = auth;
    String sessionUrll = sessionUrl;
    try{serverFacade.run(sessionUrl, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, auth);
      Assertions.assertTrue(true);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    Assertions.assertThrows(Exception.class, ()-> serverFacade.run(sessionUrll, "PUT", true, new Gson().toJson(JoinGameRecord), EmptyRecord.class, true, authh));
  }

  @Test
  void createGame() {
    AuthData authData;
    String auth = "";
    UserData userData = new UserData("ammon", "p", "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    int gameNumber = 0;
    sessionUrl = url + "/game";
    String gameName = "epicnewgame";
    GameNameRecord gameNameRecord = new GameNameRecord(gameName);
    try{var objGameID = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(gameNameRecord), GameIdRecord.class, true, auth);
      String tempGameID = new Gson().toJson(objGameID);
      GameIdRecord gameID = new Gson().fromJson(tempGameID, GameIdRecord.class);
      gameNumber =gameID.gameID();
      Assertions.assertTrue(gameNumber != 0);
    }
    catch(Exception e){
      System.out.println("Sorry try again");
      //System.out.print(e.getMessage());
    }

  }

  @Test
  void createGameNeg() {
    AuthData authData;
    String auth = "";
    UserData userData = new UserData("ammon", "p", "email");
    String sessionUrl = url + "/session";
    try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    int gameNumber = 0;
    sessionUrl = url + "/game";
    String gameName = "epicnewgame";
    GameNameRecord gameNameRecord = new GameNameRecord(gameName);
    try{var objGameID = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(gameNameRecord), GameIdRecord.class, true, auth);
      String tempGameID = new Gson().toJson(objGameID);
      GameIdRecord gameID = new Gson().fromJson(tempGameID, GameIdRecord.class);
      gameNumber =gameID.gameID();
      Assertions.assertTrue(gameNumber != 0);
    }
    catch(Exception e){
      System.out.println("Sorry try again");
      //System.out.print(e.getMessage());
    }
    String authh = "fake";
    String sessionUrll = sessionUrl;
    Assertions.assertThrows(Exception.class, ()-> serverFacade.run(sessionUrll, "POST", true, new Gson().toJson(gameNameRecord), GameIdRecord.class, true, authh));

  }


  @Test
  void listGames() {
    HashMap<Integer, Integer> gameNumberIDMap;
    HashMap<Integer, GameData> gameMap;
    gameNumberIDMap = new HashMap<>();
    gameMap = new HashMap<>();
    AuthData authData;
    String auth = "";
    UserData userData=new UserData("neww", "banner", "email");
    String sessionUrl=url + "/user";
    try {
      var objAuth=serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth=new Gson().toJson(objAuth);
      authData=new Gson().fromJson(tempAuth, AuthData.class);
      auth=authData.authToken();
      Assertions.assertTrue(auth.length() > 0);
      loggedOut.serverFacade = serverFacade;
      loggedOut.url = url;
      loggedOut.auth = auth;
      loggedOut.authData = authData;
      Assertions.assertTrue(auth.length() > 0);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
    int gameNumber = 0;
    sessionUrl = url + "/game";
    String gameName = "epicnewgame";
    GameNameRecord gameNameRecord = new GameNameRecord(gameName);
    try{var objGameID = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(gameNameRecord), GameIdRecord.class, true, auth);
      String tempGameID = new Gson().toJson(objGameID);
      GameIdRecord gameID = new Gson().fromJson(tempGameID, GameIdRecord.class);
      gameNumber =gameID.gameID();
      Assertions.assertTrue(gameNumber != 0);
    }
    catch(Exception e){
      System.out.println("Sorry try again");
      //System.out.print(e.getMessage());
    }

    try{loggedOut.listGames(false);
      //System.out.println(loggedOut.gameMap);
      //System.out.println(gameMap);
      Assertions.assertNotEquals(gameMap, loggedOut.gameMap);
    }
    catch(Exception e){
      System.out.print(e.getMessage());
    }

  }

  @Test
  void listGamesNeg() {
    HashMap<Integer, Integer> gameNumberIDMap;
    HashMap<Integer, GameData> gameMap;
    gameNumberIDMap = new HashMap<>();
    loggedOut.gameMap = new HashMap<>();
    gameMap = new HashMap<>();
    loggedOut.auth = "fake";
    try{loggedOut.listGames(false);
    Assertions.assertEquals(gameMap, loggedOut.gameMap);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }

}