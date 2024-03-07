package service;
import chess.ChessBoard;
import chess.ChessGame;
import dataAccess.*;
import model.GameData;
import dataAccess.AuthDAO;

import javax.xml.crypto.Data;
import java.util.Collection;

public class GameService extends GeneralService {
  UserDAO userDAO;
  AuthDAO authDAO;
  GameDAO gameDAO;
  private int nextGameID=0;

  public GameService(UserDAO userD, AuthDAO authD, GameDAO gameD) {
    userDAO=userD;
    authDAO=authD;
    gameDAO=gameD;
  }

  public Collection<GameData> listGames() throws DataAccessException {
    try{return gameDAO.listGames();}
    catch(DataAccessException e){
      throw e;
    }
  }

  public GameData createGame(String gameName) throws DataAccessException {
    if (gameName == null) {
      DataAccessException exception=new DataAccessException("Error: bad request");
      exception.addStatusCode(400);
      throw exception;
    } else {
      ChessGame newGame=new ChessGame();
      ChessBoard board = new ChessBoard();
      board.resetBoard();
      newGame.setBoard(board);
      nextGameID++;
      GameData newGameData=new GameData(nextGameID, null, null, gameName, newGame);
      int realGameID = gameDAO.createGame(newGameData);
      GameData realGameData=new GameData(realGameID, null, null, gameName, newGame);
      return realGameData;
    }
  }

  public boolean joinGame(String authToken, String playerColor, int gameID) throws DataAccessException {
    try {
      GameData gameData=gameDAO.getGame(gameID);
      if(gameData == null){
        DataAccessException exception=new DataAccessException("Error: bad request");
        exception.addStatusCode(400);
        throw exception;
      }
      else{
      if (playerColor == null || playerColor.equals("")) {
        System.out.print("potential audience");
        return true;
      }
      if (playerColor.equals("BLACK")) {
        if (gameData.blackUsername() != null) {
          DataAccessException exception=new DataAccessException("Error: already taken");
          exception.addStatusCode(403);
          throw exception;
        } else {
          String username=authDAO.getAuth(authToken).username();
          GameData newGameData=new GameData(gameData.gameID(), gameData.whiteUsername(), username, gameData.gameName(), gameData.game());
          gameDAO.updateGame(gameID, newGameData);
          return true;
        }
      }
      else if (playerColor.equals("WHITE")) {
        if (gameData.whiteUsername() != null) {
          DataAccessException exception=new DataAccessException("Error: already taken");
          exception.addStatusCode(403);
          throw exception;
        } else {
          String username=authDAO.getAuth(authToken).username();
          GameData newGameData=new GameData(gameData.gameID(), username, gameData.whiteUsername(), gameData.gameName(), gameData.game());
          gameDAO.updateGame(gameID, newGameData);
          return true;
        }
      } else {
        DataAccessException exception=new DataAccessException("Error: bad request");
        exception.addStatusCode(400);
        throw exception;
      }}
    } catch (DataAccessException exception) {
      System.out.print(exception.getMessage());
      throw exception;}
  }

}
