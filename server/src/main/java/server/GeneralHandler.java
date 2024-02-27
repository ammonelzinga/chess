package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import spark.Request;
import spark.Response;

public class GeneralHandler {

  public Object handleError(DataAccessException ex, Request req, Response res){
    res.status(ex.getStatusCode());
    return new Gson().toJson(new ErrorRecord(ex.getMessage()));
  }

  public Object handleRandomError(Exception ex, Request req, Response res){
    res.status(500);
    return new Gson().toJson(new ErrorRecord(ex.getMessage()));
  }


}
