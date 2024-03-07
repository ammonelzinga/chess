package service;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import dataAccess.AuthDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService extends GeneralService{
  UserDAO userDAO;
  AuthDAO authDAO;
  public UserService(UserDAO userD, AuthDAO authD){
    userDAO = userD;
    authDAO = authD;
  }
  public AuthData register(UserData user) throws DataAccessException{
      AuthData authData = null;
      if(user.username() == null || user.email() == null || user.password() == null){
        DataAccessException exception = new DataAccessException("Error: bad request");
        exception.addStatusCode(400);
        throw exception;
      }
      try{
         userDAO.createUser(user);
         authData = createAuthModel(user);
         authDAO.createAuth(authData);
      }
      catch(DataAccessException exception){
          throw exception;
      }
      return authData;
  }
  public AuthData login(UserData user) throws DataAccessException {
      AuthData authData=null;
      try{UserData userData=userDAO.getUser(user.username());
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      if (encoder.matches(user.password(), userData.password())) {
        authData=createAuthModel(user);
        authDAO.createAuth(authData);
        return authData;
      }
      else{
        DataAccessException exception = new DataAccessException("Error: unauthorized");
        exception.addStatusCode(401);
        throw exception;
      }}
      catch(DataAccessException e){
        DataAccessException exception = new DataAccessException("Error: unauthorized");
        exception.addStatusCode(401);
        throw exception;
      }
      catch(Exception e){
        DataAccessException exception = new DataAccessException("Error: unauthorized");
        exception.addStatusCode(401);
        throw exception;
      }
  }

  public void logout(String authToken) throws DataAccessException {
    try {
      authDAO.deleteAuth(authToken);}
    catch(DataAccessException exception){
      throw exception;
    }
  }

}