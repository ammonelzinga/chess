package dataAccess;
import model.GameData;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
  HashMap<Integer, GameData> mapGames = new HashMap<>();
  ArrayList<GameData> arrayGames = new ArrayList<>();
  @Override
  public void createGame(model.GameData game){
        mapGames.put(game.gameID(), game);
        arrayGames.add(game);
  }

  @Override
  public model.GameData getGame(int gameID) throws DataAccessException {
    if(mapGames.containsKey(gameID)){
      return mapGames.get(gameID);
    }
    else{
      DataAccessException exception = new DataAccessException("Game ID not found");
      throw exception;
    }
  }
  @Override
  public boolean uniqueGameID(int gameID){
    if(mapGames.containsKey(gameID)){
      return false;
  }
    else{
      return true;
    }
  }

  @Override
  public Collection<GameData> listGames() {
    /*if(mapGames.isEmpty()){
      return null;
    }*/
    //else {
      //return new ArrayList<GameData>(mapGames.values());
      return mapGames.values();
    //}
  }

  @Override
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException {
    if(mapGames.containsKey(gameID)){
      mapGames.replace(gameID, updatedGame);
    }
    else{
      DataAccessException exception = new DataAccessException("Game ID not found");
      throw exception;
    }
  }
@Override
  public void deleteAll(){
    mapGames.clear();
    arrayGames.clear();
  }
}
