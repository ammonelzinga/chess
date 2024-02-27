package service;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import dataAccess.AuthDAO;

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
      UserData userData=userDAO.getUser(user.username());
      if (userData.password().equals(user.password())) {
        authData=createAuthModel(user);
        authDAO.createAuth(authData);
        return authData;
      }
      else{
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