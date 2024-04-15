package dataAccessTests;
import chess.ChessGame;
import dataAccess.GameDAO;
import dataAccess.SqlGameDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.GameData;
import dataAccess.DataAccessException;

import java.util.HashMap;

class sqlGameDAOTest {
  GameDAO dao = new SqlGameDAO();
  @Test
  void createGame() {
    GameData game = new GameData(100, null, null, "blueberry", new ChessGame());
    try{
      var id = dao.createGame(game);
      GameData sGame = dao.getGame(id);
      Assertions.assertEquals(game.gameID(), sGame.gameID());
      Assertions.assertEquals(game.whiteUsername(), sGame.whiteUsername());
      Assertions.assertEquals(game.blackUsername(), sGame.blackUsername());
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void createGameNeg() {
    GameData game = new GameData(100, null, null, null, new ChessGame());
      Assertions.assertThrows(DataAccessException.class, () -> dao.createGame(game));
  }

  @Test
  void getGame() {
    GameData game = new GameData(101, null, null, "strawberry", new ChessGame());
    try{
      var id = dao.createGame(game);
      GameData sGame = dao.getGame(id);
      Assertions.assertEquals(game.gameID(), sGame.gameID());
      Assertions.assertEquals(game.whiteUsername(), sGame.whiteUsername());
      Assertions.assertEquals(game.blackUsername(), sGame.blackUsername());
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void getGameNeg() {
    try{Assertions.assertNull(dao.getGame(987));}
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void listGames() {
    HashMap<Integer, GameData> mapGames = new HashMap<>();
    try{
      dao.deleteAll();
      GameData game = new GameData(101, null, null, "strawberry", new ChessGame());
      var id = dao.createGame(game);
      GameData game1 = new GameData(id, null, null, "strawberry", new ChessGame());
      mapGames.put(id, game1);
      Assertions.assertTrue(dao.listGames().size() > 0);
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void listGamesNeg() {
    try {
      dao.deleteAll();
      Assertions.assertEquals(dao.listGames().size(), 0);
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void updateGame() {
    GameData game = new GameData(101, null, null, "strawberry", new ChessGame());
    try{
      var id = dao.createGame(game);
      GameData newGame = new GameData(101, "joy", "happy", "strawberry", new ChessGame());
      dao.updateGame(id, newGame);
      GameData sGame = dao.getGame(id);
      Assertions.assertEquals(game.gameID(), sGame.gameID());
      Assertions.assertEquals("joy", sGame.whiteUsername());
      Assertions.assertEquals("happy", sGame.blackUsername());
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void updateGameNeg() {
    GameData game = new GameData(101, null, null, "strawberry", new ChessGame());
    try{
      var id = dao.createGame(game);
      GameData newGame = new GameData(101, "joy", "happy", "strawberry", new ChessGame());
      dao.updateGame(123123, newGame);
      GameData sGame = dao.getGame(id);
      Assertions.assertNull(sGame);
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }
  @Test
  void deleteAll() {
    GameData game = new GameData(100, null, null, "blueberry", new ChessGame());
    try{
      var id = dao.createGame(game);
      dao.deleteAll();
      Assertions.assertEquals(dao.listGames().size(), 0);}
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
}}