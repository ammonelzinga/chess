package dataAccess;
import model.GameData;

import java.util.ArrayList;
import java.util.Collection;

public interface GameDAO {
  public void createGame(model.GameData game);
  public model.GameData getGame(int gameID) throws DataAccessException;
  public Collection<GameData> listGames();
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException;
  public void deleteAll();

  public boolean uniqueGameID(int gameID);
}
