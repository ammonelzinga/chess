package dataAccess;
import model.AuthData;

import javax.xml.crypto.Data;
import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO{

  HashMap<String, AuthData> authMap = new HashMap<>();
  @Override
  public void createAuth(model.AuthData authData) throws DataAccessException {
      if(authMap.containsKey(authData.authToken())){
        DataAccessException exception = new DataAccessException("Auth Token already exists");
        throw exception;
      }
      else{
        authMap.put(authData.authToken(), authData);
      }
  }

  @Override
  public model.AuthData getAuth(String authToken) throws DataAccessException{
    if(authMap.containsKey(authToken)){
      return authMap.get(authToken);
    }
    else{
      DataAccessException exception = new DataAccessException("Auth Token doesn't exist");
      throw exception;
    }
  }

  @Override
  public boolean checkAuth(String authToken) {
    if(authMap.containsKey(authToken)){
      return true;}
    else{
      return false;
    }
  }
  @Override
  public void deleteAuth(String authToken) throws DataAccessException {
        if(authMap.containsKey(authToken)){
    authMap.remove(authToken);}
        else{
          DataAccessException exception = new DataAccessException("Error: unauthorized");
          exception.addStatusCode(401);
          throw exception;
        }

  }
@Override
  public void deleteAll(){
    authMap.clear();
  }
}
