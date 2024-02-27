package serviceTests;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.UserService;

import java.util.HashMap;

class UserServiceTest {

  MemoryUserDAO userDAO = new MemoryUserDAO();
  MemoryAuthDAO authDAO = new MemoryAuthDAO();
  UserService userService = new UserService(userDAO, authDAO);
  HashMap<String, UserData> userMapTest = new HashMap<>();
  UserData userData = new UserData("first", "firstP", "firstE");
  UserData userDataSecond = new UserData("second", "secondP", "secondE");
  UserData userDataFake = new UserData("fake", "fakeP", "fakeE");


  @Test
  void register() throws DataAccessException{
    userMapTest.put("first", userData);
    try{userService.register(userData);}
    catch(DataAccessException e){}
    Assertions.assertEquals(userMapTest, userDAO.userMap);
    Assertions.assertThrows(DataAccessException.class, () -> userService.register(userData));
  }

  @Test
  void login() throws DataAccessException {
    AuthData authData = new AuthData("12345qwerty", "second");
    userMapTest.put("second", userDataSecond);
    try{userService.register(userData);}
    catch(DataAccessException e){}
    try {
      userService.register(userDataSecond);
    } catch (DataAccessException e) {}
    try {
      authData=userService.login(userData);
    } catch (DataAccessException e) {
    }
    Assertions.assertEquals("first", authData.username());
    Assertions.assertThrows(DataAccessException.class, () -> userService.login(userDataFake));
  }
  @Test
  void logout() throws DataAccessException{
    AuthData authData = new AuthData("12345qwerty", "second");
    try{ authData = userService.register(userData);}
    catch(DataAccessException e){}
    Assertions.assertEquals(1, authDAO.authMap.size());
    try{
      userService.logout(authData.authToken());
    }
    catch(DataAccessException e){}
    Assertions.assertEquals(0, authDAO.authMap.size());
    Assertions.assertThrows(DataAccessException.class, () -> userService.logout("123"));
  }

}