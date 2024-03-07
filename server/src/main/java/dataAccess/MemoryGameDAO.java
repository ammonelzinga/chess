package dataAccess;
import model.GameData;
import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
  public HashMap<Integer, GameData> mapGames = new HashMap<>();

  @Override
  public int createGame(model.GameData game){
        mapGames.put(game.gameID(), game);
        return game.gameID();
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
  public Collection<GameData> listGames() throws DataAccessException {
      try{return mapGames.values();}
      catch(Exception e){
        DataAccessException exception = new DataAccessException("Error: unauthorized");
        exception.addStatusCode(401);
        throw exception;
      }
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
  public void deleteAll() throws DataAccessException{
    try{mapGames.clear();}
    catch(Exception e){
      DataAccessException exception = new DataAccessException(e.getMessage());
      throw exception;
    }
  }

}
