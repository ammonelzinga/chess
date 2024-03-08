package dataAccessTests;
import dataAccess.UserDAO;
import dataAccess.sqlUserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.UserData;
import dataAccess.DataAccessException;
import static org.junit.jupiter.api.Assertions.*;

class sqlUserDAOTest {
  public UserDAO sqlUserDao=new sqlUserDAO();

  @Test
  void clearAllUserData() {
      UserData newUser=new UserData("one", "one", "one@fakeemail.com");
      UserData newUser1=new UserData("two", "one", "one@fakeemail.com");
      try {
        sqlUserDao.createUser(newUser);
        sqlUserDao.createUser(newUser1);
      sqlUserDao.clearAllUserData();
      Assertions.assertNull(sqlUserDao.getUser("one"));
        Assertions.assertNull(sqlUserDao.getUser("two"));
      }
      catch(DataAccessException e){
        System.out.print(e.getMessage());
      }
  }

  @Test
  void createUser() {
    UserData newUser=new UserData("Eeyore", "dismal", "eeyore@fakeemail.com");
    UserDAO sqlUserDao=new sqlUserDAO();
    try {
      sqlUserDao.createUser(newUser);
      UserData sqlUser=sqlUserDao.getUser("Eeyore");
      Assertions.assertEquals(sqlUser.email(), newUser.email());
      Assertions.assertEquals(sqlUser.email(), newUser.email());
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }

  }

  @Test
  void createUserNeg() {
    UserData newUser=new UserData("Piglet", "ohdear", "piglet@fakeemail.com");
    UserDAO sqlUserDao=new sqlUserDAO();
    try {
      sqlUserDao.createUser(newUser);
      Assertions.assertThrows(DataAccessException.class, () -> sqlUserDao.createUser(newUser));
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }

  }

  @Test
  void getUser() {
    UserData newUser=new UserData("Tigger", "bouncy", "tigger@fakeemail.com");
    try {
      sqlUserDao.createUser(newUser);
      UserData sqlUser=sqlUserDao.getUser("Tigger");
      Assertions.assertEquals(sqlUser.email(), newUser.email());
      Assertions.assertEquals(sqlUser.email(), newUser.email());
    } catch (DataAccessException e) {
      System.out.print(e.getMessage());
    }
  }

  @Test
  void getUserNeg() {
    try{Assertions.assertNull(sqlUserDao.getUser("fakefakefake"));}
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }
}