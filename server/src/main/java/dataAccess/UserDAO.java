package dataAccess;

public interface UserDAO {
  public void clearAllUserData();
  public void createUser(model.UserData newUser) throws DataAccessException;
  public model.UserData getUser(String username) throws DataAccessException;
}
