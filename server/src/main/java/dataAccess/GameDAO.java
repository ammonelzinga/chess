package dataAccess;
import model.GameData;
import java.util.Collection;

public interface GameDAO {
  public int createGame(model.GameData game) throws DataAccessException;
  public model.GameData getGame(int gameID) throws DataAccessException;
  public Collection<GameData> listGames() throws DataAccessException;
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException;
  public void deleteAll() throws DataAccessException;
}
