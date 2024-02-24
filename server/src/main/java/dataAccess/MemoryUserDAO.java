package dataAccess;
import model.UserData;

import javax.xml.crypto.Data;
import java.util.HashMap;
public class MemoryUserDAO implements UserDAO{

  HashMap<String, UserData> userMap = new HashMap<>();

  @Override
  public void clearAllUserData() {
    userMap.clear();
  }

  @Override
  public void createUser(UserData newUser) throws DataAccessException {
    if(userMap.containsKey(newUser.username())){
      DataAccessException exception = new DataAccessException("username already exists");
      throw exception;
    }
    else{
    userMap.put(newUser.username(), newUser);}
  }

  @Override
  public UserData getUser(String username) throws DataAccessException {
    if(userMap.containsKey(username)){
      return userMap.get(username);
    }
    else{
      DataAccessException exception = new DataAccessException("username doesn't exist");
      throw exception;
    }
  }


}
