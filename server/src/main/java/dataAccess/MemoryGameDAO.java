package dataAccess;
import model.GameData;
import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
  HashMap<Integer, GameData> mapGames = new HashMap<>();

  @Override
  public void createGame(model.GameData game){
        mapGames.put(game.gameID(), game);
  }

  @Override
  public model.GameData getGame(int gameID) throws DataAccessException {
    if(mapGames.containsKey(gameID)){
      return mapGames.get(gameID);
    }
    else{
      DataAccessException exception = new DataAccessException("Error: bad request");
      exception.addStatusCode(400);
      throw exception;
    }
  }

  @Override
  public Collection<GameData> listGames() {
      return mapGames.values();
  }

  @Override
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException {
    if(mapGames.containsKey(gameID)){
      mapGames.replace(gameID, updatedGame);
    }
    else{
      DataAccessException exception = new DataAccessException("Error: bad request");
      exception.addStatusCode(400);
      throw exception;
    }
  }
@Override
  public void deleteAll(){
    mapGames.clear();
  }

}
