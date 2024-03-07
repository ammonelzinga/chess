package dataAccess;
import model.GameData;
import java.util.Collection;
import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class sqlGameDAO implements GameDAO{
  DatabaseManager dbm = new DatabaseManager();

  void configureGameTable() throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      var createGameTable ="""
              CREATE TABLE IF NOT EXISTS gametable (
                gameid INT NOT NULL AUTO_INCREMENT,
                whiteusername VARCHAR(255), DEFAULT NULL,
                blackusername VARCHAR(255), DEFAULT NULL,
                gamename VARCHAR(255), NOT NULL,
                game TEXT NOT NULL,
                PRIMARY KEY (gameid), 
                INDEX (gamename)
              )""";

      try(var createStatement = conn.prepareStatement(createGameTable)){
        createStatement.executeUpdate();
      }
      catch(SQLException e){
        throw new DataAccessException(e.getMessage());
      }
    }
    catch(DataAccessException e){
      throw e;
    }
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }
  }
  @Override
  public void createGame(GameData game) throws DataAccessException {
    var jsonGame = new Gson().toJson(game);
    try (var conn = dbm.getConnection()) {
      try(var addGameStatement = conn.prepareStatement("INSERT INTO gametable (whiteusername, blackusername, gamename, game) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
        addGameStatement.setString(1, game.whiteUsername());
        addGameStatement.setString(2, game.blackUsername());
        addGameStatement.setString(3, game.gameName());
        addGameStatement.setString(4, jsonGame);
        addGameStatement.executeUpdate();
        try (var resultSet = addGameStatement.getGeneratedKeys()){
          var id = "";
          if(resultSet.next()){
            id = resultSet.getString(1);
          }
          System.out.println("New Game Primary Key: ");
          System.out.print(id);}
      }}
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }
  }

  @Override
  public GameData getGame(int gameID) throws DataAccessException {
    return null;
  }

  @Override
  public Collection<GameData> listGames() {
    return null;
  }

  @Override
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException {

  }

  @Override
  public void deleteAll() {

  }
}
