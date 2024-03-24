import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.*;
import server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class temp {
  //ServerFacade serverFacade;
  //LoggedOut client;
  //String url;


  private static Server server;

  @BeforeAll
  public void init() {
    server = new Server();
    var port = server.run(0);
    System.out.println("Started test HTTP server on " + port);
    //url = "http://localhost:" + port;
    //client = new LoggedOut(url);
    //serverFacade = new ServerFacade();
  }

  @AfterAll
  static void stopServer() {
    server.stop();
  }


  @Test
  public void Login() {
      /*AuthData authData;
      String auth;
      UserData userData = new UserData("username", "password", "email");
      String sessionUrl = url + "/session";
      try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
      catch(Exception e){
        System.out.print(e.getMessage());
      }*/
  }
  @Test
  public void LoginNeg(){
      /*AuthData authData;
      String auth;
      UserData userData = new UserData("yaaaaaaa", "password", "email");
      String sessionUrl = url + "/session";
      Assertions.assertThrows(Exception.class, () -> serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, ""));
       */
  }
  @Test
  public void Register(){
      /*
      AuthData authData;
      String auth;
      UserData userData = new UserData("bruce", "banner", "email");
      String sessionUrl = url + "/user";
      try{var objAuth = serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, "");
      String tempAuth = new Gson().toJson(objAuth);
      authData = new Gson().fromJson(tempAuth, AuthData.class);
      auth =authData.authToken();
      Assertions.assertTrue(auth.length() > 0);}
      catch(Exception e){
        System.out.print(e.getMessage());
      }*/
  }
  @Test
  public void RegisterNeg(){
      /*
      AuthData authData;
      String auth;
      UserData userData = new UserData("username", "password", "email");
      String sessionUrl = url + "/user";
      Assertions.assertThrows(Exception.class, ()-> serverFacade.run(sessionUrl, "POST", true, new Gson().toJson(userData), AuthData.class, false, ""));

       */
  }

  @Test
  public void Logout(){

  }

  @Test
  public void LogoutNeg(){

  }


  @Test
  public void Create(){}
  @Test
  public void CreateNeg(){}

  @Test
  public void Join(){}
  @Test
  public void JoinNeg(){}

  @Test
  public void Observe(){}
  @Test
  public void ObserveNeg(){}

  static class LoggedOutTest {
    ServerFacade serverFacade;
    LoggedOut clientt;
    @Test
    void login() {
    }

    @Test
    void register() {
    }

    @Test
    void logout() {
    }

    @Test
    void observeGame() {
    }

    @Test
    void joinGame() {
    }

    @Test
    void createGame() {
    }

    @Test
    void listGames() {
    }
  }
}

