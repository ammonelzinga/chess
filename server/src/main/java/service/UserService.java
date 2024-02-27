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
        System.out.println("BAD Requestt");
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
  public AuthData login(UserData user) {
    AuthData authData=null;
    try {
      UserData userData=userDAO.getUser(user.username());
      if (userData.password() == user.password()) {
        authData=createAuthModel(user);
        authDAO.createAuth(authData);
      }
    } catch (Exception e) {
      return null;
    }
    return authData;
  }

  public void logout(String authToken) {
    try {
      authDAO.deleteAuth(authToken);}
    catch(Exception e){}
  }

}