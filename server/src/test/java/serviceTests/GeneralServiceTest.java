package serviceTests;

import chess.InvalidMoveException;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.UserService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GeneralServiceTest {

  MemoryUserDAO userDAO = new MemoryUserDAO();
  MemoryAuthDAO authDAO = new MemoryAuthDAO();
  UserService userService = new UserService(userDAO, authDAO);
  HashMap<String, UserData> userMapTest = new HashMap<>();
  UserData userData = new UserData("first", "firstP", "firstE");
  UserData userDataSecond = new UserData("second", "secondP", "secondE");
  UserData userDataFake = new UserData("fake", "fakeP", "fakeE");



  @Test
  void createAuthModel() {

  }

  @Test
  void checkAuth() {
  }

  @Test
  void deleteAllData() {
  }
}