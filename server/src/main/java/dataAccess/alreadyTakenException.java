package dataAccess;

/**
 * Indicates there was an error connecting to the database
 */
public class alreadyTakenException extends Exception{

  //public DataAccessException() {}
  public alreadyTakenException(String message) {
    super(message);
  }
}