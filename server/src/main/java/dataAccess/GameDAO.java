package dataAccess;
import model.GameData;
import java.util.Collection;

public interface GameDAO {
  public void createGame(model.GameData game) throws DataAccessException;
  public model.GameData getGame(int gameID) throws DataAccessException;
  public Collection<GameData> listGames();
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException;
  public void deleteAll();
}
