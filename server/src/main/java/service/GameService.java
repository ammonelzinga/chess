package service;
import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import dataAccess.AuthDAO;
import java.util.ArrayList;
import java.util.Random;

public class GameService extends GeneralService{
  UserDAO userDAO;
  AuthDAO authDAO;
  GameDAO gameDAO;
  Random randInt = new Random();

  public GameService(UserDAO userD, AuthDAO authD, GameDAO gameD){
    userDAO = userD;
    authDAO = authD;
    gameDAO = gameD;
  }

  public ArrayList<GameData> listGames(){
    return gameDAO.listGames();
  }

  public boolean createGame(String gameName){
    int gameID=0;
    boolean uniqueID = false;
    while(uniqueID == false){
      gameID = randInt.nextInt(1000000);
      if(gameDAO.uniqueGameID(gameID)==true){
        uniqueID = true;
      }
    }
    ChessGame newGame = new ChessGame();
    GameData newGameData = new GameData(gameID, null, null, gameName, newGame);
    try{
      gameDAO.createGame(newGameData);
      return true;
    }
    catch(Exception e){
      return false;
    }
  }

  public boolean joinGame(String authToken, String playerColor, int gameID){
    try{
      GameData gameData = gameDAO.getGame(gameID);
      //player wants to be black side
      if(playerColor == "black"){
        if(gameData.blackUsername() != null){
          return false;
        }
        else{
          //let them join game as black
          String username = authDAO.getAuth(authToken).username();
          GameData newGameData = new GameData(gameData.gameID(), gameData.whiteUsername(), username, gameData.gameName(), gameData.game());
          gameDAO.updateGame(gameID, newGameData);
        }}
        //player wants to be white side
        else{
          if(gameData.whiteUsername() != null){
            return false;
        }
          else{
              //let them join game as white
              String username = authDAO.getAuth(authToken).username();
              GameData newGameData = new GameData(gameData.gameID(), username, gameData.whiteUsername(), gameData.gameName(), gameData.game());
              gameDAO.updateGame(gameID, newGameData);
            }}
        return true;
    }
    catch(Exception e){return false;}

  }


}
