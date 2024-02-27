package dataAccess;
import model.UserData;
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
      DataAccessException exception = new DataAccessException("Error: already taken");
      exception.addStatusCode(403);
      throw exception;
    }
    else{
    userMap.put(newUser.username(), newUser);
    }
  }

  @Override
  public UserData getUser(String username) throws DataAccessException {
    if(userMap.containsKey(username)){
      return userMap.get(username);
    }
    else{
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
  }

  @Override
  public String toString() {
    return "MemoryUserDAO{" +
            "userMap=" + userMap.values() +
            '}';
  }
}
