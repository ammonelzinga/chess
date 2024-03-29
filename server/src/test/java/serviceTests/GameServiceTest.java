package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.GameService;
import service.GeneralService;

class GameServiceTest {

  MemoryUserDAO userDAO = new MemoryUserDAO();
  MemoryAuthDAO authDAO = new MemoryAuthDAO();
  MemoryGameDAO gameDAO = new MemoryGameDAO();
  GameService gameService = new GameService(userDAO, authDAO, gameDAO);
  GeneralService generalService = new GeneralService(userDAO, authDAO, gameDAO);
  AuthData authDataTest = new AuthData("123", "first");

  @Test
  void listGames() {
    try{gameDAO.mapGames.put(123, new GameData(123, "first", null, "GameNameAwesome", new ChessGame()));
    Assertions.assertEquals(gameDAO.mapGames.values(), gameService.listGames());}
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }

  }

  @Test
  void listGamesNeg() {
    try{gameDAO.mapGames.put(123, new GameData(123, "first", null, "GameNameAwesome", new ChessGame()));
    try{generalService.deleteAllData();}
    catch(Exception e){System.out.print(e.getMessage());}
    Assertions.assertEquals(0, gameService.listGames().size());}
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }

  }

  @Test
  void createGame() throws DataAccessException {
    GameData gameDataTest = new GameData(1, null, null, "Awesome", new ChessGame());
    GameData gameData = gameService.createGame("Awesome");
    Assertions.assertEquals(gameDataTest.gameID(), gameData.gameID());
    Assertions.assertEquals(gameDataTest.whiteUsername(), gameData.whiteUsername());
    Assertions.assertEquals(gameDataTest.gameName(), gameData.gameName());
  }

  @Test
  void createGameNeg() throws DataAccessException{
    Assertions.assertThrows(DataAccessException.class, () -> gameService.createGame(null));
  }

  @Test
  void joinGame() throws DataAccessException {
    gameService.createGame("Awesome");
    authDAO.authMap.put("123", authDataTest);
    authDAO.authMap.put("456", authDataTest);
    boolean tru =gameService.joinGame("123", "BLACK", 1);
    Assertions.assertTrue(tru);
    Assertions.assertNull(gameDAO.mapGames.get(1).whiteUsername());
    Assertions.assertEquals("first", gameDAO.mapGames.get(1).blackUsername());

  }

  @Test
  void joinGameNeg() throws DataAccessException {
    gameService.createGame("Awesome");
    authDAO.authMap.put("123", authDataTest);
    authDAO.authMap.put("456", authDataTest);
    boolean tru =gameService.joinGame("123", "BLACK", 1);
    Assertions.assertThrows(DataAccessException.class, () -> gameService.joinGame("456", "BLACK", 1));
  }
}