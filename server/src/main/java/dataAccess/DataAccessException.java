package dataAccess;

/**
 * Indicates there was an error connecting to the database
 */
public class DataAccessException extends Exception{

    int statusCode;
    public DataAccessException(String message) {
        super(message);
    }
    public void addStatusCode(int status){
        statusCode = status;
    }
    public int getStatusCode(){
        return statusCode;
    }
}
