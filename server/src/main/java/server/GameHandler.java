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

public class GameHandler extends GeneralHandler {
  UserDAO userDAO;
  AuthDAO authDAO;
  GameDAO gameDAO;
  UserService userService;
  GeneralService generalService;
  GameService gameService;

  public GameHandler(UserDAO userD, AuthDAO authD, GameDAO gameD, UserService userS,
                     GeneralService generalS, GameService gameS) {
    userDAO=userD;
    authDAO=authD;
    gameDAO=gameD;
    userService=userS;
    generalService=generalS;
    gameService=gameS;
  }

  public Object listGames(Request req, Response res){
    String authToken = req.headers("authorization");
    try{
      generalService.checkAuth(authToken);
      var gameList = gameService.listGames();
      res.type("application/json");
      res.status(200);
      return new Gson().toJson(Map.of("game", gameList));
    }
    catch(DataAccessException exception){
      return handleError(exception, req, res);
    }
    catch(Exception exception){
      return handleRandomError(exception, req, res);
    }
  }


  public Object createGame(Request req, Response res){
    String authToken = req.headers("authorization");
    var gameName = new Gson().fromJson(req.body(), GameNameRecord.class);
    try{
      generalService.checkAuth(authToken);
      var newGameData = gameService.createGame(gameName.gameName());
      GameIdRecord gameID = new GameIdRecord(newGameData.gameID());
      res.status(200);
      return new Gson().toJson(gameID);
    }
    catch(DataAccessException exception){
      return handleError(exception, req, res);
    }
    catch(Exception exception){
      return handleRandomError(exception, req, res);
    }
  }


}

