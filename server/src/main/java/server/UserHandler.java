package server;
import spark.*;
import com.google.gson.Gson;
import java.util.*;
import model.UserData;

public class UserHandler {

  public Object registerUser(Request req, Response res){
    var newUser = new Gson().fromJson(req.body(), UserData.class);
    System.out.print("newUser: ");
    System.out.print(newUser.toString());
    return new Gson().toJson(newUser);}

}
