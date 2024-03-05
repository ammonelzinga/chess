package dataAccess;
import com.google.gson.Gson;
import model.UserData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlUserDAO implements UserDAO{
  DatabaseManager dbm = new DatabaseManager();

  void configureUserTable() throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      var createUserTable ="""
              CREATE TABLE IF NOT EXISTS usertable (
                username VARCHAR(255) NOT NULL,
                password VARCHAR(255), NOT NULL,
                email VARCHAR(255), NOT NULL,
                PRIMARY KEY (username)
              )""";

      try(var createStatement = conn.prepareStatement(createUserTable)){
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
  public void clearAllUserData() {

  }

  @Override
  public void createUser(UserData newUser) throws DataAccessException {
    try (var conn = dbm.getConnection()) {
      try(var addUserStatement = conn.prepareStatement("INSERT INTO usertable (username, password, email) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
      addUserStatement.setString(1, newUser.username());
      addUserStatement.setString(2, newUser.password());
      addUserStatement.setString(3, newUser.email());
      addUserStatement.executeUpdate();
      try (var resultSet = addUserStatement.getGeneratedKeys()){
      var id = "";
      if(resultSet.next()){
        id = resultSet.getString(1);
      }
      System.out.println("New Users Primary Key: ");
      System.out.print(id);}
    }}
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }
  }

  @Override
  public UserData getUser(String username) throws DataAccessException {
    try(var conn = dbm.getConnection()) {
      var selectStatement = "SELECT username, password, email FROM usertable WHERE username=?";
      try (var ps = conn.prepareStatement(selectStatement)){
        ps.setString(1, username);
        try(var rs = ps.executeQuery()) {
          while (rs.next()) {
            var name = rs.getString("username");
            var password = rs.getString("password");
            var email = rs.getString("email");
            UserData user = new UserData(name, password, email);
            return user;
          }
        }
      }
    }
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }
    return null;
  }

  private UserData readUser(ResultSet rs) throws DataAccessException {
    try{var username = rs.getString("username");
    var json = rs.getString("json");
    var userData = new Gson().fromJson(json, UserData.class);
    return userData;
  }
    catch(SQLException e){
      throw new DataAccessException(e.getMessage());
    }}
}
