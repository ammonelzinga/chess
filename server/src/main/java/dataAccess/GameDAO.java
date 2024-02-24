package dataAccess;
import model.GameData;

import java.util.ArrayList;
public interface GameDAO {
  public void createGame(model.GameData game) throws DataAccessException;
  public model.GameData getGame(int gameID) throws DataAccessException;
  public ArrayList<model.GameData> listGames();
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException;
  public void deleteAll();

  public boolean uniqueGameID(int gameID);
}
