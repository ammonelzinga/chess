package service;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import dataAccess.AuthDAO;

import java.sql.SQLException;
import java.util.UUID;
public class GeneralService {

  UserDAO userDAO;
  AuthDAO authDAO;
  GameDAO gameDAO;
  public GeneralService(UserDAO userD, AuthDAO authD, GameDAO gameD){
    userDAO = userD;
    authDAO = authD;
    gameDAO = gameD;
  }

  public GeneralService() {

  }

  public AuthData createAuthModel(UserData user){
    AuthData authData;
    String authToken = UUID.randomUUID().toString();
    authData = new AuthData(authToken, user.username());
    return authData;
  }

  public boolean checkAuth(String authToken) throws DataAccessException{
    if(authDAO.checkAuth(authToken)){
      System.out.println("Great Authorization");
      return true;
    }
    else{
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
  }
  public void deleteAllData() throws DataAccessException{
    try{userDAO.clearAllUserData();
    authDAO.deleteAll();
    gameDAO.deleteAll();}
    catch(DataAccessException e){
      throw e;
    }
  }

}
