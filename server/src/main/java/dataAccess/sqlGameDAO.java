package dataAccess;
import model.GameData;
import java.util.ArrayList;
import java.util.Collection;
import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import server.JoinGameRecord;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class sqlGameDAO implements GameDAO{
  DatabaseManager dbm = new DatabaseManager();

  public sqlGameDAO(){
    try{configureGameTable();}
    catch(DataAccessException e){
      System.out.print(e.getMessage());
    }
  }

  void configureGameTable() throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      var createGameTable ="""
              CREATE TABLE IF NOT EXISTS gametable (
                gameid INT NOT NULL AUTO_INCREMENT,
                whiteusername VARCHAR(255) DEFAULT NULL,
                blackusername VARCHAR(255) DEFAULT NULL,
                gamename VARCHAR(255) NOT NULL,
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
  public int createGame(GameData game) throws DataAccessException {
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
          //System.out.println("New Game Primary Key: ");
          //System.out.print(id);
          int gameID = Integer.parseInt(id);
          return gameID;}
      }}
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }
  }

  @Override
  public GameData getGame(int gameId) throws DataAccessException {
    try(var conn = dbm.getConnection()) {
      var selectStatement = "SELECT gameid, game FROM gametable WHERE gameid=?";
      try (var ps = conn.prepareStatement(selectStatement)){
        ps.setInt(1, gameId);
        try(var rs = ps.executeQuery()) {
          while (rs.next()) {
            /*var gameID = rs.getInt("gameid");
            var whiteUsername = rs.getString("whiteusername");
            var blackUsername = rs.getString("blackusername");
            var gameName = rs.getString("gamename");*/
            var game = rs.getString("game");
            var gameData = new Gson().fromJson(game, GameData.class);
            if(gameData != null){
              //System.out.print("gameId: ");
              //System.out.print(gameData.gameID());
              return gameData;
            }
            else{
              DataAccessException exception = new DataAccessException("Error: bad request");
              exception.addStatusCode(400);
              throw exception;
            }
          }}}}
    catch(SQLException e){
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
    return null;
  }

  @Override
  public Collection<GameData> listGames() throws DataAccessException{
    HashMap<Integer, GameData> mapGames = new HashMap<>();
    try (var conn = dbm.getConnection()) {
      var statement = "SELECT gameid, game FROM gametable";
      try (var ps = conn.prepareStatement(statement)) {
        try (var rs = ps.executeQuery()) {
          while (rs.next()) {
            var gameID = rs.getInt("gameid");
            mapGames.put(gameID, readGame(rs, gameID));
          }}}
    return mapGames.values();}
  catch(SQLException e){
    DataAccessException exception = new DataAccessException("Error: unauthorized");
    exception.addStatusCode(401);
    throw exception;
    }
  }
  GameData readGame(ResultSet rs, int gameID) throws DataAccessException{
    try{var gameString = rs.getString("game");
    var gameData = new Gson().fromJson(gameString, GameData.class);
    GameData realGame = new GameData(gameID, gameData.whiteUsername(), gameData.blackUsername(), gameData.gameName(), gameData.game());
    return realGame;}
    catch(SQLException e){
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
  }

  @Override
  public void updateGame(int gameID, GameData updatedGame) throws DataAccessException {
    //UPDATE pet SET name = 'fido' WHERE id = 1;
    try{if(getGame(gameID) != null){
    var jsonGame = new Gson().toJson(updatedGame);
    try (var conn = dbm.getConnection()) {
      try(var updateGameStatement = conn.prepareStatement("UPDATE gametable SET game = ?, whiteusername = ?, blackusername = ? WHERE gameid = ?")){
        updateGameStatement.setString(1, jsonGame);
        updateGameStatement.setString(2, updatedGame.whiteUsername());
        updateGameStatement.setString(3, updatedGame.blackUsername());
        updateGameStatement.setInt(4, gameID);
        updateGameStatement.executeUpdate();}}
    catch(SQLException e){
      //System.out.print(e.getMessage());
      throw new DataAccessException(e.getMessage());
    }}
    else{
      System.out.print("not a game id");
      DataAccessException exception = new DataAccessException("Error: bad request");
      exception.addStatusCode(400);
      throw exception;
    }
    }
    catch(DataAccessException e){
      throw e;
    }
  }

  @Override
  public void deleteAll() throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      try(var deleteUserTableStatement = conn.prepareStatement("TRUNCATE gametable")){
        deleteUserTableStatement.executeUpdate();
      }}
    catch(SQLException e){
      DataAccessException exception = new DataAccessException(e.getMessage());
      throw exception;
    }
  }
}
