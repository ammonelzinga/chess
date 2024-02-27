package serviceTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.GeneralService;

class GeneralServiceTest {

  MemoryUserDAO userDAO = new MemoryUserDAO();
  MemoryAuthDAO authDAO = new MemoryAuthDAO();
  MemoryGameDAO gameDAO = new MemoryGameDAO();
  GeneralService generalService = new GeneralService(userDAO, authDAO, gameDAO);
  UserData userData = new UserData("first", "firstP", "firstE");
  AuthData authDataTest = new AuthData("123", "first");



  @Test
  void createAuthModel() {
      AuthData authData = generalService.createAuthModel(userData);
      Assertions.assertEquals(authDataTest.username(), authData.username());
    AuthData authData2 = generalService.createAuthModel(userData);
    Assertions.assertNotEquals(authData.authToken(), authData2.authToken());
  }

  @Test
  void checkAuth() throws DataAccessException{
    authDAO.authMap.put("123", authDataTest);
    boolean tru = generalService.checkAuth("123");
    Assertions.assertTrue(tru);
    Assertions.assertThrows(DataAccessException.class, () -> generalService.checkAuth("987"));
  }

  @Test
  void deleteAllData() {
    authDAO.authMap.put("123", authDataTest);
    authDAO.authMap.put("456", authDataTest);
    userDAO.userMap.put("first", userData);
    gameDAO.mapGames.put(123, new GameData(123, "first", null, "GameNameAwesome", new ChessGame()));
    generalService.deleteAllData();
    Assertions.assertEquals(0, authDAO.authMap.size());
    Assertions.assertEquals(0, userDAO.userMap.size());
    Assertions.assertEquals(0, gameDAO.mapGames.size());
  }
}