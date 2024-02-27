package server;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import model.AuthData;
import service.GameService;
import service.GeneralService;
import service.UserService;
import spark.*;
import com.google.gson.Gson;
import java.util.*;
import model.UserData;
import dataAccess.*;

public class UserHandler extends GeneralHandler{
  UserDAO userDAO;
  AuthDAO authDAO;
  GameDAO gameDAO;
  UserService userService;
  GeneralService generalService;
  GameService gameService;

  public UserHandler(UserDAO userD, AuthDAO authD, GameDAO gameD, UserService userS,
                     GeneralService generalS, GameService gameS){
    userDAO = userD;
    authDAO = authD;
    gameDAO = gameD;
    userService = userS;
    generalService = generalS;
    gameService = gameS;
  }

  public Object registerUser(Request req, Response res) throws DataAccessException{
    var newUser = new Gson().fromJson(req.body(), UserData.class);
    //System.out.print("newUser: ");
    //System.out.print(newUser.toString());
    try{
      var newAuthData = userService.register(newUser);
      //System.out.println("Registered new user: ");
      //System.out.print(userDAO.toString());
      //System.out.println("End to String");
      res.status(200);
      return new Gson().toJson(newAuthData);
    }
    catch(DataAccessException exception){
      System.out.print("data access exception");
      return handleError(exception, req, res);
      //throw exception;
    }
    catch(Exception exception){
      System.out.println("RandomExc");
      return handleRandomError(exception, req, res);
    }
  }

  public Object login(Request req, Response res){
    var user = new Gson().fromJson(req.body(), UserData.class);
    try{
      var authData = userService.login(user);
      res.status(200);
      return new Gson().toJson(authData);
    }
    catch(DataAccessException exception){
      return handleError(exception, req, res);
    }
    catch(Exception exception){
      return handleRandomError(exception, req, res);
    }
  }

  public Object logout(Request req, Response res){
    String authToken = req.headers("authorization");
    try{
      userService.logout(authToken);
      res.status(200);
      return new Gson().toJson(new EmptyRecord());
    }
    catch(DataAccessException exception){
      return handleError(exception, req, res);
    }
    catch(Exception exception){
      return handleRandomError(exception, req, res);
    }
  }


  public Object clearAll(Request req, Response res) {
    try {
      generalService.deleteAllData();
      res.status(200);
      return new Gson().toJson(new EmptyRecord());
    } catch (Exception exception) {
      System.out.println("RandomExc");
      return handleRandomError(exception, req, res);
    }
  }

}
