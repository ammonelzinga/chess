package dataAccessTests;
import dataAccess.UserDAO;
import dataAccess.sqlUserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.UserData;
import dataAccess.AuthDAO;
import dataAccess.sqlAuthDAO;
import model.AuthData;
import dataAccess.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;

class sqlAuthDAOTest {
  sqlAuthDAO dao = new sqlAuthDAO();
  @Test
  void createAuth() {
    AuthData auth = new AuthData("123", "one");
    try{
      dao.createAuth(auth);
      AuthData sqlAuth = dao.getAuth("123");
      Assertions.assertEquals(auth, sqlAuth);
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void createAuthNeg() {
    AuthData auth = new AuthData("321", "two");
    try{
      dao.createAuth(auth);
      Assertions.assertThrows(DataAccessException.class, ()->  dao.createAuth(auth));
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void getAuth() {
    AuthData auth = new AuthData("3", "three");
    try {
      dao.createAuth(auth);
      AuthData sqlAuth=dao.getAuth("3");
      Assertions.assertEquals(sqlAuth, auth);
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }
  }

  @Test
  void getAuthNeg() {
    AuthData auth = new AuthData("4", "four");
    try {
      dao.createAuth(auth);
      AuthData sqlAuth=dao.getAuth("9854984");
      Assertions.assertNull(sqlAuth);
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }
  }

  @Test
  void deleteAuth() {
    AuthData auth = new AuthData("10", "ten");
    try{
      dao.createAuth(auth);
      dao.deleteAuth("10");
      Assertions.assertNull(dao.getAuth("10"));
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void deleteAuthNeg() {
    AuthData auth = new AuthData("12", "ten");
    AuthData auth1 = new AuthData("11", "eleven");
    try{
      dao.createAuth(auth);
      dao.createAuth(auth1);
      dao.deleteAuth("12");
      Assertions.assertNotNull(dao.getAuth("11"));
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  @Test
  void checkAuth() {
    AuthData auth = new AuthData("5", "five");
    try {
      dao.createAuth(auth);
      Assertions.assertTrue(dao.checkAuth("5"));
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }
  }

  @Test
  void checkAuthNeg() {
    AuthData auth = new AuthData("5", "five");
    try {
      dao.createAuth(auth);
      Assertions.assertFalse(dao.checkAuth("984984984"));
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }
  }

  @Test
  void deleteAll() {
    AuthData auth = new AuthData("12", "ten");
    AuthData auth1 = new AuthData("11", "eleven");
    try{
      dao.createAuth(auth);
      dao.createAuth(auth1);
      dao.deleteAll();
      Assertions.assertNull(dao.getAuth("11"));
      Assertions.assertNull(dao.getAuth("12"));
    }
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }
}