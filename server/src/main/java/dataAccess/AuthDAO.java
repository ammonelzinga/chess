package dataAccess;

public interface AuthDAO {
  public void createAuth(model.AuthData authData) throws DataAccessException;
  public model.AuthData getAuth(String authToken) throws DataAccessException;
  public void deleteAuth(String authToken) throws DataAccessException;
  public void deleteAll();
}