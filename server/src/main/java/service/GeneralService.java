package service;
import dataAccess.*;
import model.AuthData;
import model.UserData;
import dataAccess.AuthDAO;

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

  public void deleteAllData(){
    userDAO.clearAllUserData();
    authDAO.deleteAll();
    gameDAO.deleteAll();
  }

}