package dataAccess;
import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlAuthDAO implements AuthDAO{

  DatabaseManager dbm = new DatabaseManager();

   void configureAuthTable() throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      var createAuthTable ="""
              CREATE TABLE IF NOT EXISTS authtable (
                authtoken VARCHAR(255) NOT NULL,
                username VARCHAR(255) NOT NULL,
                PRIMARY KEY (authtoken)
              )""";

      try(var createStatement = conn.prepareStatement(createAuthTable)){
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
  public void createAuth(AuthData authData) throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      try(var addAuthStatement = conn.prepareStatement("INSERT INTO authtable (authtoken, username) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)){
        addAuthStatement.setString(1, authData.authToken());
        addAuthStatement.setString(2, authData.username());
        addAuthStatement.executeUpdate();
        try (var resultSet = addAuthStatement.getGeneratedKeys()) {
          var id="";
          if (resultSet.next()) {
            id=resultSet.getString(1);
          }
          //System.out.println("New Auth Primary Key: ");
          //System.out.print(id);}
        }
      }}
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }
  }

  @Override
  public AuthData getAuth(String authToken) throws DataAccessException {
    try(var conn = dbm.getConnection()) {
      var selectStatement = "SELECT authtoken, username FROM authtable WHERE authtoken=?";
      try (var ps = conn.prepareStatement(selectStatement)){
        ps.setString(1, authToken);
        try(var rs = ps.executeQuery()) {
          while (rs.next()) {
            var authT = rs.getString("authtoken");
            var username = rs.getString("username");
            AuthData auth = new AuthData(authT, username);
            if(auth != null){
            return auth;}
            else{
              DataAccessException exception = new DataAccessException("Error: unauthorized");
              exception.addStatusCode(401);
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
  public void deleteAuth(String authToken) throws DataAccessException {
    try{if(getAuth(authToken) != null){
    try(var conn = dbm.getConnection()) {
      var selectStatement = "DELETE FROM authtable WHERE authtoken=?";
      try (var ps = conn.prepareStatement(selectStatement)){
        ps.setString(1, authToken);
        ps.executeUpdate();
        System.out.print("tried to delete authtoken");
          }}}
    else{DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
    }
    catch(SQLException e){
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
    catch(DataAccessException e){
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
    catch(Exception e){
      DataAccessException exception = new DataAccessException("Error: unauthorized");
      exception.addStatusCode(401);
      throw exception;
    }
  }

  @Override
  public boolean checkAuth(String authToken) {
    try{if(getAuth(authToken) != null){ return true;}
      else{return false;}
    }
    catch(DataAccessException e){
      return false;
    }
  }

  @Override
  public void deleteAll() throws DataAccessException{
    try (var conn = dbm.getConnection()) {
      try(var deleteAuthTableStatement = conn.prepareStatement("TRUNCATE authtable")){
        deleteAuthTableStatement.executeUpdate();
      }}
    catch(SQLException e){
      DataAccessException exception = new DataAccessException(e.getMessage());
      throw exception;
    }
  }
}
